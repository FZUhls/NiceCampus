package com.campus.nicecampus.controller;

import com.campus.nicecampus.base.model.Cart;
import com.campus.nicecampus.res.BaseResponse;
import com.campus.nicecampus.service.CartService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Data
public class CartController extends BaseController{
    @Autowired
    CartService cartService;

    @GetMapping("/myCart")
    public String myCart(Model model){
        model.addAttribute("user",getUser());
        return "cart";
    }

    @ResponseBody
    @PostMapping("/good/addToCart")
    public BaseResponse addToCart(long goodsId){
        cartService.addToCart(goodsId);
        return BaseResponse.succ();
    }

    @PostMapping("/cart/delAll")
    @ResponseBody
    public BaseResponse clearAll(){
        cartService.clearAll();
        return BaseResponse.succ();
    }
    @PostMapping("/cart/getCartRecord")
    @ResponseBody
    public BaseResponse<List<Cart>> getCartRecord(){
        List<Cart> cartList = cartService.getAllRecordByUserId();
        BaseResponse<List<Cart>> response = BaseResponse.succ();
        response.setData(cartList);
        return response;
    }
    @PostMapping("/cart/delOneCartRecord")
    @ResponseBody
    public BaseResponse deleteOneCart(long goodsId){
        cartService.deleteOneCart(goodsId);
        return BaseResponse.succ();
    }
}
