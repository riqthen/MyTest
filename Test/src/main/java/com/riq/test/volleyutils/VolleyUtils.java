package com.riq.test.volleyutils;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.riq.test.Lcat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Volley工具类
 *
 * 1.在MyApp中初始化 VolleyUtils.init(this);
 */
public class VolleyUtils {
    private static Context context;
    private static RequestQueue requestQueue;
    /**
     * 返回数据成功
     */
    public static final int RESULT_SUCCESS = 200;
    /**
     * 返回数据失败
     */
    public static final int RESULT_FAIL = 201;
    private static int timeOut = 10000;//超时时间
    public static final String TAG = "volley_requestQueue";
    private static final String DEFAULT_CACHE_DIR = "volley";//Volley默认缓存路径,不可更改

    private static VolleyFactory volleyFactory;
    private static VolleyUtils volleyUtils;
    private final File cacheDir;
    private static DiskBasedCache diskBasedCache;
    private static HashMap<String, String> tagMap;

    public static VolleyUtils getInstance() {
        if (null == context) {
            throw new RuntimeException("Must be use init(context) in Application");
        }
        if (null == volleyUtils) {
            volleyUtils = new VolleyUtils(context);
        }
        return volleyUtils;
    }

    public static void init(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context can not be null");
        }

        if (!(context instanceof Application)) {
            throw new RuntimeException("context must be an Application Context");
        }
        VolleyUtils.context = context;
        volleyUtils = new VolleyUtils(context);
    }

    private VolleyUtils(Context context) {
        VolleyUtils.context = context;
        if (null == volleyFactory)
            volleyFactory = VolleyFactory.getInstance(context);
        if (null == requestQueue)
            requestQueue = volleyFactory.getRequestQueue();
        cacheDir = new File(context.getCacheDir(), DEFAULT_CACHE_DIR);//实例化缓存路径
        if (null == diskBasedCache)
            diskBasedCache = new DiskBasedCache(cacheDir);//实例化磁盘缓存类，用于清除缓存用
        if (null == tagMap)
            tagMap = new HashMap<>();
        tagMap.put(TAG, TAG);
    }

    /**
     * 发送Get请求
     *
     * @param url     请求url
     * @param handler 用于发回数据的handler，
     *                参数：what = {成功{@link #RESULT_SUCCESS}|失败{@link #RESULT_FAIL}}，agr1 = {method}，obj = {result}
     * @param method  用于判断哪个方法在调用
     */
    public void sendGetRequest(final String url, String tag, final Handler handler, final int method) {
        sendGetRequest(url, tag, new OnRequestResultListener() {
            @Override
            public void onResponse(String response) {
                String jsonString = response.toString();
                Message message = handler.obtainMessage();
                message.what = RESULT_SUCCESS;
                message.arg1 = method;
                message.obj = jsonString;
                handler.sendMessage(message);
            }

            @Override
            public void onErrorResponse(VolleyError error, String errString) {
                error(error, handler, method);
            }
        });
    }

    /**
     * 发送GET请求
     *
     * @param url      请求url
     * @param listener 结果监听器
     *                 没有网络的情况下,先onErrorResponse()请求失败,再deliverError()缓存,再onResponse()请求成功
     */
    public void sendGetRequest(String url, String tag, final OnRequestResultListener listener) {
        if (TextUtils.isEmpty(tag)) {
            tag = TAG;
        } else {
            cancelRequest(tag);
        }
        tagMap.put(tag, tag);
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listener.onResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onErrorResponse(error, error(error, null, 0));
            }
        }) {
            // TODO 没有网络的时候缓存上一次请求的数据
//            @Override
//            public void deliverError(VolleyError error) {
//                super.deliverError(error);
//                if (error instanceof NoConnectionError) {
//                    Cache.Entry entry = this.getCacheEntry();
//                    if (entry != null) {
//                        Response<String> response = parseNetworkResponse(new NetworkResponse(entry.data, entry.responseHeaders));
//                        deliverResponse(response.result);
//                    }
//                }
//            }
        };
        request.setShouldCache(true);
        request.setCacheEntry(new Cache.Entry());
        request.setTag(tag);//设置标签
        request.setRetryPolicy(new DefaultRetryPolicy(timeOut,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));//修改超时时间和重载次数
        requestQueue.add(request);
    }

    /**
     * 发送Post请求
     *
     * @param url     请求url
     * @param params  请求的参数集
     * @param handler 用于发回数据的handler，
     *                参数：what = {成功{@link #RESULT_SUCCESS}|失败{@link #RESULT_FAIL}}，agr1 = {method}，obj = {result}
     * @param method  用于判断哪个方法在调用
     */
    public void sendPostRequest(final String url, String tag, final HashMap<String, String> params, final Handler handler, final int method) {
        sendPostRequest(url, tag, params, new OnRequestResultListener() {
            @Override
            public void onResponse(String response) {
                Message message = handler.obtainMessage();
                message.what = RESULT_SUCCESS;
                message.arg1 = method;
                message.obj = response;
                handler.sendMessage(message);
            }

            @Override
            public void onErrorResponse(VolleyError error, String errString) {
                error(error, handler, method);
            }
        });
    }

    /**
     * 发送POST请求
     *
     * @param url      请求url
     * @param params   参数集
     * @param listener 结果监听器
     */
    public void sendPostRequest(String url, String tag, final HashMap<String, String> params, final OnRequestResultListener listener) {
        if (TextUtils.isEmpty(tag)) {
            tag = TAG;
        } else {
            cancelRequest(tag);
        }
        tagMap.put(tag, tag);

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {//成功的请求结果
                listener.onResponse(response);
            }
        }, new Response.ErrorListener() {//失败的请求结果
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onErrorResponse(error, error(error, null, 0));
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;//参数从这里进去
            }

            /**
             * 设置头部信息
             * @return
             * @throws AuthFailureError
             */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/x-www-form-urlencoded");//发送的参数以表单形式
                return headers;

            }
            // TODO 没有网络的时候缓存上一次请求的数据
