package com.wff.androidtool.testapi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.LruCache;

import com.orhanobut.logger.Logger;
import com.wff.androidtool.R;

public class LruCacheT {
    int maxmemory = (int) (Runtime.getRuntime().totalMemory() / 1024);
    int cacheSize = maxmemory / 8;
    LruCache<String, Bitmap> mLrucache = new LruCache<String, Bitmap>(cacheSize) {

        @Override
        protected int sizeOf(String key, Bitmap value) {
            if (value != null) {
                return value.getRowBytes() * value.getHeight() / 1024;
            }
            return super.sizeOf(key, value);
        }
    };

    public LruCacheT(Context context) {
        put(context);
    }

    public void put(Context context) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.a);
        mLrucache.put("bim", bitmap);
    }

    public Bitmap get() {
        Logger.d("size=" + mLrucache.putCount());
        return mLrucache.get("bim");
    }
}
