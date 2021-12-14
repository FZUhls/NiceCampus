package com.campus.nicecampus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController extends BaseController {
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("user",getUser());
        return "index";
    }
}
