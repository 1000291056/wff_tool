package com.wff.androidtool.retrofit.netinterface;

import com.wff.androidtool.retrofit.Bean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RxBook {
    @GET("book/search")
    public Flowable<Bean> searchBooks(@Query("q") String name, @Query("tag") String tag, @Query("count") int count, @Query("start") int start);
}
