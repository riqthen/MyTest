package com.riq.test;


import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 10903 on 2017/5/24.
 */

public class MyStringRequest extends StringRequest {

    private final Response.Listener<String> listener;
    private Map<String, String> params;

    public MyStringRequest(int method, String url, Response.Listener<String> listener
            , Response.ErrorListener errorListener, Map<String, String> params) {
        super(method, url, listener, errorListener);
        this.listener = listener;
        this.params = params;
    }

    public MyStringRequest(String url, Map<String, String> mParams, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        this(Method.POST, url, listener, errorListener,mParams);
        this.params = mParams;
    }


    public MyStringRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener
            , Map<String, String> params) {
        super(url, listener, errorListener);
        this.listener = listener;
        this.params = params;
    }

    @Override
    protected void deliverResponse(String response) {
        listener.onResponse(response);
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        MyApp.getInstance().checkSessionCookie(response.headers);
        String parsed;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }
        return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = super.getHeaders();
        if (headers == null || headers.equals(Collections.emptyMap())) {
            headers = new HashMap<String, String>();
        }
        MyApp.getInstance().addSessionCookie(headers);
        return headers;
    }
}
