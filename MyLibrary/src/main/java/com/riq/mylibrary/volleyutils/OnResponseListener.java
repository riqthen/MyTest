package com.riq.mylibrary.volleyutils;

/**
 * Created by 10903 on 2017/4/26.
 */

public interface OnResponseListener<T> {
    /**
     * On result success
     *
     * @param status
     * @param t
     */
    void onResponse(int status, T t);

    /**
     * On result error
     *
     * @param errCode
     * @param msg
     */
    void onError(int errCode, String msg);
}
