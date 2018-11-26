package com.chengzi.redis;

import com.chengzi.redis.tools.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisApplicationTests {
    @Autowired
    private RedisUtil redisUtil ;
    @Test
    public void contextLoads() {
        redisUtil.insert("login-key-tomer","2a0ce447-fcbf-4f51-8ac7-4c5818330d70",2000);
        System.out.println("the answer is : "+redisUtil.select("login-key-tomer"));
    }
    @Test
    public void select() {
        System.out.println("the answer is : "+redisUtil.select("login-key-tomer"));
    }

}
