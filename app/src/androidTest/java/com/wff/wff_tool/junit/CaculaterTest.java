package com.wff.wff_tool.junit;

import android.util.Log;

import com.orhanobut.logger.Logger;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CaculaterTest {
    Caculater caculater;

    @Before
    public void setUp() throws Exception {
        caculater = new Caculater();
    }

    @Test
    public void add() {
        Assert.assertEquals(3,caculater.add(1,2));
    }

    @After
    public void tearDown() throws Exception {

    }
}