package com.chengzi.commonaction.controller;

import com.chengzi.commonaction.service.DataBaseService;
import com.chengzi.entity.User;
import com.chengzi.enums.Code;
import com.chengzi.tools.NumberUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private DataBaseService dataBaseService;
    @GetMapping("queryAll")
    public String queryAll(@RequestParam(required = false,defaultValue = "1") String page,@RequestParam(required = false,defaultValue = "10") String rows){
        int pageNum = NumberUtils.parsePositiveInt(page,1);
        int rowsNum = NumberUtils.parsePositiveInt(rows,10);
       return  dataBaseService.getAll(pageNum,rowsNum);
    }
    @PostMapping(value="/insert",params = {})
    public String insert(@Valid User user){
        return dataBaseService.insertUser(user)>0? Code.SUCCESS.toString():Code.FAIL.toString();
    }
}
