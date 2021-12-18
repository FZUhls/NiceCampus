package com.campus.nicecampus.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campus.nicecampus.base.exception.AuthorityException;
import com.campus.nicecampus.base.model.GoodsDetail;
import com.campus.nicecampus.req.AddGoodsReq;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.AuthenticationException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface GoodService {
    /**
     * @param req
     */
    void addGood(AddGoodsReq req, MultipartFile file) throws IOException;
    GoodsDetail getGoods(long id);
    Page<GoodsDetail> findPages(long pageNo, String type);

    /**
     * @param userId 用户id
     * @return 商品列表
     */
    List<GoodsDetail> getAllGoodsByUserId(long userId);
    void deleteGoods(long goodsId) throws AuthorityException;
    GoodsDetail findById(long id);
}
