package com.wff.androidtool.dragger;

import dagger.Module;
import dagger.Provides;

@Module
public class DraggerModule {
    @Provides
    Person proverPerson() {
        return new Person();
    }
}
