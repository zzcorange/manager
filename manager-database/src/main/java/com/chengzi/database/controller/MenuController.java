package com.chengzi.database.controller;

import com.chengzi.database.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService ;
    @RequestMapping("/queryAll")
    @ResponseBody
    public String queryAll(){
        return menuService.queryAll();
    }
}
