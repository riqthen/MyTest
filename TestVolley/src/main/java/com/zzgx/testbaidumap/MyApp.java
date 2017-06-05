package com.zzgx.testbaidumap;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.riq.mylibrary.utils.Lcat;
import com.riq.mylibrary.utils.SPUtils;
import com.riq.mylibrary.utils.ToastUtils;

import java.util.Map;

/**
 * Created by 10903 on 2017/5/24.
 */

public class MyApp extends Application {
    private static final String SET_COOKIE_KEY = "Set-Cookie";
    private static final String COOKIE_KEY = "Cookie";
    public static String localCookie;

    private static MyApp instance;
    private RequestQueue queue;


    public static MyApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        SPUtils.getInstance().init(this, "session");
        queue = Volley.newRequestQueue(this);
    }

    public RequestQueue getRequestQueue() {
        return queue;
    }

    /**
     * 检查返回的Response header中有没有session
     *
     * @param responseHeaders Response Headers.
     */
    public final void checkSessionCookie(Map<String, String> responseHeaders) {
        if (responseHeaders.containsKey(SET_COOKIE_KEY)
                && responseHeaders.get(SET_COOKIE_KEY).startsWith("JSESSIONID")) {
            String cookie = responseHeaders.get(SET_COOKIE_KEY);
            if (cookie.length() > 0) {
                String[] splitCookie = cookie.split(";");
                String[] splitSessionId = splitCookie[0].split("=");
                cookie = splitSessionId[1];
                SPUtils.getInstance().saveString("JSESSIONID", cookie);
            }
        }
    }

    /**
     * 添加session到Request header中
     */
    public final void addSessionCookie(Map<String, String> requestHeaders) {
        String sessionId = SPUtils.getInstance().getString("JSESSIONID");
        Lcat.print(SPUtils.getInstance().getString("JSESSIONID", "session"));
        ToastUtils.showToast(this, SPUtils.getInstance().getString("JSESSIONID", "session"));
        if (sessionId.length() > 0) {
            StringBuilder builder = new StringBuilder();
            builder.append("JSESSIONID");
            builder.append("=");
            builder.append(sessionId);
            if (requestHeaders.containsKey(COOKIE_KEY)) {
                builder.append("; ");
                builder.append(requestHeaders.get(COOKIE_KEY));
            }
            requestHeaders.put(COOKIE_KEY, builder.toString());
        }
    }


}
