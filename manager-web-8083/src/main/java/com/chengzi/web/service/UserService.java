package com.chengzi.web.service;

import com.chengzi.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient("MANAGER-DATABASE")
public interface UserService {
    @GetMapping("/database/user/getAll")
    List<User> getAll();
    @PostMapping("/database/user/insert")
    int insert(User user);
}
