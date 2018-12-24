package com.chengzi.commonaction.exceptionHandler;
import com.chengzi.enums.Code;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@ControllerAdvice
public class AllExceptionHandler {
	@ExceptionHandler(Exception.class) //拦截所有运行时的全局异常
	@ResponseBody //返回 JOSN
	public String errorHandler(Exception ex){
		System.out.println(ex.getClass().getName());
		if(ex instanceof org.springframework.validation.BindException){
			return deal((org.springframework.validation.BindException)ex);
		}
		return Code.ERROR_500.toString();
	}
	public String deal(org.springframework.validation.BindException ex){
		StringBuilder sb = new StringBuilder();
		ex.getAllErrors().forEach((error)->{
			sb.append(":");
			sb.append(error.getDefaultMessage());
			sb.append(";");
		});
		return Code.DATAVAVALIAD.toErrorString(sb.toString());
	}
} 