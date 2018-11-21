package com.chengzi.commonaction.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/common")
public class LoginController {
    @GetMapping("/login")
    @ResponseBody
    public String login(@RequestParam String userName,@RequestParam String password){
        return "success";
    }

}
