package com.chengzi.database;

import com.chengzi.database.dao.UserMapper;
import com.chengzi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/database/user/getAll")
    public List<User> getAll(){
        return userMapper.selectAll();
    }
    @PostMapping("/database/user/insert")
    public int insert(@RequestBody  User user){
     return     userMapper.insert(user);
    }
}
