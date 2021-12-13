package com.campus.nicecampus.controller;

import com.campus.nicecampus.req.AddGoodsReq;
import com.campus.nicecampus.res.BaseResponse;
import com.campus.nicecampus.service.GoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@Controller
public class GoodsController {

    @Autowired
    GoodService goodService;
    @GetMapping("/goodList")
    public String goodList(){
        return "goodList";
    }
    @GetMapping("/findGoods")
    public String findGoods(){
        return "findGoods";
    }
    @GetMapping("/releaseGoods")
    public String releaseGoods(){
        return "releaseGoods";
    }
    @PostMapping("/goods/addGoods")
    @ResponseBody
    public BaseResponse addGoods(AddGoodsReq req, MultipartFile image){
        log.info("接收商品添加请求 = {}",req);
        try {
            goodService.addGood(req,image);
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setCode(1);
            return baseResponse;
        } catch (Exception e) {
            log.error("添加商品时发生错误，异常...",e);
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setCode(0);
            return baseResponse;
        }
    }
}
