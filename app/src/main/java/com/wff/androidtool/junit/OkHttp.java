package com.wff.androidtool.junit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttp {
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public String testOkHttp() {
        String url="http://www.weather.com.cn/data/sk/101190408.html";
        OkHttpClient client=new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
            return response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";

    }
}
