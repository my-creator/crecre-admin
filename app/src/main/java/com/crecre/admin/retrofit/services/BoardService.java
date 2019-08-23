package com.crecre.admin.retrofit.services;

import com.crecre.admin.retrofit.messages.responses.VoteResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BoardService {

    @GET("/api/boards/suggestions")
    Call<VoteResponse> getSuggestedVotes();

}
