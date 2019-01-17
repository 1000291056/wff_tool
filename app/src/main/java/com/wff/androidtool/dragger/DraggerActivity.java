package com.wff.androidtool.dragger;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

public class DraggerActivity extends AppCompatActivity {
    @Inject
    Person person;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerDraggerComponent.builder().build().inject(this);
        person.log();
    }
}
