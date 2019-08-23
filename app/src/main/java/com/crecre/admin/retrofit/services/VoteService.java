package com.crecre.admin.retrofit.services;

import com.crecre.admin.retrofit.messages.responses.VoteResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface VoteService {

    @GET("/api/votes/suggestions")
    Call<VoteResponse> getSuggestedVotes();

}
