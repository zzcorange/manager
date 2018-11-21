package com.chengzi.enums;

import org.json.JSONObject;

public enum Code {
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
