package com.honger.expo.utils;

import com.honger.expo.myexception.MyDateFormatException;

import java.util.Calendar;

/**
 * Created by chenjian on 2018/4/15.
 */
public class DateTransform {
    //Ym的格式为YYYY/MM
    public static String getMaxYmdFromYM(String ym){
        if(!ym.contains("/"))
            throw new MyDateFormatException("时间格式错误，正确格式YYYY/MM");
        String[] split = ym.split("/");
        Integer year = Integer.valueOf(split[0]);
        Integer month = Integer.valueOf(split[1]);

        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        Integer maxDate = a.get(Calendar.DATE);
        return year+"-"+month+"-"+maxDate.toString();
    }

    public static String getMinYmdFromYM(String ym){
        if(!ym.contains("/"))
            throw new MyDateFormatException("时间格式错误，正确格式YYYY/MM");

        String[] split = ym.split("/");
        Integer year = Integer.valueOf(split[0]);
        Integer month = Integer.valueOf(split[1]);

        return year+"-"+month+"-"+"1";
    }
}
