package com.chengzi.zuul;

import org.junit.Test;

import java.util.regex.Pattern;

public class test {
    @Test
    public void test(){
        String pattern = ".*/login.html";
        String url="/web/images/log_04.gif";
        System.out.println(url.matches(pattern));
    }
}
