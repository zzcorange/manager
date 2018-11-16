package com.chengzi.web;

import com.chengzi.entity.User;
import com.chengzi.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "com")
@RestController
public class WebApplication {
    @Autowired
    private UserService userService;
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

    @RequestMapping("/test")
    public String getData(){
        StringBuilder sb = new StringBuilder();
        userService.getAll().forEach((user)->{
            if(user!=null)
            sb.append(user.toString());
        });
        return sb.toString();
    }
    @RequestMapping("/insert")
    public String insert(@RequestParam String username,@RequestParam String password){
        User user = new User();
        user.setPassword(password);
        user.setUsername(username);
        System.out.println(user.toString());
       return  ""+ userService.insert(user);
    }
}
