package com.riq.mylibrary.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

/**
 * Created by 10903 on 2017/5/11.
 */

public class StringUtils {
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
     * TODO 替换文本中的所有空白字符
     * 包括空格、制表符、换页符等（每一个空白字符均会被替换）
     *
     * @param oldStr      需要被替换的字符串
     * @param replacement 需要将空白字符替换为的字符
     * @return 如, replaceAllChar("a  ","w q")    ----> 返回"aw qw q"
     */
    public static String replaceAllChar(String oldStr, String replacement) {
        return oldStr.replaceAll("[\\s+]", replacement);
    }

    /**
     * TODO 判断文本是否为非负整数
     *
     * @param text 文本
     * @return
     */
    public static boolean isNumber(String text) {
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

//    /**
//     * TODO 判断文本是否为汉字
//     *
//     * @param text 文本
//     * @return
//     */
//    public static boolean isHanzi(String text) {
//        return Pattern.compile(" [\u4e00-\u9fa5]").matcher(text).matches();
//    }

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
