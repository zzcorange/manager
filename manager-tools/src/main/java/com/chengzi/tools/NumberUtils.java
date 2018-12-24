package com.chengzi.tools;

public class NumberUtils {
    public static int parsePositiveInt(String number,int defaultValue){
        try{
            int answer= Integer.valueOf(number);
            return answer>0?answer:defaultValue;

        }catch (Exception e){
            return defaultValue;
        }
    }

}
