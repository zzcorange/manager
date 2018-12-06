package com.chengzi.redis.controller;

import com.chengzi.redis.tools.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 鉴权相关
 */
@RestController
@RequestMapping("/auth")
@PropertySource("classpath:redisProperties.properties")
public class AuthController {
    @Autowired
    private RedisUtil redisUtil;
    @Value("${key.authurl-prefix}")
    private String authurlPrefix;
    @Value("${key.exptime}")
    private long exp;

    public boolean isAuth(String url,String username){
        return false;
    }

}
