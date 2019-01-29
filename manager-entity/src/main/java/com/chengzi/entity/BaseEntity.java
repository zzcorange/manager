package com.chengzi.entity;

import com.chengzi.entity.tools.DateFormatUtils;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Field;

public class BaseEntity implements Serializable {
    public String toString(){
        JSONObject jsonObject = new JSONObject();
        Field[] fields = getClass().getDeclaredFields();
        for(Field field:fields){
            try{
                Object o = getClass().getMethod("get"+getFirstUp(field.getName())).invoke(this);
                if(o instanceof  java.util.Date){
                   jsonObject.put(field.getName(),DateFormatUtils.toyyyyMMddHHmmss((java.util.Date)o));
                }else{
                    jsonObject.put(field.getName(),o.toString());
                }

            }catch (Exception e){

            }

        }
        return jsonObject.toString();
    }
    private String getFirstUp(String string){
        if(null==string||"".equals(string)||(string.charAt(0)>='A'&&string.charAt(0)<='Z')){
            return string;
        }
        char[] chars = string.toCharArray();
        chars[0]-=32;
        return new String(chars);
    }
}
