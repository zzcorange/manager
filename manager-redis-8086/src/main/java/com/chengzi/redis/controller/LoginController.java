package com.chengzi.redis.controller;


import com.chengzi.redis.tools.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/redis")
@RestController
@PropertySource("classpath:redisProperties.properties")
public class LoginController {
    @Autowired
    private RedisUtil redisUtil;
    @Value("${key.login-prefix}")
    private String loginPrefix;
    @Value("${key.exptime}")
    private long exp;

    /**
     * 判断是否已经登录
     * @param userName
     * @param token
     * @return  1 已经成功登录  0 未登录
     */
    @GetMapping("/isLogin")
    public String isLogin(@RequestParam  String userName,@RequestParam String token){
        return token.equals(redisUtil.select(loginPrefix+userName))?"1":"0";
    }

    /**
     * 保存登录token
     * @param userName
     * @param token
     * @return  1 token已存在，拒绝更新 0 更新失败 1 更新成功
     */
    @PostMapping("/login")
    public String login(@RequestParam("userName") String userName,@RequestParam("token") String token){
        System.out.println("UserName="+userName+";token="+token);
       String key = loginPrefix+userName;
       if(redisUtil.isExists(key)){
           return "1";
       }
       try{
            redisUtil.insert(key,token,exp);
            return "1";
       }catch (Exception e){
           return "0";
       }
    }


}
