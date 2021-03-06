package com.campus.nicecampus.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campus.nicecampus.base.exception.AuthorityException;
import com.campus.nicecampus.base.model.Comments;
import com.campus.nicecampus.base.model.GoodsDetail;
import com.campus.nicecampus.base.model.WantRecord;
import com.campus.nicecampus.req.AddGoodsReq;
import com.campus.nicecampus.req.WantRecordReq;
import com.campus.nicecampus.res.BaseResponse;
import com.campus.nicecampus.service.CommentService;
import com.campus.nicecampus.service.GoodService;
import com.campus.nicecampus.service.WantRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Controller
public class GoodsController extends BaseController{

    @Autowired
    GoodService goodService;
    @Autowired
    WantRecordService wantRecordService;
    @Autowired
    CommentService commentService;
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
    @GetMapping("/good/detail")
    public String goodDetail(Model model,long id){
        model.addAttribute("user",getUser());
        model.addAttribute("id",id);
        return "detail";
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
        log.info("???????????????????????? = {}",req);
        try {
            goodService.addGood(req,image);
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setCode(1);
            return baseResponse;
        } catch (Exception e) {
            log.error("????????????????????????????????????...",e);
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

    @ResponseBody
    @PostMapping("/good/getReleased")
    public BaseResponse<List<GoodsDetail>> getAllReleasedByUser(){
        List<GoodsDetail> allGoodsByUserId = goodService.getAllGoodsByUserId(getUser().getId());
        BaseResponse<List<GoodsDetail>> succ = BaseResponse.succ();
        succ.setData(allGoodsByUserId);
        return succ;
    }
    @ResponseBody
    @PostMapping("/good/getMySeek")
    public BaseResponse<List<WantRecord>> getAllWantRecordByUser(){
        List<WantRecord> wantRecordList = wantRecordService.getAllWantRecordsByUserId(getUser().getId());
        BaseResponse<List<WantRecord>> succ = BaseResponse.succ();
        succ.setData(wantRecordList);
        return succ;
    }
    @ResponseBody
    @PostMapping("/good/getOneGoods")
    public BaseResponse<GoodsDetail> getOneGoods(long id){
        GoodsDetail goodsDetail = goodService.findById(id);
        BaseResponse<GoodsDetail> res = BaseResponse.succ();
        res.setData(goodsDetail);
        return res;
    }
    @ResponseBody
    @PostMapping("/good/getComment")
    public BaseResponse<List<Comments>> getComments(long goodsId,int commentType){
        List<Comments> comments = commentService.getByGoodsIdAndType(goodsId,commentType);
        BaseResponse<List<Comments>> response = BaseResponse.succ();
        response.setData(comments);
        return response;
    }
    @ResponseBody
    @PostMapping("/good/offShelf")
    public BaseResponse offShelf(long goodsId){
        try {
            goodService.deleteGoods(goodsId);
            return BaseResponse.succ();
        } catch (Exception e) {
            log.error("????????????????????????...",e);
            BaseResponse fail = BaseResponse.fail();
            fail.setMsg("????????????????????????");
            return fail;
        }
    }
    @ResponseBody
    @PostMapping("/good/delSeekInfo")
    public BaseResponse delSeekInfo(long id){
        try {
            wantRecordService.delWantRecord(id);
            return BaseResponse.succ();
        }catch (AuthorityException e) {
            log.error("??????????????????????????????...",e);
            BaseResponse fail = BaseResponse.fail();
            fail.setMsg(e.getLocalizedMessage());
            return fail;
        }
    }
    @ResponseBody
    @PostMapping("/comment/addComment")
    public BaseResponse addComment(long goodsId, String commentContent, int score){
        commentService.addComment(goodsId,commentContent,score);
        return BaseResponse.succ();
    }

}
