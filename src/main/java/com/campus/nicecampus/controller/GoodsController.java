package com.campus.nicecampus.controller;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campus.nicecampus.base.model.GoodsDetail;
import com.campus.nicecampus.base.model.WantRecord;
import com.campus.nicecampus.req.AddGoodsReq;
import com.campus.nicecampus.req.WantRecordReq;
import com.campus.nicecampus.res.BaseResponse;
import com.campus.nicecampus.service.GoodService;
import com.campus.nicecampus.service.WantRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Controller
public class GoodsController extends BaseController{

    @Autowired
    GoodService goodService;
    @Autowired
    WantRecordService wantRecordService;
    @GetMapping("/goodList")
    public String goodList(Model model){
        model.addAttribute("user",getUser());
        return "goodList";
    }
    @GetMapping("/findGoods")
    public String findGoods(Model model){
        model.addAttribute("user",getUser());
        return "findGoods";
    }
    @GetMapping("/good/seekGoods")
    public String seekGoods(Model model){
        model.addAttribute("user",getUser());
        return "seekGoods";
    }
    @GetMapping("/good/releaseGoods")
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
    @ResponseBody
    @PostMapping("/goods/getTypesGoods")
    public BaseResponse<Page<GoodsDetail>> goods(int currentPage, String type){
        Page<GoodsDetail> goodsDetailPage = goodService.findPages(currentPage, type);
        BaseResponse<Page<GoodsDetail>> res = BaseResponse.succ();
        res.setData(goodsDetailPage);
        return res;
    }
    @PostMapping("/goods/addWantRecord")
    public String addWantRecord(WantRecordReq wantRecordReq){
        wantRecordService.add(wantRecordReq);
        return "redirect:/findGoods";
    }
    @ResponseBody
    @PostMapping("/goods/getRecords")
    public BaseResponse<Page<WantRecord>> getRecords(int currentPage){
        Page<WantRecord> pages = wantRecordService.getPages(currentPage);
        BaseResponse<Page<WantRecord>> res = BaseResponse.succ();
        res.setData(pages);
        return res;
    }
}
