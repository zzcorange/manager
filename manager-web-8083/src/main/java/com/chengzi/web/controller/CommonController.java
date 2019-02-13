package com.chengzi.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommonController {
    @GetMapping("/index")
    public String index(){
        System.out.println("come here");
        return "/index";
    }
    @PostMapping("/testccss")
    public String test(){
        System.out.println("testrs");
        return "redirect:/index.html";
    }
}
