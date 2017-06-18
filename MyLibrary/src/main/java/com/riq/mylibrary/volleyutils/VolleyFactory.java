//package com.riq.mylibrary.volleyutils;
//
//import android.content.Context;
//
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.toolbox.Volley;
//
///**
// * Volley工厂类
// *
// * @author Created by qinlang on 2015/10/21.
// */
//public class VolleyFactory {
//    private RequestQueue requestQueue;
//    private Context context;
//    private MImgCache imgCache;
//    private static final int MAX_DISK_CACHE = 20 * 1024 * 1024;//磁盘缓存大小，20M
//
//    private VolleyFactory(Context context) {
//        this.context = context;
//        requestQueue = getRequestQueue();
//        imgCache = new MImgCache();
//    }
//
//    private static VolleyFactory instance;
//
//    /**
//     * @param context
//     * @return
//     */
//    public static synchronized VolleyFactory getInstance(Context context) {
//        if (instance == null) {
//            instance = new VolleyFactory(context);
//        }
//        return instance;
//    }
//
//    /**
//     * 获取请求队列
//     *
//     * @return
//     */
//    public RequestQueue getRequestQueue() {
//        if (requestQueue == null) {
//            requestQueue = Volley.newRequestQueue(context.getApplicationContext(), MAX_DISK_CACHE);//实例一个请求队列，并初始化磁盘缓存大小
//        }
//        return requestQueue;
//    }
//
//    /**
//     * 添加下载队列
//     *
//     * @param request
//     * @param <T>
//     */
//    public <T> void addRequest(Request<T> request) {
//        getRequestQueue().add(request);
//    }
//
//    /**
//     * 取消下载队列
//     *
//     * @param tag
//     */
//    public void cancelRequest(Object tag) {
//        getRequestQueue().cancelAll(tag);
//    }
//
//    /**
//     * 获取内存缓存对象
//     *
//     * @return
//     */
//    public MImgCache getImgCache() {
//        return imgCache;
//    }
//}
