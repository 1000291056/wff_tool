package com.wff.androidtool.mock;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

import static org.mockito.Mockito.*;

public class MockTestTest {

    @Test
    public void mockTest() {
        Person person = mock(Person.class);
        Iterator<String> iterator = mock(Iterator.class);
        when(iterator.next()).thenReturn("first,").thenReturn("second");
        //doThrow(new NoSuchElementException()).when(iterator).next();
        String res = iterator.next() + iterator.next();
        //iterator.next();

        verify(iterator,atLeastOnce()).next();
        Assert.assertEquals("first,second",res);

    }
}