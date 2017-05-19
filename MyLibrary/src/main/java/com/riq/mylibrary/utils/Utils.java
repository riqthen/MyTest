package com.riq.mylibrary.utils;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.SparseIntArray;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.Context.AUDIO_SERVICE;

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
     * @return true有网, false没有网
     */
    public static class NetUtil {
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

    /**
     * Created by riq on 2017/5/10.
     * TODO 随机数工具类
     */

    public static class RandomUtils {

        private static final String NUMBERS_AND_LETTERS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        private static final String NUMBERS = "0123456789";
        private static final String LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        private static final String UPPER_CASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        private static final String LOWER_CASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";

        private RandomUtils() {
            throw new AssertionError();
        }

        /**
         * TODO: 随机获取length长度个字符
         * 数字,大写字母,小写字母
         *
         * @param length length
         * @return RandomUtils
         */
        public static String getRandomNumbersAndLetters(int length) {
            return getRandom(NUMBERS_AND_LETTERS, length);
        }

        /**
         * TODO: 随机获取length长度个 数字
         *
         * @param length 字符长度
         * @return RandomUtils
         */
        public static String getRandomNumbers(int length) {
            return getRandom(NUMBERS, length);
        }

        /**
         * TODO: 随机获取length长度个 字母（无论大小写）
         *
         * @param length length
         * @return RandomUtils
         */
        public static String getRandomLetters(int length) {
            return getRandom(LETTERS, length);
        }

        /**
         * TODO: 随机获取length长度个 大写字母
         *
         * @param length length
         * @return ADSFY
         */
        public static String getRandomUpperCaseLetters(int length) {
            return getRandom(UPPER_CASE_LETTERS, length);
        }

        /**
         * TODO: 随机获取length长度个 小写字母
         *
         * @param length length
         * @return fdsfs
         */
        public static String getRandomLowerCaseLetters(int length) {
            return getRandom(LOWER_CASE_LETTERS, length);
        }

        /**
         * TODO: 获取随机自然数
         *
         * @param max 接收的数值
         * @return 返回一个随机的数字[0, max)
         */
        public static int getRandom(int max) {
            return getRandom(0, max);
        }

        /**
         * TODO: 在[min, max)范围内获取随机整数
         *
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
         * get a fixed-length random string, its a mixture of chars in source
         *
         * @param source source
         * @param length length
         * @return get a fixed-length random string, its a mixture of chars in source
         */
        public static String getRandom(String source, int length) {
            return TextUtils.isEmpty(source) ? null : getRandom(source.toCharArray(), length);
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
        public static int[] shuffle(int[] intArray, boolean includeChildArray) {
            if (intArray == null) {
                return null;
            }
            if (includeChildArray) {
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


    /**
     * TODO 播放音频工具
     * 使用方法：
     * 1. 加载音频池 SoundPlayUtils.getInstance().init(this,int[] raws);
     * 2. 播放某音频 SoundPlayUtils.getInstance().playSound(this, rawId)
     */

    public static class SoundPlayUtils {
        // TODO: 将所有音频放入raws数组中
        private static SoundPool soundPool;
        private static boolean soundPoolLoaded;
        private static SparseIntArray soundIds;

        public static SoundPlayUtils getInstance() {
            return new SoundPlayUtils();
        }

        public void init(final Context context, final int[] raws) {
            soundIds = new SparseIntArray();
            //音频池没有加载的话,则加载线程池
            if (!soundPoolLoaded) {
                soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0); // 同时播放的流的最大数量／流的类型，一般为STREAM_MUSIC／采样率转化质量，当前无效果，使用0作为默认值
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int raw : raws) {
                            //加载音频资源  priority参数目前没有效果，建议设置为1
                            int soundId = soundPool.load(context, raw, 1);  //记载成功将返回一个非0的soundID ，用于播放时指定特定的音频。
                            soundIds.put(raw, soundId);
                        }
                        soundPoolLoaded = true;
                    }
                }).start();
            }
        }

        /**
         * 播放音频
         *
         * @param context this
         * @param rawId   R.raw.
         */
        public void playSound(Context context, int rawId) {
            if (soundPoolLoaded) {
                AudioManager am = (AudioManager) context.getSystemService(AUDIO_SERVICE);
                float volume = (float) am.getStreamVolume(AudioManager.STREAM_MUSIC)
                        / am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
                Lcat.print(soundIds.get(rawId));
                soundPool.play(soundIds.get(rawId), volume, volume, 1, 0, 1);   //soundId,通过load方法获取／左声道音量比／右声道音量比／优先级,0为最小／
            }
        }

        /**
         * 播放音频
         *
         * @param context  this
         * @param rawId    R.raw.
         * @param priority 流的优先级，值越大优先级高，影响当同时播放数量超出了最大支持数时SoundPool对该流的处理；
         * @param loop     循环次数,负数表示无限循环,0表示播放1次,1表示播放2次,即循环1次
         * @param rate     播放速率[0.5, 2]
         */
        public void playSound(Context context, int rawId, int priority, int loop, float rate) {
            if (soundPoolLoaded) {
                AudioManager am = (AudioManager) context.getSystemService(AUDIO_SERVICE);
                float volume = (float) am.getStreamVolume(AudioManager.STREAM_MUSIC)
                        / am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
                Lcat.print(soundIds.get(rawId));
                soundPool.play(soundIds.get(rawId), volume, volume, priority, loop, rate);   //soundId,通过load方法获取／左声道音量比／右声道音量比／优先级,0为最小／
            }
        }

    }
}