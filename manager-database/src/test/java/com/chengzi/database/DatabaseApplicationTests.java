package com.chengzi.database;

import com.chengzi.database.dao.MenuMapper;
import com.chengzi.database.dao.UserMapper;
import com.chengzi.entity.User;
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
    @Test
    public void contextLoads() {
    }
    @Test
    public void testUserMapper(){
        userMapper.selectAll().forEach((user)->{
            System.out.println(user);
        });
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
        menu.setParent_id(1);
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

}
