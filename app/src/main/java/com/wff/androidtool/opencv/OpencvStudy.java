package com.wff.androidtool.opencv;

import android.os.Looper;

import com.orhanobut.logger.Logger;

public class OpencvStudy {
    public native void studyOpencv(byte[]data);
    public native void nativetest();
    public static void callback(){
        Logger.e("在主线程："+ (Looper.getMainLooper()==Looper.myLooper()));
    }
    static {
        System.loadLibrary("OpencvStudy");
    }
}
