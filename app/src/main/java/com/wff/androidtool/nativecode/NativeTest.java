package com.wff.androidtool.nativecode;

import android.util.Log;

import java.nio.charset.Charset;

/**
 * Created by 13248 on 2018/4/23.
 */

public class NativeTest {
    private static final String TAG = NativeTest.class.getSimpleName();

    public NativeTest() {
        NativeObject o = new NativeObject();
        o.printMsg("test jni - - - - - - - - - - - - - - - - - -  - - - - - - - - - - - - - -  - - - - - -start");
        int[] array = o.makeIntArray(4);
        int count = array.length;
        for (int i = 0; i < count; i++
                ) {
            Log.i(TAG, "array" + String.valueOf(i) + ":" + array[i]);
        }
        int twoArray[][] = o.makeIntArray(4, 5);
        o.printIntArrayElement(new int[]{3, 5, 7});
        o.printByteArrayElement("我是bt".getBytes(Charset.forName("utf-8")));
        o.printMsg("test jni - - - - - - - - - - - - - - - - - -  - - - - - - - - - - - - - -  - - - - - -end");
    }
}
