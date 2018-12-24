package com.chengzi.commonaction.controller;

import com.chengzi.commonaction.service.DataBaseService;
import com.chengzi.entity.auth.Menu;
import com.chengzi.entity.auth.MenuAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/queryAllForZTree")
    @ResponseBody
    public String queryAllForZTree(){
        return menuService.queryAllForZTree();
    }
    @PostMapping("/save")
    public String save(@RequestBody List<MenuAction> list){
        StringBuilder sb = new StringBuilder();
        list.forEach((menuAction)->{
            sb.append(menuAction.toString());
            sb.append(";");
        });
       return sb.toString();
    }
    @RequestMapping(value="/modify",method = RequestMethod.POST)
    public String modify(@RequestBody List<MenuAction> list){
        System.out.println(list.size()+">>>>>>>>>>>>>>>>.");
        boolean mofidy = menuService.mofidy(list);
        return mofidy?"success":"fail";
    }
}
