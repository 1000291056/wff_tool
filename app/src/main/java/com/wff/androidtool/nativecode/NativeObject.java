package com.wff.androidtool.nativecode;

/**
 * Created by 13248 on 2018/4/20.
 * classpath=com.wff.androidtool.nativecode.NativeObject
 */

public class NativeObject {
    static {
        System.loadLibrary("hello");
    }
    public native void printMsg(String msg);
    public native int[] makeIntArray(int size);
    public native int[][] makeIntArray(int r,int c);
    public native void testCObject();
    public native void printIntArrayElement(int[]array);
    public native void printByteArrayElement(byte[]array);
}
