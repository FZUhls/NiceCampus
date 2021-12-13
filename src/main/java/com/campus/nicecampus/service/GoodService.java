package com.campus.nicecampus.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campus.nicecampus.base.model.GoodsDetail;
import com.campus.nicecampus.req.AddGoodsReq;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface GoodService {
    /**
     * @param req
     */
    void addGood(AddGoodsReq req, MultipartFile file) throws IOException;
    GoodsDetail getGoods(long id);
    Page<GoodsDetail> findPages(long pageNo, String type);
}
