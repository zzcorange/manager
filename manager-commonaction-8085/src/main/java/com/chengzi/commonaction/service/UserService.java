package com.chengzi.commonaction.service;


import com.chengzi.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("MANAGER-DATABASE")
public interface UserService {
    @GetMapping("/database/user/getOne")
    User selectOne(@RequestParam("userName")  String userName);
}