package com.wff.androidtool.retrofit;

import java.util.concurrent.atomic.AtomicReference;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpUtils {
    static AtomicReference<Retrofit> reference = new AtomicReference<>();
    private Retrofit retrofit;
    private String BASEURL = "https://api.douban.com/v2/";

    public static Retrofit instance() {
        if (reference.get() == null) {
            reference.set(new HttpUtils().retrofit);
        }
        return reference.get();
    }

    public HttpUtils() {
        retrofit = new Retrofit.Builder().baseUrl(BASEURL).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
    }
}
