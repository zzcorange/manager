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
		if(ex instanceof  org.springframework.web.bind.MissingServletRequestParameterException){
			return deal((org.springframework.web.bind.MissingServletRequestParameterException)ex);
		}
		if(ex instanceof  org.springframework.web.HttpMediaTypeNotSupportedException){
			return deal((org.springframework.web.HttpMediaTypeNotSupportedException)ex);
		}
		return Code.ERROR_500.toString();
	}
	private String deal(org.springframework.validation.BindException ex){
		StringBuilder sb = new StringBuilder();
		ex.getAllErrors().forEach((error)->{
			sb.append(":");
			sb.append(error.getDefaultMessage());
			sb.append(";");
		});
		return Code.DATAVAVALIAD.toErrorString(sb.toString());
	}
	private String deal(org.springframework.web.bind.MissingServletRequestParameterException ex){
		   return Code.MISSPARAMETER.toErrorString("参数【"+ex.getParameterName()+"】缺失");
	}
	private String deal(org.springframework.web.HttpMediaTypeNotSupportedException ex){
		return Code.HTTPMEDIATYPENOTSUPPORT.toString();
	}
} 