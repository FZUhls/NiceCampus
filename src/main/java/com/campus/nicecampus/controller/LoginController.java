package com.campus.nicecampus.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class LoginController extends BaseController{
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/failure")
    public String failure(Model model){
        model.addAttribute("flag",0);
        model.addAttribute("errMsg","密码错误");
        return "login";
    }
}
