package com.chengzi.enums;

import org.json.JSONObject;

public enum Code {
    LOGIN_FAIL_NOTUSER("-1","用户不存在"),
    LOGIN_FAIL_PASSWORD_ERROR("-1","密码错误"),
    LOGIN_FAIL("-1","登录失败"),
    SUCCESS("1","");
    private String code;
    private String message;
    private Code(String code,String message){
        this.code = code;
        this.message = message;
    }
    public String toString(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",code);
        jsonObject.put("message",message);
        return jsonObject.toString();
    }
    public String toString(String message){
        this.message = message;
        return toString();
    }
}
