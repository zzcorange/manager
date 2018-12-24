package com.chengzi.commonaction.service;

import com.chengzi.entity.User;
import com.chengzi.entity.auth.MenuAction;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.ws.rs.GET;
import java.util.List;

@FeignClient("manager-database")
public interface DataBaseService {
    @GetMapping("/database/user/getOne")
    User selectOne(@RequestParam("userName")  String userName);
    @GetMapping("/menu/queryAll")
    String queryAll();
    @GetMapping("/menu/queryAllForZTree")
    String queryAllForZTree();
    @PostMapping("/menu/modify")
    boolean mofidy(@RequestBody List<MenuAction> list);
    @GetMapping("/database/user/getAll")
    String getAll(@RequestParam("page") int page,@RequestParam("rows") int rows);
    @PostMapping("/database/user/insert")
    int insertUser(@RequestBody User user);

}
