package com.wff.androidtool.retrofit.netinterface;

import com.wff.androidtool.retrofit.Bean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Book {
    @GET("book/search")
    public Call<Bean> searchBooks(@Query("q") String name, @Query("tag") String tag, @Query("count") int count, @Query("start") int start);
}
