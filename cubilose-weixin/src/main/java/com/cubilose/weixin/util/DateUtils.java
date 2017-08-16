package com.cubilose.weixin.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jianxin.wang on 2017/8/16.
 *
 * 时间工具
 */

public class DateUtils {

    private final static SimpleDateFormat dateFormat
            = new SimpleDateFormat("yyyy-hh-dd HH:mm:ss");

    public static Date parse(String timeString) {
        try {
            return dateFormat.parse(timeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    public static String format(Date date) {
        return dateFormat.format(date);
    }

}
