package com.wff.wff_tool.junit;

import org.junit.Assert;
import org.junit.Test;

public class CaculaterTest {
    @Test
    public void testAdd() {
        Caculater caculater = new Caculater();
        int a=caculater.add(1,2);
        Assert.assertEquals(5,a);

    }
}
