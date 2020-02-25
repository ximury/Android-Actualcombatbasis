package com.mingrisoft.fieldassistant.cache;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by dllo on 16/1/19.
 */
public class NetLruCacheLoad implements ImageLoader.ImageCache{
    private LruCache<String,Bitmap> cache;
    public NetLruCacheLoad() {
        //获取手机运行时最的内存
        //由于获取到的是字节，所以除1024
        int maxMemory = (int) (Runtime.getRuntime().maxMemory()/1024);
        //取出最大内存的1/4；
        int cacheSize = maxMemory/4;
        cache = new LruCache<String,Bitmap>(cacheSize){
            protected int sizeOf(String url ,Bitmap bmp){
                return bmp.getRowBytes()*bmp.getHeight()/1024;
            }
        };
    }
    @Override
    public Bitmap getBitmap(String url) {
        return cache.get(url);
    }
    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        cache.put(url,bitmap);
    }
}
