package com.chengzi.redis.controller;


import com.chengzi.redis.tools.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;

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
     * @param token
     * @return  1 已经成功登录  0 未登录
     */
    @GetMapping("/isLogin")
    public boolean isLogin(@RequestParam String token){
       return redisUtil.containKey(token);
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
           return redisUtil.select(key).toString();
       }
       try{
            redisUtil.insert(token,userName,exp);
            return token;
       }catch (Exception e){
           return "-1";
       }
    }
    /**
     * 清除登录凭证
     */
    @GetMapping("/clearLoginToken")
    public void clearLoginToken(@RequestParam String token)
    {
        redisUtil.delete(token);
    }

}
