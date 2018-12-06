package com.chengzi.commonaction.controller;

import com.chengzi.commonaction.service.DataBaseService;
import com.chengzi.commonaction.service.UserRedisService;
import com.chengzi.entity.User;
import com.chengzi.enums.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class LoginController {
    @Autowired
    private DataBaseService userService;
    @Autowired
    private UserRedisService userRedisService;
    @PostMapping("/login.do")
    @ResponseBody
    public String login(@RequestParam String userName,@RequestParam String password){
        User user = userService.selectOne(userName);
        if(user==null){
            return Code.LOGIN_FAIL_NOTUSER.toString();
        }
        System.out.println("parampassword="+password+";;;;password"+user.getPassword());
        if(!password.equals(user.getPassword())){
            return Code.LOGIN_FAIL_PASSWORD_ERROR.toString();
        }
        /**
         * 加载token到redis
         */
        String uuid = userRedisService.login(userName, UUID.randomUUID().toString());
        return  !"-1".equals(uuid)?Code.SUCCESS.toString(uuid):Code.LOGIN_FAIL.toString();
    }
    @GetMapping("/test")
    @ResponseBody
    public String test(){
        System.out.println("here");
        return "success";
    }

}
