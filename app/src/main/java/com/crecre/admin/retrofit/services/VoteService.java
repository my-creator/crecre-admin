package com.crecre.admin.retrofit.services;

import com.crecre.admin.retrofit.messages.responses.SimpleResponse;
import com.crecre.admin.retrofit.messages.responses.VoteResponse;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface VoteService {

    @GET("/api/votes/suggestions")
    Call<VoteResponse> getSuggestedVotes();

    @PUT("/api/votes/{voteIdx}/permit")
    Call<SimpleResponse> permitVote(@Path("voteIdx") int voteIdx);

    @POST("/api/votes/{voteIdx}/choice")
    Call<SimpleResponse> addChoice(@Path("voteIdx") int voteIdx);

    @PUT("/api/votes/choice/{choiceIdx}")
    Call<SimpleResponse> updateChoice(@Path("choiceIdx") int choiceIdx);

    @DELETE("/api/votes/choice/{choiceIdx}")
    Call<SimpleResponse> deleteChoice(@Path("choiceIdx") int choiceIdx);

}
