package com.crecre.admin.retrofit;

import com.crecre.admin.retrofit.messages.requests.SignInRequest;
import com.crecre.admin.retrofit.messages.responses.SignInResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("/api/auth/signin")
    Call<SignInResponse> signIn(@Body SignInRequest signInRequest);

}
