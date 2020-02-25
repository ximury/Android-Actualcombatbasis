package com.mingrisoft.fieldassistant.cache;

import android.graphics.Bitmap;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by dllo on 16/1/19.
 */
public class AllLruCacheLoad implements ImageLoader.ImageCache {
    private SdcardLruCacheLoad sdcardLruCacheLoad;
    private NetLruCacheLoad netLruCacheLoad;
    public AllLruCacheLoad() {
        sdcardLruCacheLoad = new SdcardLruCacheLoad();
        netLruCacheLoad = new NetLruCacheLoad();
    }
    @Override
    public Bitmap getBitmap(String url) {
        Bitmap bitmap = sdcardLruCacheLoad.getBitmap(url);
        if (null == sdcardLruCacheLoad){
            bitmap =  netLruCacheLoad.getBitmap(url);
        }
        return bitmap;
    }
    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        netLruCacheLoad.putBitmap(url, bitmap);
        sdcardLruCacheLoad.putBitmap(url, bitmap);
    }
}
