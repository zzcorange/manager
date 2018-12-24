package com.chengzi.tools.exception;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

@ControllerAdvice
public class AllExceptionHandler{
	@ExceptionHandler(Exception.class) //拦截所有运行时的全局异常
	@ResponseBody //返回 JOSN
	public HashMap<String, Object> errorHandler(Exception ex){
        System.out.println(11);
		HashMap<String, Object> map = new HashMap<>();
		map.put("500", "空指针异常");
		map.put("404", "地址错误");
		return map;
	}

} 