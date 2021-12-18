package com.campus.nicecampus.service.impl;

import com.campus.nicecampus.base.mapper.CartMapper;
import com.campus.nicecampus.base.model.Cart;
import com.campus.nicecampus.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl extends BaseService implements CartService {
    @Autowired
    CartMapper cartMapper;
    @Override
    public void addToCart(long goodsId) {
        Cart cart = new Cart();
        cart.setGoodsId(goodsId);
        cart.setUserId(getUser().getId());
        cartMapper.insert(cart);
    }

    @Override
    public void clearAll() {
        long userId = getUser().getId();
        cartMapper.deleteByMap(Map.of("user_id",userId));
    }

    @Override
    public List<Cart> getAllRecordByUserId() {
        return cartMapper.selectByMap(Map.of("user_id",getUser().getId()));
    }

    @Override
    public void deleteOneCart(long goodsId) {
        cartMapper.deleteByMap(Map.of("user_id",getUser().getId(),"goods_id",goodsId));
    }

    @Override
    public void deleteOneGoodsFormAllCarts(long goodsId) {
        cartMapper.deleteByMap(Map.of("goods_id",goodsId));
    }
}
