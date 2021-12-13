package com.campus.nicecampus.service.impl;

import com.aliyun.oss.model.PutObjectResult;
import com.campus.nicecampus.base.mapper.GoodsDetailMapper;
import com.campus.nicecampus.base.model.GoodsDetail;
import com.campus.nicecampus.req.AddGoodsReq;
import com.campus.nicecampus.service.GoodService;
import com.campus.nicecampus.service.OssService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;
@Slf4j
@Service
public class GoodServiceImpl implements GoodService {
    @Autowired
    GoodsDetailMapper goodsDetailMapper;
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
        goodsDetail.setUserId(1L);
        ossService.upLoad(fileName, file.getInputStream(), file.getContentType());

        String url = BASE_URL+fileName;
        log.info("保存图片成功，图片uri为{}",url);
        goodsDetail.setImgUrl(url);
        goodsDetailMapper.insert(goodsDetail);
    }

    @Override
    public GoodsDetail getGoods(long id) {
        return null;
    }
}