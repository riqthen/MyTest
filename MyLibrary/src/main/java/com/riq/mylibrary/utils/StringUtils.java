package com.riq.mylibrary.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by riq on 2017/5/11.
 * 字符串工具
 */

public class StringUtils {
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
     * TODO 判断文本是否为非负整数
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

    /**
     * TODO 将文本转为 UTF-8
     *
     * @param str
     * @return
     */
    public static String toUtf8(String str) {
        String result = null;
        try {
            result = new String(str.getBytes("UTF-8"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
