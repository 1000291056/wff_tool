package com.wff.wff_tool.nativecode;

import android.util.Log;

/**
 * Created by 13248 on 2018/4/23.
 */

public class NativeTest {
    private static final String TAG=NativeTest.class.getSimpleName();
    public NativeTest() {
        NativeObject o = new NativeObject();
        o.printMsg("test jni - - - - - - - - - - - - - - - - - -  - - - - - - - - - - - - - -  - - - - - -start");
        int[] array = o.makeIntArray(4);
        int count=array.length;
        for (int i=0;i<count;i++
             ) {
            Log.i(TAG,"array"+String.valueOf(i)+":"+array[i]);
        }
        int twoArray[][]=o.makeIntArray(4,5);
        o.printMsg("test jni - - - - - - - - - - - - - - - - - -  - - - - - - - - - - - - - -  - - - - - -end");
    }
}
