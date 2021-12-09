package com.campus.nicecampus.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class GoodsController {
    @GetMapping("/goodList")
    public String goodList(){
        return "goodList";
    }
}
