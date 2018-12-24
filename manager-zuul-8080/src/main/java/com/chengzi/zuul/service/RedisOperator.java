package com.chengzi.zuul.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("manager-redis")
public interface RedisOperator {
    @GetMapping("/isLogin")
    boolean isLogin( @RequestParam("token") String token);
}
