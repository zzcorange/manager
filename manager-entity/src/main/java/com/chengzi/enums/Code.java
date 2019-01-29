package com.chengzi.enums;

import org.json.JSONObject;

public enum Code {
    LOGIN_FAIL_NOTUSER("-1","用户不存在"),
    LOGIN_FAIL_PASSWORD_ERROR("-1","密码错误"),
    LOGIN_FAIL("-1","登录失败"),
    ERROR_500("-2","系统内部错误"),
    DATAVAVALIAD("-3","参数鉴权失败"),
    FAIL("-4","失败，请重试"),
    EMPTY("-5","数据为空"),
    MISSPARAMETER("-6","缺失参数"),
    HTTPMEDIATYPENOTSUPPORT("-7","请求的type不支持，请检查"),
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
    public String toErrorString(String errorMessage){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",code);
        jsonObject.put("message",message);
        jsonObject.put("errorMessage",errorMessage);
        return jsonObject.toString();
    }
}
