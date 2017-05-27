package com.riq.test.volleyutils;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * 图片内存缓存类
 *
 * @author Created by qinlang on 2015/10/21.
 */
public class MImgCache extends LruCache<String, Bitmap> implements ImageLoader.ImageCache {
    private static int MAX_SIZE = 10 * 1024 * 1024;//内存缓存大小,10M

    /**
     * @param maxSize for caches that do not override {@link #sizeOf}, this is
     *                the maximum number of entries in the cache. For all other caches,
     *                this is the maximum sum of the sizes of the entries in this cache.
     */
    public MImgCache(int maxSize) {
        super(maxSize);
    }

    public MImgCache() {
        this(MAX_SIZE);
    }

    @Override
    protected int sizeOf(String key, Bitmap value) {
        return value.getRowBytes() * value.getHeight();
    }

    @Override
    public Bitmap getBitmap(String url) {
        return get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        put(url, bitmap);
    }
}
