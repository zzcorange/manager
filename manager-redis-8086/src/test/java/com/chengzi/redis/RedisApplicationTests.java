package com.chengzi.redis;

import com.chengzi.redis.tools.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisApplicationTests {
    @Autowired
    private RedisUtil redisUtil ;
    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void contextLoads() {
        redisUtil.insert("login-key-tomer","2a0ce447-fcbf-4f51-8ac7-4c5818330d70",2000);
        System.out.println("the answer is : "+redisUtil.select("login-key-tomer"));
    }
    @Test
    public void select() {
        System.out.println("the answer is : "+redisUtil.select("login-key-tomer"));
    }
    @Test
    public void testInsertKey(){
        for(int i=0;i<100;i++)
            redisUtil.insert("keytest-"+i,i,2000);
    }
    @Test
    public void testInsertKeys(){
        ListOperations<String,Object> listOperations =  redisTemplate.opsForList();
        for(int i=0;i<100;i++)
            listOperations.leftPush("keytest1",i);
    }
    @Test
    public void testSelectkeys(){
        ListOperations<String,Object> listOperations =  redisTemplate.opsForList();
        System.out.println(listOperations.range("keytest1",0,listOperations.size("keytest1")));;
    }
    @Test
    public void testContainsKeys(){
        ListOperations<String,Object> listOperations =  redisTemplate.opsForList();
    }
    @Test
    public void testSetInsert(){
        SetOperations<String,Object> setOperations = redisTemplate.opsForSet();
        for(int i=0;i<100;i++)
            setOperations.add("testset",i);
    }
    @Test
    public void testSelectSets(){
        SetOperations<String,Object> setOperations = redisTemplate.opsForSet();
        System.out.println(setOperations.members("testset"));
    }

}
