package com.wff.wff_tool.junit;

import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OkHttpTest {
    OkHttp okHttp;

    @Before
    public void setUp() throws Exception {
        okHttp=new OkHttp();
    }

    @After
    public void tearDown() throws Exception {
        okHttp=null;
    }


    @Test
    public void testOkHttp() {
        Log.i("test",okHttp.testOkHttp());
        System.out.print(11111);
    }
}