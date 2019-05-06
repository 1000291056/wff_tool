package com.wff.androidtool.utils;

import java.lang.ref.WeakReference;

public class ScreenUtil {
    private static WeakReference<ScreenUtil> mScreenUtilWeakReference;

    public static ScreenUtil Instance() {
        if (mScreenUtilWeakReference == null) {
            mScreenUtilWeakReference = new WeakReference<ScreenUtil>(new ScreenUtil());
        }
        if (mScreenUtilWeakReference.get()==null){
//            mScreenUtilWeakReference
        }
        return null;
    }
}
