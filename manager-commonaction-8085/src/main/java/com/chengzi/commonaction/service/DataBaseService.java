package com.chengzi.commonaction.service;

import com.chengzi.entity.User;
import com.chengzi.entity.auth.Group;
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
    @GetMapping("/database/user/selectOne")
    User selectOne(@RequestParam("username")  String userName);
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
    @GetMapping("/database/user/getOne")
    User queryUser(@RequestParam("id") int id);
    @PostMapping("/database/user/update")
    int updateUser(@RequestBody User user);
    /**
     * --------------权限分组------------------
     */
    @PostMapping("/group/addGroup")
    int addGroup(@RequestBody Group group);
    @PostMapping("/group/modify")
    int modifyGroup(@RequestBody Group group);
    @GetMapping("/group/queryAll")
    String queryAllGroup(@RequestParam("page") int pageNum,@RequestParam("rows") int rowsNum);
    @GetMapping("/group/queryAllMenu")
    String queryAllMenuForGroup();
    @GetMapping("/group/queryAllMenuById")
    String queryAllMenuForGroupById(@RequestParam("id") String id);
    @GetMapping("/group/queryOne")
    String queryOneGroup(@RequestParam("id") String id);
}
