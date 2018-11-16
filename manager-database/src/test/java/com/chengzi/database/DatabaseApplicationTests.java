package com.chengzi.database;

import com.chengzi.database.dao.UserMapper;
import com.chengzi.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DatabaseApplicationTests {
    @Autowired
    private JdbcTemplate jdbcTemplate;
     @Autowired
     private UserMapper userMapper;
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


}
