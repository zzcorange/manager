package com.chengzi.database;

import com.chengzi.database.dao.MenuMapper;
import com.chengzi.database.dao.UserMapper;
import com.chengzi.database.service.MenuService;
import com.chengzi.entity.User;
import com.chengzi.entity.auth.MenuAction;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import com.chengzi.entity.auth.Menu;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DatabaseApplicationTests {
    @Autowired
    private JdbcTemplate jdbcTemplate;
     @Autowired
     private UserMapper userMapper;
     @Autowired
     private MenuMapper menuMapper;
     @Autowired
     private MenuService menuService ;
    @Test
    public void contextLoads() {
    }
    @Test
    public void testUserMapper(){
//        userMapper.selectAll().forEach((user)->{
//            System.out.println(user);
//        });
    }
    @Test
    public void testInsert(){
        User user = new User();
        user.setUsername("1235");
        user.setPassword("43121");
        userMapper.insert(user);
    }
    @Test
    public void testInserMenu(){
        Menu menu = new Menu();
        menu.setParent_id("1");
        menu.setName("总菜单下属一级子菜单");
        menu.setUrl("/web/total/");
        menuMapper.insertMenu(menu);
    }
    @Test
    public void testSelectMenu(){
        List<Menu> list = menuMapper.queryAll();
        for(Menu menu:list){
            System.out.println(menu.toString());
        }

    }
    @Test
    public void testModifyMenu(){
        String data = "[{\"name\":\"菜单管理1\",\"url\":\"/web/auth/menu.html\",\"action\":\"modify\",\"parent_id\":\"1\",\"id\":\"2\"},{\"name\":\"用户管理1\",\"url\":\"/web/auth/user.html\",\"action\":\"modify\",\"parent_id\":\"1\",\"id\":\"3\"},{\"name\":\"权限组管理\",\"url\":\"/web/auth/role.html\",\"action\":\"delete\",\"parent_id\":\"1\",\"id\":\"4\"},{\"name\":\"增加1\",\"action\":\"add\",\"parent_id\":\"5\",\"id\":\"5_1\"}]";
        Gson gson = new Gson();
        List<MenuAction> list =  gson.fromJson(data, new TypeToken<List<MenuAction>>() {}.getType());
       list.forEach((menuAction)->{
           System.out.println(menuAction);
       });
    }

}
