package com.chengzi.commonaction.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("manager-redis")
public interface UserRedisService {
    @PostMapping("/login")
    String login(@RequestParam("userName")  String userName, @RequestParam("token") String token);
}
