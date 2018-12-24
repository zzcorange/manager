package com.chengzi.database.controller;

import com.chengzi.database.service.MenuService;
import com.chengzi.entity.auth.MenuAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
    @RequestMapping("/queryAllForZTree")
    @ResponseBody
    public String queryAllForZTree(){
        return menuService.queryAllForZTree();
    }
    @PostMapping("/modify")
    public boolean modify(@RequestBody List<MenuAction> list){
        menuService.modify(list);
        return true;
    }
}
