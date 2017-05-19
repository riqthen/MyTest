package com.riq.mylibrary.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 锐 on 2017/5/14.
 */

public class DateUtils {
    /**
     * TODO 时间戳转日期字符串
     *
     * @param timeOrTimestamps 时间戳
     * @return 1970-01-01 08:00:00
     * 区分是时间戳格式还是时间格式1970-01-01 08:00:00.0
     */
    public static String formatTimestamp(String timeOrTimestamps) {
        if (StringUtils.isNaturalNumber(timeOrTimestamps)) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return format.format(new Date(Long.parseLong(timeOrTimestamps)));
        } else if (timeOrTimestamps.contains(".")) {
            return timeOrTimestamps.substring(0, timeOrTimestamps.lastIndexOf("."));
        }
        return null;
    }

    /**
     * @param timestamp 时间戳（毫秒）
     * @param pattern   格式：yyyy-MM-dd/yyyymmdd/yyyy-MM-dd HH:mm:ss...
     * @return 时间字符串 格式： yyyy-MM-dd/yyyyMMdd...
     */
    public static String formatTimestamp(String timestamp, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(new Date(Long.parseLong(timestamp)));
    }



    /**
     * TODO 比较日期大小
     * (只能日期格式) date1 - date2
     *
     * @param date1 日期1，格式yyyy-mm-dd
     * @param date2 日期2，格式yyyy-mm-dd
     * @return 1，大于；    0，等于；   -1，小于；  -2,比较失败.
     */
    public static int compareDate(String date1, String date2) {
        SimpleDateFormat df = new SimpleDateFormat("yy-MM-dd");
        try {
            Date dt1 = df.parse(date1);
            Date dt2 = df.parse(date2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() == dt2.getTime()) {
                return 0;
            } else {
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -2;
        }
    }
}
