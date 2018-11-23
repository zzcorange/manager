package com.chengzi.commonaction.controller;

import com.chengzi.commonaction.service.UserRedisService;
import com.chengzi.commonaction.service.UserService;
import com.chengzi.entity.User;
import com.chengzi.enums.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/common")
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRedisService userRedisService;
    @GetMapping("/login")
    @ResponseBody
    public String login(@RequestParam String userName,@RequestParam String password){
        User user = userService.selectOne(userName);
        if(user==null){
            return Code.LOGIN_FAIL_NOTUSER.toString();
        }
        if(password.equals(user.getPassword())){
            return Code.LOGIN_FAIL_PASSWORD_ERROR.toString();
        }
        /**
         * 加载token到redis
         */
        return  "1".equals(userRedisService.login(userName, UUID.randomUUID().toString()))?Code.SUCCESS.toString():Code.LOGIN_FAIL.toString();
    }

}
