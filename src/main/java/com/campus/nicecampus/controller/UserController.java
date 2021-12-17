package com.campus.nicecampus.controller;

import cn.hutool.core.net.multipart.UploadFile;
import cn.hutool.json.JSONUtil;
import com.campus.nicecampus.base.exception.FileTypeIllegalException;
import com.campus.nicecampus.base.model.User;
import com.campus.nicecampus.req.SignUpReq;
import com.campus.nicecampus.req.UpdateUserInfoReq;
import com.campus.nicecampus.res.BaseResponse;
import com.campus.nicecampus.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@Slf4j
public class UserController extends BaseController{
    @Autowired
    UserService userService;
    @PostMapping("/user/getOneUser")
    @ResponseBody
    public BaseResponse<User> getOneUser(long userId){
        BaseResponse<User> res = BaseResponse.succ();
        res.setData(userService.getById(userId));
        return res;
    }
    @PostMapping("/user/register")
    public String register(SignUpReq req, Model model){
        try {
            log.info("接受到注册请求---{}",JSONUtil.toJsonStr(req));
            userService.signUp(req);
            return "redirect:/login";
        }catch (Exception e) {
            log.error("注册用户 {} 时发生错误...", JSONUtil.toJsonStr(req), e);
            model.addAttribute("flag", 0);
            model.addAttribute("errMsg", e.getLocalizedMessage());
            return "redirect:/login";
        }
    }
    @GetMapping("/user/userInfo")
    public String userInfo(Model model){
        model.addAttribute("user",getUser());
        return "/userInfo";
    }
    @PostMapping("/user/updateUserInfo")
    @ResponseBody
    public BaseResponse updateUserInfo(UpdateUserInfoReq req, MultipartFile iconUrl){
        try {
            userService.updateUser(req,iconUrl);
        }catch (FileTypeIllegalException e){
            log.error("更新用户信息时发生异常...",e);
            BaseResponse fail = BaseResponse.fail();
            fail.setMsg(e.getLocalizedMessage());
            return fail;
        }
        return BaseResponse.succ();
    }
}
