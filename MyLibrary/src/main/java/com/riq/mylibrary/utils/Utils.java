package com.riq.mylibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.Context.MODE_PRIVATE;
import static com.riq.mylibrary.utils.Utils.StringUtils.isNaturalNumber;

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
    public static class StringUtils {
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
        public static String toMD5(String content) {
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
    }

    /*
     * TODO 时间工具
     * 1.时间戳或时间文本转日期字符串／时间戳转指定格式日期字符串
     * 2.比较指定pattern日期大小／比较时间戳 或 yyyy-MM-dd hh:mm:ss／
     *
     */
    public static class DateUtils {
        /**
         * TODO 时间戳或时间文本转日期字符串
         *
         * @param timeOrTimestamps 时间戳
         * @return 1970-01-01 08:00:00
         * 区分是时间戳格式还是时间格式1970-01-01 08:00:00.0
         */
        public static String formatTimestamp(String timeOrTimestamps) {
            if (isNaturalNumber(timeOrTimestamps)) {
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
            if (isNaturalNumber(date1) && isNaturalNumber(date2)) {
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
    }

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
     * 文件工具
     * 读取Storage和网络图片用Glide
     * 1.保存图片到storage
     * 2.从storage读取图片
     * 3.保存文本到file中
     * 4.从file中读取文本
     * 5.保存图片到file中
     * 6.读取file中的图片
     */
    public static class FileUtils {
        /**
         * TODO：保存图片到手机
         *
         * @param path     保存在手机的路径 /storage/emulated/0/
         * @param filename 文件名（需要扩展名,png,jpg...）
         * @param bmp      bitmap
         */
        public static void saveImageToStorage(String path, String filename, Bitmap bmp) {
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(new File(path, filename));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
            try {
                assert fos != null;
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * TODO：从手机读取图片
         * 使用Glide
         *
         * @param path     文件路径
         * @param filename 文件名，需要扩展名
         * @return 图片
         */
        public static Bitmap getImageFromStorage(String path, String filename) {
            File file = new File(path);
            if (file.exists()) {
                return BitmapFactory.decodeFile(path + filename);
            } else {
                return null;
            }
        }

        /**
         * TODO：保存文本到data/data/包/file中
         *
         * @param context  this
         * @param filename 文件名
         * @param text     保存到txt中的文本
         */
        public static void saveTextToFile(Context context, String filename, String text) {
            FileOutputStream out;
            BufferedWriter writer = null;
            OutputStreamWriter osw;
            try {
                out = context.openFileOutput(filename, MODE_PRIVATE);
                osw = new OutputStreamWriter(out);
                writer = new BufferedWriter(osw);
                writer.write(text);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (writer != null) {
                        writer.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        /**
         * TODO：读取data/data/包/file中的文本
         *
         * @param context  this
         * @param filename 文件名，需要扩展名
         * @return 读取的文本
         */
        public static String getTextFromFile(Context context, String filename) {
            FileInputStream in = null;//只需传文件名
            try {
                in = context.openFileInput(filename);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();//输出到内存
            int len = 0;
            byte[] buf = new byte[1024];
            try {
                assert in != null;
                while ((len = in.read(buf)) != -1) {
                    outStream.write(buf, 0, len);//
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            byte[] content_byte = outStream.toByteArray();
            return new String(content_byte);
        }

        /**
         * TODO：保存图片到data/data/包/file中
         *
         * @param context  this
         * @param filename 文件名，需要扩展名
         * @param bitmap   图片bitmap
         */
        public static void saveImageToFile(Context context, String filename, Bitmap bitmap) {
            try {
                FileOutputStream fos = context.openFileOutput(filename, MODE_PRIVATE);
                //bitmap转文件对象
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * TODO：读取data/data/包/file中的图片
         *
         * @param context  this
         * @param filename 图片文件名 需要扩展名
         * @return bitmap
         */
        public static Bitmap getImageFromFile(Context context, String filename) {
            Bitmap bitmap = null;
            try {
                FileInputStream fis = context.openFileInput(filename);
                bitmap = BitmapFactory.decodeStream(fis);
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }
    }

    /*
       * TODO 检查是否有网络
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

    /*
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

    /*
     * TODO APP相关
     * 1.获取程序版本名
     * 2.获取程序版本号
     * 3.获取屏幕宽度
     * 4.获取屏幕高度
     */
    public static class AppUtils {
        /**
         * TODO 获取程序版本名
         */
        public static String getVersionName(Context context) {
            String versionName = "";
            try {
                PackageManager manager = context.getPackageManager();
                PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
                versionName = info.versionName;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return versionName;
        }

        /**
         * TODO 获取程序版本号
         */
        public static int getVersionCode(Context context) {
            int versionCode = 0;
            try {
                PackageManager manager = context.getPackageManager();
                PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
                versionCode = info.versionCode;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return versionCode;
        }

        /**
         * TODO 获取屏幕宽度
         * 竖屏 1806   横屏 1080
         **/
        public static int getScreenWidth(Context context) {
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            return wm.getDefaultDisplay().getWidth();
        }

        /**
         * TODO 获取屏幕高度
         * 竖屏 1080   横屏 1806（不包含虚拟按键的高度）
         **/
        public static int getScreenHeight(Context context) {
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            return wm.getDefaultDisplay().getHeight();
        }

        /**
         * TODO 显示软键盘
         * (功能未实现)
         *
         * @param activity 当前Activity
         * @param view     接受软键盘输入的视图,光标在该视图上才显示软键盘
         */
        public static void showKeyboard(Activity activity, View view) {
            ((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(view, 0);
        }

        /**
         * TODO 隐藏软键盘
         *
         * @param activity 当前Activity
         */
        public static void hideKeyboard(Activity activity) {
            if (activity.getCurrentFocus() != null) {
                ((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
            }
        }
    }
}