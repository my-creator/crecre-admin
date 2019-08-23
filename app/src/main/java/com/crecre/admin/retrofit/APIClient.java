package com.crecre.admin.retrofit;

import com.crecre.admin.utils.GsonDateFormatAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import lombok.Getter;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Getter
public class APIClient {

    private static final String BASE_URL = "15.164.106.239";
    private static APIClient instance;
    private UserService userService;

    private APIClient() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new GsonDateFormatAdapter())
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        OkHttpClient okHttp = new OkHttpClient.Builder()
                .addInterceptor(new MyInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getAPIServer())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttp)
                .build();

        userService = retrofit.create(UserService.class);
    }

    private String getAPIServer() {
        return "http://" + BASE_URL + ":3000";
    }

    public static APIClient getInstance() {
        if (instance == null) {
            synchronized (APIClient.class) {
                if (instance == null) {
                    instance = new APIClient();
                }
            }
        }
        return instance;
    }
}
