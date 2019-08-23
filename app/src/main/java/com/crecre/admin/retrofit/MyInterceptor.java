package com.crecre.admin.retrofit;

import android.support.annotation.NonNull;
import android.util.Log;

import com.crecre.admin.utils.GsonDateFormatAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.Date;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

class MyInterceptor implements Interceptor {
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request().newBuilder()
                .method(chain.request().method(), chain.request().body())
                .build();

//        long t1 = System.nanoTime();
        Log.i("okhttp", String.format("Sending request %s on %s%n%s%n%s\n%s",
                request.url(), chain.connection(), request.headers(), request.body(), request.header("content-type")));

        Response response = chain.proceed(request);

        //        long t2 = System.nanoTime();
        Log.i("okhttp",String.format("Received response for %s in %n%s",
                response.body().toString(), response.headers()));

        return response;
    }
}