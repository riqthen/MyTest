package com.riq.test.volleyutils;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 用法:
 * 1.手动解析到需要数据的项目
    JSONObject jsonObject = new JSONObject(response);
    JSONObject object = jsonObject.getJSONObject("showapi_res_body");
 * 2.再JSON.getXxxx获取数组,int,String等
    JSONArray contentlist = JSON.getArray(object, "contentlist");
 * 3.在数组中,按照手动解析的方式获取数组内的数据
    JSONObject obj = contentlist.getJSONObject(i);      //原始
    JSONObject obj = JSON.getObjInArray(alarmLog, i);   //封装
 */
public class JSON {
    public static JSONObject getObj(String jsonStr) {
        if (TextUtils.isEmpty(jsonStr)) {
            return null;
        }
        JSONObject obj = null;
        try {
            obj = new JSONObject(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static JSONObject getObj(JSONObject obj, String name) {
        if (obj == null || TextUtils.isEmpty(name)) {
            return null;
        }
        JSONObject obj_ = null;
        try {
            if (obj.has(name)) {
                obj_ = obj.getJSONObject(name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj_;
    }

    public static JSONObject getObjInArray(JSONArray array, int pos) {
        if (pos < 0 || array == null || pos > array.length()) {
            return null;
        }
        JSONObject obj = null;
        try {
            obj = array.getJSONObject(pos);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static JSONArray getArray(JSONObject obj, String name) {
        if (obj == null || TextUtils.isEmpty(name)) {
            return null;
        }
        JSONArray array = null;
        try {
            if (obj.has(name)) {
                array = obj.getJSONArray(name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }

    public static int getInt(JSONObject obj, String name) {
        if (obj == null || TextUtils.isEmpty(name)) {
            return -44;
        }
        int back = 0;
        try {
            if (obj.has(name)) {
                back = obj.getInt(name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return back;
    }

    public static int getInt(JSONObject obj, String name, int defaultVal) {
        if (obj == null || TextUtils.isEmpty(name)) {
            return defaultVal;
        }
        int back = defaultVal;
        try {
            if (obj.has(name)) {
                back = obj.getInt(name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return back;
    }

    public static char getChar(JSONObject obj, String name) {
        if (obj == null || TextUtils.isEmpty(name)) {
            return 0;
        }
        char back = 0;
        try {
            if (obj.has(name)) {
                back = (char) (obj.getInt(name) & 0xFFFF);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return back;
    }

    public static short getShort(JSONObject obj, String name) {
        if (obj == null || TextUtils.isEmpty(name)) {
            return -44;
        }
        short back = 0;
        try {
            if (obj.has(name)) {
                back = (short) (obj.getInt(name));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return back;
    }

    public static byte getByte(JSONObject obj, String name) {
        if (obj == null || TextUtils.isEmpty(name)) {
            return -1;
        }
        byte back = 0;
        try {
            if (obj.has(name)) {
                back = (byte) (obj.getInt(name));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return back;
    }

    public static long getLong(JSONObject obj, String name) {
        if (obj == null || TextUtils.isEmpty(name)) {
            return -44;
        }
        long back = 0;
        try {
            if (obj.has(name)) {
                back = obj.getLong(name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return back;
    }

    public static float getFloat(JSONObject obj, String name) {
        if (obj == null || TextUtils.isEmpty(name)) {
            return -44;
        }
        float back = 0;
        try {
            if (obj.has(name)) {
                back = (float) obj.getDouble(name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return back;
    }

    public static float getFloat(JSONObject obj, String name, float defaultVal) {
        float back = defaultVal;
        if (obj == null || TextUtils.isEmpty(name)) {
            return back;
        }
        try {
            if (obj.has(name)) {
                back = (float) obj.getDouble(name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return back;
    }

    public static String getString(JSONObject obj, String name) {
        if (obj == null || TextUtils.isEmpty(name)) {
            return null;
        }
        String back = "";
        try {
            if (obj.has(name)) {
                back = obj.getString(name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return back;
    }

    public static double getDouble(JSONObject obj, String name) {
        if (obj == null || TextUtils.isEmpty(name)) {
            return -44;
        }
        double back = 0;
        try {
            if (obj.has(name)) {
                back = obj.getDouble(name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return back;
    }

    public static boolean getBoolean(JSONObject obj, String name) {
        if (obj == null || TextUtils.isEmpty(name)) {
            return false;
        }
        boolean back = false;

        try {
            if (obj.has(name)) {
                back = obj.getBoolean(name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return back;
    }
}
