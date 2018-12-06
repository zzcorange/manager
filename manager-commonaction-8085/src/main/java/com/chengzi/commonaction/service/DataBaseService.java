package com.chengzi.commonaction.service;

import com.chengzi.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.ws.rs.GET;

@FeignClient("manager-database")
public interface DataBaseService {
    @GetMapping("/database/user/getOne")
    User selectOne(@RequestParam("userName")  String userName);
    @GetMapping("/menu/queryAll")
    String queryAll();
}
