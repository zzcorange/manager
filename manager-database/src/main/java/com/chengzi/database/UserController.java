package com.chengzi.database;

import com.chengzi.database.dao.UserMapper;
import com.chengzi.entity.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/database/user/getAll")
    public String getAll(@RequestParam int page,@RequestParam int rows){
        Map<String,Integer> map = new HashMap<>();
        map.put("begin",(page-1)*rows);
        map.put("end",(page)*rows-1);
        List<User> list = userMapper.selectAll(map);
        JSONArray jsonArray = new JSONArray();
        list.forEach((user)->{
            jsonArray.put(user.toListJSONObject());
        });
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total",userMapper.count());
        jsonObject.put("rows",jsonArray);
        return jsonObject.toString();
    }
    @PostMapping("/database/user/insert")
    public int insert(@RequestBody  User user){
        try{
          userMapper.insert(user);}catch (Exception e){
            return 0;
        }
        return 1;
    }
    @GetMapping("/database/user/getOne")
    public User getOne(@RequestParam int id){
        return userMapper.selectOne(id);
    }
    @GetMapping("/database/user/selectOne")
    public User selectOne(@RequestParam String username){
        return userMapper.selectOneByUserName(username);
    }
    @PostMapping("/database/user/update")
    public int update(@RequestBody User user){
        return userMapper.update(user);
    }
}
