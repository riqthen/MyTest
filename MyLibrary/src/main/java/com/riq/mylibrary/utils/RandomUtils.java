package com.riq.mylibrary.utils;

import com.github.lazylibrary.util.StringUtils;

import java.util.Random;

/**
 * Created by riq on 2017/5/10.
 * 随机数工具类
 */

public class RandomUtils {

    public static final String NUMBERS_AND_LETTERS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String NUMBERS = "0123456789";
    public static final String LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String UPPER_CASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LOWER_CASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";

    private RandomUtils() {
        throw new AssertionError();
    }

    /**
     * 随机获取length长度个字符
     * 数字,大写字母,小写字母
     *
     * @param length length
     * @return RandomUtils
     */
    public static String getRandomNumbersAndLetters(int length) {
        return getRandom(NUMBERS_AND_LETTERS, length);
    }

    /**
     * 随机获取length长度个 数字
     *
     * @param length 字符长度
     * @return RandomUtils
     */
    public static String getRandomNumbers(int length) {
        return getRandom(NUMBERS, length);
    }

    /**
     * 随机获取length长度个 字母（无论大小写）
     *
     * @param length length
     * @return RandomUtils
     */
    public static String getRandomLetters(int length) {
        return getRandom(LETTERS, length);
    }

    /**
     * 随机获取length长度个 大写字母
     *
     * @param length length
     * @return ADSFY
     */
    public static String getRandomUpperCaseLetters(int length) {
        return getRandom(UPPER_CASE_LETTERS, length);
    }

    /**
     * 随机获取length长度个 小写字母
     *
     * @param length length
     * @return fdsfs
     */
    public static String getRandomLowerCaseLetters(int length) {
        return getRandom(LOWER_CASE_LETTERS, length);
    }

    /**
     * get a fixed-length random string, its a mixture of chars in source
     *
     * @param source source
     * @param length length
     * @return get a fixed-length random string, its a mixture of chars in source
     */
    public static String getRandom(String source, int length) {
        return StringUtils.isEmpty(source) ? null : getRandom(source.toCharArray(), length);
    }

    /**
     * sourceChar个字符,随机排列为一个长度为length的字符串
     *
     * @param sourceChar new char[]{'3','f','d'}
     * @param length     4
     * @return f3d3
     */
    public static String getRandom(char[] sourceChar, int length) {
        if (sourceChar == null || sourceChar.length == 0 || length < 0) {
            return null;
        }
        StringBuilder str = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            str.append(sourceChar[random.nextInt(sourceChar.length)]);
        }
        return str.toString();
    }


    /**
     * @param max 接收的数值
     * @return 返回一个随机的数值[0, max)
     */
    public static int getRandom(int max) {
        return getRandom(0, max);
    }


    /**
     * @param min 最小
     * @param max 最大
     * @return 返回一个范围的数值[min, max)
     */
    public static int getRandom(int min, int max) {
        if (min > max) {
            return 0;
        }
        if (min == max) {
            return min;
        }
        return min + new Random().nextInt(max - min);
    }

    /**
     * Shuffling algorithm, Randomly permutes the specified array using a default source of randomness
     *
     * @param objArray 数组
     * @return 从新的数组
     */
    public static boolean shuffle(Object[] objArray) {
        if (objArray == null) {
            return false;
        }
        return shuffle(objArray, getRandom(objArray.length));
    }

    /**
     * Shuffling algorithm, Randomly permutes the specified array
     *
     * @param objArray     数组
     * @param shuffleCount 洗的个数
     * @return 是否成功
     */
    public static boolean shuffle(Object[] objArray, int shuffleCount) {
        int length;
        if (objArray == null || shuffleCount < 0 || (length = objArray.length) < shuffleCount) {
            return false;
        }

        for (int i = 1; i <= shuffleCount; i++) {
            int random = getRandom(length - i);
            Object temp = objArray[length - i];
            objArray[length - i] = objArray[random];
            objArray[random] = temp;
        }
        return true;
    }

    /**
     * 将数组元素随机排列,个数为[o,intArray.length)
     *
     * @param intArray 数组
     * @return 洗牌之后
     */
    public static int[] shuffle(int[] intArray, boolean lengthStable) {
        if (intArray == null) {
            return null;
        }
        if (!lengthStable) {
            return shuffle(intArray, getRandom(intArray.length));
        } else {
            return shuffle(intArray, intArray.length);
        }
    }


    /**
     * Shuffling algorithm, Randomly permutes the specified int array
     *
     * @param intArray     数组
     * @param shuffleCount 范围
     * @return 新的数组
     */
    public static int[] shuffle(int[] intArray, int shuffleCount) {
        int length;
        if (intArray == null || shuffleCount < 0) {
            return new int[]{};
        }
        if ((length = intArray.length) < shuffleCount) {
            shuffleCount = length;
        }
        int[] out = new int[shuffleCount];

        for (int i = 1; i <= shuffleCount; i++) {
            int random = getRandom(length - i);
            out[i - 1] = intArray[random];
            int temp = intArray[length - i];
            intArray[length - i] = intArray[random];
            intArray[random] = temp;
        }
        return out;
    }
}