package com.wff.androidtool.dragger;

import com.orhanobut.logger.Logger;

import javax.inject.Inject;

public class Person {
    @Inject
    public Person() {

    }
    public void log(){
        Logger.d("Person create");
    }
}