package com.campus.nicecampus.service.impl;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.util.HashUtil;
import cn.hutool.core.util.StrUtil;
import com.aliyun.oss.OSS;
import com.campus.nicecampus.base.exception.FileTypeIllegalException;
import com.campus.nicecampus.base.mapper.UserMapper;
import com.campus.nicecampus.base.model.User;
import com.campus.nicecampus.req.SignUpReq;
import com.campus.nicecampus.req.UpdateUserInfoReq;
import com.campus.nicecampus.service.OssService;
import com.campus.nicecampus.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl extends BaseService implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    OssService ossService;
    @Value("${aliyun.oss.baseUrl}")
    private String BASE_URL;
    private static final Set<String> FILE_HEAD_SET = new HashSet<>();
    static {
        FILE_HEAD_SET.add("jpg");
        FILE_HEAD_SET.add("jpeg");
        FILE_HEAD_SET.add("png");
    }
    @Override
    public User getById(long id) {
        User user = userMapper.selectById(id);
        user.setPassword("");
        return user;
    }

    @Override
    public void signUp(SignUpReq req) throws Exception{
        if(StrUtil.isBlank(req.getPassword()) || StrUtil.isBlank(req.getPassword2())){
            throw new Exception("密码不可为空");
        }else if(!Objects.equals(req.getPassword(), req.getPassword2())){
            throw new Exception("两次输入的密码不一致");
        }else {
            User user = new User();
            user.setUsername(req.getUsername());
            user.setTel(req.getTel());
            user.setPassword(req.getPassword());
            userMapper.insert(user);
        }
    }

    @Override
    public void updateUser(UpdateUserInfoReq req, MultipartFile file) throws FileTypeIllegalException{
        String fileName = UUID.randomUUID().toString();
        try {
            User user = userMapper.selectById(getUser().getId());
            user.setUsername(req.getUsername());
            user.setSignature(req.getSignature());
            user.setWechatId(req.getWeChatId());
            if(file.getSize() != 0){
                if(!FILE_HEAD_SET.contains(FileTypeUtil.getType(file.getInputStream()))){
                    throw new FileTypeIllegalException();
                }
                long iconHashNew = HashUtil.cityHash64(file.getBytes());
                long iconHashOld = user.getIconHash();
                if(iconHashNew != iconHashOld){
                    //上传新的头像图片
                    ossService.upLoad(fileName, file.getInputStream(), file.getContentType());
                    String url = BASE_URL + fileName;
                    //删除旧的头像图片
                    ossService.delete(user.getIconUrl());
                    user.setIconUrl(url);
                    user.setIconHash(iconHashNew);
                    log.info("保存图片成功，图片uri为{}",url);
                }else {
                    log.info("新图片与原图相同不做更新处理");
                }
            }
            userMapper.updateById(user);
        } catch (IOException e) {
            log.error("上传头像时发生异常...",e);
        }
    }
}
