package com.chengzi.zuul.service;

import com.chengzi.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("manager-database")
public interface UserServiceForDataBase {
    @GetMapping("/database/user/getOne")
    User getUser(String username);
}
