package com.campus.nicecampus.service;

import com.campus.nicecampus.base.model.Cart;

import java.util.List;

public interface CartService {
    void addToCart(long goodsId);
    void clearAll();
    List<Cart> getAllRecordByUserId();
    void deleteOneCart(long goodsId);
    void deleteOneGoodsFormAllCarts(long goodsId);
}
