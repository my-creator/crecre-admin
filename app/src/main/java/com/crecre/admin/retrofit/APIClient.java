package com.crecre.admin.retrofit;

import com.crecre.admin.retrofit.services.BoardService;
import com.crecre.admin.retrofit.services.UserService;
import com.crecre.admin.retrofit.services.VoteService;
import com.crecre.admin.utils.GsonDateFormatAdapter;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

import lombok.Getter;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Getter
public class APIClient {

    private static final String BASE_URL = "15.164.106.239";
    private static APIClient instance;
    private UserService userService;
    private VoteService voteService;
    private BoardService boardService;

    private OkHttpClient okHttp;
    private Retrofit retrofit;
    private Gson gson;

    private APIClient() {
        gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new GsonDateFormatAdapter())
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        init("");
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

    public void init(String token) {
        okHttp = new OkHttpClient.Builder()
                .addInterceptor(new MyInterceptor(token))
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(getAPIServer())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttp)
                .build();

        userService = retrofit.create(UserService.class);
        voteService = retrofit.create(VoteService.class);
        boardService = retrofit.create(BoardService.class);
    }
}
