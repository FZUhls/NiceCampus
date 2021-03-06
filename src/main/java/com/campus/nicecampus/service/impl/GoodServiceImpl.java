package com.campus.nicecampus.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campus.nicecampus.base.exception.AuthorityException;
import com.campus.nicecampus.base.mapper.GoodsDetailMapper;
import com.campus.nicecampus.base.model.GoodsDetail;
import com.campus.nicecampus.req.AddGoodsReq;
import com.campus.nicecampus.service.CartService;
import com.campus.nicecampus.service.GoodService;
import com.campus.nicecampus.service.OssService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.AuthenticationException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
@Slf4j
@Service
public class GoodServiceImpl extends BaseService implements GoodService {
    @Autowired
    GoodsDetailMapper goodsDetailMapper;
    @Autowired
    CartService cartService;
    @Autowired
    OssService ossService;
    @Value("${aliyun.oss.baseUrl}")
    private String BASE_URL;
    @Override
    public void addGood(AddGoodsReq req, MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID().toString();
        GoodsDetail goodsDetail = new GoodsDetail();
        goodsDetail.setGoodsDesc(req.getGoodsDesc());
        goodsDetail.setGoodsName(req.getGoodsName());
        goodsDetail.setLabels(req.getLabels());
        goodsDetail.setPrice(Double.parseDouble(req.getPrice()));
        goodsDetail.setType(req.getType());
        goodsDetail.setUserId(getUser().getId());
        ossService.upLoad(fileName, file.getInputStream(), file.getContentType());

        String url = BASE_URL+fileName;
        log.info("???????????????????????????uri???{}",url);
        goodsDetail.setImgUrl(url);
        goodsDetailMapper.insert(goodsDetail);
    }

    @Override
    public GoodsDetail getGoods(long id) {
        return null;
    }

    @Override
    public Page<GoodsDetail> findPages(long pageNo, String type) {
        Page<GoodsDetail> page = new Page<>();
        page.setCurrent(pageNo);
        page.setSize(10);
        QueryWrapper<GoodsDetail> queryWrapper = new QueryWrapper<>();
        if(!Objects.equals(type,"all")){
            queryWrapper.eq("type",type);
        }
        return goodsDetailMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<GoodsDetail> getAllGoodsByUserId(long userId) {
        return goodsDetailMapper.selectByMap(Map.of("user_id",userId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteGoods(long goodsId) throws AuthorityException {
        GoodsDetail goodsDetail = goodsDetailMapper.selectById(goodsId);
        try {
            if(Objects.equals(getUser().getId(),goodsDetail.getUserId())){
                cartService.deleteOneCart(goodsId);
                goodsDetailMapper.deleteById(goodsId);
                ossService.delete(goodsDetail.getImgUrl());
            }else {
                throw new AuthorityException("????????????????????????");
            }
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public GoodsDetail findById(long id) {
        return goodsDetailMapper.selectById(id);
    }
}
