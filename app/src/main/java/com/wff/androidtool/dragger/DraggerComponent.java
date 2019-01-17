package com.wff.androidtool.dragger;

import android.app.Activity;

import dagger.Component;

@Component(modules = {DraggerModule.class})
public interface DraggerComponent {
    void inject(Activity activity);
}
