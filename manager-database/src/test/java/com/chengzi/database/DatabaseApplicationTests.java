package com.chengzi.database;

import com.chengzi.database.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DatabaseApplicationTests {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Test
    public void contextLoads() {
    }

    @Test
    public void testConnect(){
        User user = new User();
        user.setUserName("tomer");
        user.setPassword("123a456");
        String execSQL = "INSERT into user (username,password) values (?, ?)";

        // 2. 执行查询方法
        int result = jdbcTemplate.update(execSQL,
                new Object[]{user.getUserName(), user.getPassword()});
        System.out.println(result);

    }

}
