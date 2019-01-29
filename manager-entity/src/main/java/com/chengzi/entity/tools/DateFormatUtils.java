package com.chengzi.entity.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtils {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 日期转为 yyyy-mm-ss HH:mm:ss 格式
     * @param date
     * @return
     */
    public static String toyyyyMMddHHmmss(Date date){
        try{
            return simpleDateFormat.format(date);
        }catch (Exception e){
            return "-1";
        }
    }

}
