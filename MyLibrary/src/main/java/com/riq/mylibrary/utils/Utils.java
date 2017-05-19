package com.riq.mylibrary.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by riq on 2017/5/19.
 */

public class Utils {
    /*
     * TODO 字符串工具
     * 1.匹配手机号码
     * 2.把字符串转换为md5加密形式
     * 3.去掉text中的所有空格、换行等／替换text中的所有空格、换行等为newStr
     * 4.判断文本是否为自然数
     * 5.判断文本是否为字母
     * 6.判断文本是否为汉字(只能判断一个字)     *
     */

    /**
     * TODO 匹配手机号码
     * 移动号段：139 138 137 136 135 134 147 150 151 152 157 158 159 178 182 183 184 187 188
     * 联通号段：130 131 132 155 156 185 186 145 176
     * 电信号段：133 153 177 173 180 181 189
     * 虚拟运营商号段：170 171
     * 130 131 132 133 134 135 136 137 138 139 145 147 150 151 152 153 155 156
     * 157 158 159 170 171 173 176 177 178 180 181 182 183 184 185 186 187 188 189
     *
     * @param phone 手机号
     * @return 是否是正确的手机号
     */
    public static boolean isPhoneNumber(String phone) {
        Pattern p = Pattern.compile("^1((3\\d)|(4[57])|(5[^4,\\D])|(7[013678])|(8\\d))\\d{8}$");
        Matcher m = p.matcher(phone.trim());
        return m.matches();
    }

    /**
     * TODO 把字符串转换为md5加密形式
     *
     * @param content 需要加密的内容
     * @return 已加密的内容文本
     */
    public static String getMD5(String content) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(content.getBytes());
            StringBuilder builder = new StringBuilder();
            for (byte b : digest.digest()) {
                builder.append(Integer.toHexString((b >> 4) & 0xf));
                builder.append(Integer.toHexString(b & 0xf));
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * TODO 去掉text中的所有空格、换行等
     * 包括空格、制表符、换页符等（每一个空白字符均会被替换）
     *
     * @param text 需要被替换的字符串
     * @return 如, replaceAllChar("a ","w")    ----> 返回"aw"
     */
    public static String replaceAllChar(String text) {
        return text.replaceAll("[\\s+]", "");
    }

    /**
     * TODO 替换text中的所有空格、换行等为newStr
     * 包括空格、制表符、换页符等（每一个空白字符均会被替换）
     *
     * @param text   需要被替换的字符串
     * @param newStr 需要将空白字符替换为的字符
     * @return 如, replaceAllChar("a ","w")    ----> 返回"aw"
     */
    public static String replaceAllChar(String text, String newStr) {
        return text.replaceAll("[\\s+]", newStr);
    }

    /**
     * TODO 判断文本是否为自然数
     *
     * @param text 文本
     * @return
     */
    public static boolean isNaturalNumber(String text) {
        if (text.equals("")) {
            return false;
        } else
            return Pattern.compile("[0-9]*").matcher(text).matches();
    }

    /**
     * TODO 判断文本是否为字母
     *
     * @param text 文本
     * @return
     */
    public static boolean isLetter(String text) {
        return Pattern.compile("[a-zA-Z]").matcher(text).matches();
    }

    /**
     * TODO 判断文本是否为汉字(只能判断一个字)
     *
     * @param text 文本
     * @return
     */
    public static boolean isHanzi(String text) {
        return Pattern.compile("[\u4e00-\u9fa5]").matcher(text).matches();
    }

    /*
     * TODO 时间工具
     * 1.时间戳或时间文本转日期字符串／时间戳转指定格式日期字符串
     * 2.比较指定pattern日期大小／比较时间戳 或 yyyy-MM-dd hh:mm:ss／
     *
     */

    /**
     * TODO 时间戳或时间文本转日期字符串
     *
     * @param timeOrTimestamps 时间戳
     * @return 1970-01-01 08:00:00
     * 区分是时间戳格式还是时间格式1970-01-01 08:00:00.0
     */
    public static String formatTimestamp(String timeOrTimestamps) {
        if (Utils.isNaturalNumber(timeOrTimestamps)) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return format.format(new Date(Long.parseLong(timeOrTimestamps)));
        } else if (timeOrTimestamps.contains(".")) {
            return timeOrTimestamps.substring(0, timeOrTimestamps.lastIndexOf("."));
        }
        return null;
    }

    /**
     * TODO 时间戳转指定格式日期字符串
     *
     * @param timestamp 时间戳（毫秒）
     * @param pattern   格式：yyyy-MM-dd/yyyymmdd/yyyy-MM-dd hh:mm:ss...
     * @return 时间字符串 格式： yyyy-MM-dd/yyyyMMdd...
     */
    public static String formatTimestamp(String timestamp, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(new Date(Long.parseLong(timestamp)));
    }


    /**
     * TODO 比较指定pattern日期大小
     * date1 - date2
     *
     * @param date1   日期1
     * @param date2   日期2
     * @param pattern 格式，格式yyyy-MM-dd hh:mm:ss ...
     * @return 1，大于；    0，等于；   -1，小于；  -2,比较失败.
     */
    public static int compareDate(String date1, String date2, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        try {
            Date dt1 = df.parse(date1);
            Date dt2 = df.parse(date2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;   //大于
            } else if (dt1.getTime() == dt2.getTime()) {
                return 0;   //等于
            } else {
                return -1;  //小于
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -99999;  //例如日期为空，或者日期格式不对
        }
    }

    /**
     * TODO 比较时间戳 或 yyyy-MM-dd hh:mm:ss
     *
     * @param date1 时间戳1
     * @param date2 时间戳2
     * @return 时间戳1 - 时间戳2
     */
    public static int compareDate(String date1, String date2) {
        //比较时间戳
        if (Utils.isNaturalNumber(date1) && Utils.isNaturalNumber(date2)) {
            long difference = (Long.parseLong(date1) - Long.parseLong(date2));
            if (difference > 0) {
                return 1;   //大于
            } else if (difference == 0) {
                return 0;   //等于
            } else {
                return -1;  //小于
            }
        }
        //比较日期 2017-05-19 10:24:55
        if (date1.contains(":") && date2.contains(":")) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            try {
                Date dt1 = df.parse(date1);
                Date dt2 = df.parse(date2);
                if (dt1.getTime() > dt2.getTime()) {
                    return 1;   //大于
                } else if (dt1.getTime() == dt2.getTime()) {
                    return 0;   //等于
                } else {
                    return -1;  //小于
                }
            } catch (Exception e) {
                e.printStackTrace();
                return -99999;
            }
        } else {
            return -99999;
        }
    }

    /*
     * TODO 数据工具
     */

    /**
     * TODO ArrayList去除重复
     *
     * @param list
     * @return
     */
    public static ArrayList removeSame(ArrayList list) {
        if (null == list) {
            return null;
        }
        HashSet h = new HashSet(list);
        list.clear();
        list.addAll(h);
        return list;
    }

    /*
     * TODO 网络工具
     */

    /**
     * TODO 检查是否有网络
     *
     * @param context this
     * @return true有网, false没有网
     */
    public static boolean checkNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (NetworkInfo anInfo : info) {
                    if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
                        if (anInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                            return true;
                        } else if (anInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

}