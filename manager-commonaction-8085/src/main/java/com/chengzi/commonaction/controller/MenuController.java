package com.chengzi.commonaction.controller;

import com.chengzi.commonaction.service.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private DataBaseService menuService;
    @GetMapping("/queryAll")
    @ResponseBody
    public String queryAll(){
        return menuService.queryAll();
    }
}
