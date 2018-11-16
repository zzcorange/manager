package com.chengzi.entity;

import java.lang.reflect.Field;

public class BaseEntity {
    public String toString(){
        StringBuilder sb = new StringBuilder();
        Field[] fields = getClass().getDeclaredFields();
        sb.append("{");
        for(Field field:fields){
            sb.append(field.getName());
            sb.append(":");
            try{
                sb.append(getClass().getMethod("get"+getFirstUp(field.getName())).invoke(this));
            }catch (Exception e){

            }
            sb.append(",");

        }
        if(sb.charAt(sb.length()-1)==',')
            sb.deleteCharAt(sb.length()-1);
        sb.append("}");
        return sb.toString();
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
