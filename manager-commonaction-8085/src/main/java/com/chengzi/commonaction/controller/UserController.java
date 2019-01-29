package com.chengzi.commonaction.controller;

import com.chengzi.commonaction.service.DataBaseService;
import com.chengzi.commonaction.service.UserRedisService;
import com.chengzi.entity.User;
import com.chengzi.enums.Code;
import com.chengzi.tools.NumberUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private DataBaseService dataBaseService;
    @Autowired
    private UserRedisService redisService;
    @GetMapping("queryAll")
    public String queryAll(@RequestParam(required = false,defaultValue = "1") String page,@RequestParam(required = false,defaultValue = "10") String rows){
        int pageNum = NumberUtils.parsePositiveInt(page,1);
        int rowsNum = NumberUtils.parsePositiveInt(rows,10);
       return  dataBaseService.getAll(pageNum,rowsNum);
    }
    @PostMapping(value="/insert",params = {})
    public String insert(@Valid User user){
        System.out.println("接收参数"+user.toString());
        System.out.println("返回值："+Code.SUCCESS.toString());
        return dataBaseService.insertUser(user)>0? Code.SUCCESS.toString():Code.FAIL.toString();
    }
    @GetMapping("/query")
    public String query(@RequestParam int id){
        User user = dataBaseService.queryUser(id);
        return user==null?Code.EMPTY.toString():user.toString();
    }
    @PostMapping("/modify")
    public String update(@RequestBody User user, HttpSession session){
        if(0==user.getId()){
                return Code.MISSPARAMETER.toErrorString("id不能为空");
        }
        //更新用户数据
        int total = dataBaseService.updateUser(user);
        if(total>0){//更新成功之后清除用户的登录数据
            System.out.println("清除登录凭证");
            redisService.clearLoginToken(session.getAttribute("token").toString());//清除redis中的登录凭证
            session.removeAttribute("token"); //清除session中的登录凭证
        }
        return total>0? Code.SUCCESS.toString():Code.FAIL.toString();
    }
}
