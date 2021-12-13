package com.campus.nicecampus.service;

import com.campus.nicecampus.base.model.GoodsDetail;
import com.campus.nicecampus.req.AddGoodsReq;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

public interface GoodService {
    /**
     * @param req
     */
    void addGood(AddGoodsReq req, MultipartFile file) throws IOException;
    GoodsDetail getGoods(long id);
}