//            @Override
//            public void deliverError(VolleyError error) {
//                super.deliverError(error);
//                if (error instanceof NoConnectionError) {
//                    Cache.Entry entry = this.getCacheEntry();
//                    if (entry != null) {
//                        Response<String> response = parseNetworkResponse(new NetworkResponse(entry.data, entry.responseHeaders));
//                        deliverResponse(response.result);
//                    }
//                }
//            }
        };
        request.setShouldCache(true);//是否启用缓存，默认已经启用，可不用设置
        request.setCacheEntry(new Cache.Entry());//设置缓存实体对象，注意：这里是重点，如果为null则不会有缓存
        request.setTag(tag);//设置标签
        request.setRetryPolicy(new DefaultRetryPolicy(timeOut, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));//修改超时时间和重载次数
        requestQueue.add(request);
    }

    /**
     * 处理错误
     *
     * @param error
     * @param handler
     * @param method
     */
    private String error(VolleyError error, Handler handler, int method) {
        String err = "请求失败！";
        if (error instanceof NetworkError) {
            err = "网络异常！";
        } else if (error instanceof ServerError) {
            err = "系统繁忙！";
        } else if (error instanceof AuthFailureError) {
            err = "请求验证失败！";
        } else if (error instanceof ParseError) {
            err = "请求解析错误！";
        } else if (error instanceof NoConnectionError) {
            err = "无法连接！";
        } else if (error instanceof TimeoutError) {
            err = "请求超时！";
        }
        if (null != handler) {
            Message message = handler.obtainMessage();
            message.what = RESULT_FAIL;
            message.arg1 = method;
            message.obj = err;
            handler.sendMessage(message);
        }
        return err;
    }

    /**
     * 加载图片
     *
     * @param url    图片url
     * @param result 回调，bitmap加载失败时为null
     */
    public void loadImg(String url, final IBitmapResult result) {
        getImageLoader().get(url, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                Bitmap bitmap = response.getBitmap();
                result.onResult(bitmap);
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                result.onResult(null);
            }
        });
    }

    /**
     * 加载图片
     *
     * @param url               图片url
     * @param imageView
     * @param defaultImageResId 默认时显示的图片资源
     * @param errorImageResId   加载错误时显示的图片资源
     */
    public void loadImg(String url, ImageView imageView, int defaultImageResId, int errorImageResId) {
        getImageLoader().get(url, ImageLoader.getImageListener(imageView, defaultImageResId, errorImageResId));
    }


    /**
     * 移除指定缓存
     *
     * @param method 方法名 请参阅{@link Request.Method}，GET为0，POST为1；如此条数据用GET方式请求得到的，则参数为{@link Request.Method #GET}
     * @param url    请求url
     */
    public void removeCache(Request.Method method, String url) {
        diskBasedCache.remove(method + ":" + url);//参数为key，默认:请求方法名+“:”+url
    }

    /**
     * 清除所有缓存
     */
    public void clearAllCache() {
        diskBasedCache.clear();
    }

    /**
     * 设置超时时间，默认10s
     *
     * @param timeOut
     */
    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    /**
     * 取消全部请求
     */
    public void cancleAllRequest() {
        if (null != requestQueue && null != tagMap) {
            for (Map.Entry entry : tagMap.entrySet()) {
                requestQueue.cancelAll(entry.getValue());
            }
        }
    }

    /**
     * 取消请求
     *
     * @param tag tag
     */
    public void cancelRequest(String tag) {
        if (null != tagMap) {
            if (!TextUtils.isEmpty(tag) && tagMap.containsKey(tag)) {
                requestQueue.cancelAll(tag);
            }
        }
    }

    /**
     * 获取请求队列
     *
     * @return
     */
    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    /**
     * 获取工厂类
     *
     * @return
     */
    public VolleyFactory getVolleyFactroy() {
        return volleyFactory;
    }

    /**
     * 获取ImageLoader
     *
     * @return
     */
    public ImageLoader getImageLoader() {
        return new ImageLoader(requestQueue, volleyFactory.getImgCache());
    }

    /**
     * 请求结果监听器
     */
    public interface OnRequestResultListener {
        /**
         * 成功的请求
         *
         * @param response 请求返回的字符串
         */
        void onResponse(String response);

        /**
         * 失败的请求
         *
         * @param error
         * @param errString
         */
        void onErrorResponse(VolleyError error, String errString);
    }

    /**
     * 加载图片的回调
     */
    public interface IBitmapResult {
        /**
         * 加载结果
         *
         * @param bitmap
         */
        void onResult(Bitmap bitmap);
    }

    /**
     * TODO　保存图片到手机
     *
     * @param path     保存在手机的路径 /storage/emulated/0/
     * @param fileName 文件名（需要扩展名,png,jpg...）
     * @param
     */
    public static void saveImageToStorage(String url, final String path, final String fileName) {
        RequestQueue queue = Volley.newRequestQueue(context);
        Request request = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(new File(path, fileName));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                response.compress(Bitmap.CompressFormat.PNG, 100, fos);
                try {
                    assert fos != null;
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 800, 800, ImageView.ScaleType.CENTER, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Lcat.print(error);
            }
        });
        queue.add(request);
    }


}
