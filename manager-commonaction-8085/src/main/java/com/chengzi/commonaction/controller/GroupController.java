package com.chengzi.commonaction.controller;

import com.chengzi.commonaction.service.DataBaseService;
import com.chengzi.entity.auth.Group;
import com.chengzi.enums.Code;
import com.chengzi.tools.NumberUtils;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {
    @Autowired
    private DataBaseService dataBaseService;
    @GetMapping("/queryAll")
    public String queryAll(@RequestParam(required = false,defaultValue = "1") String page,@RequestParam(required = false,defaultValue = "10") String rows){
        int pageNum = NumberUtils.parsePositiveInt(page,1);
        int rowsNum = NumberUtils.parsePositiveInt(rows,10);
        return dataBaseService.queryAllGroup(pageNum,rowsNum);
    }
    @GetMapping("/queryAllMenu")
    public String queryAllMenu(@RequestParam(name = "id",required =  false) String id){
        if(null==id||id.length()==0)
            return dataBaseService.queryAllMenuForGroup();
        else
           return dataBaseService.queryAllMenuForGroupById(id);
    }
    @PostMapping("/addGroup")
    public String addGroup(@RequestBody Group group){
        System.out.println("权限组名称是："+group.getName());
       return  dataBaseService.addGroup(group)>0? Code.SUCCESS.toString():Code.FAIL.toString();
    }
    @GetMapping("/queryOne")
    public String queryOne(@RequestParam String id){
        return dataBaseService.queryOneGroup(id);
    }
    @PostMapping("/modify")
    public String modify(@RequestBody Group group){
        return Code.SUCCESS.toString();
    }
}
