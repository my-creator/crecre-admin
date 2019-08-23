package com.crecre.admin.uis;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.crecre.admin.R;
import com.crecre.admin.adapters.VoteRecyclerViewAdapter;
import com.crecre.admin.databinding.ActivityVoteBinding;
import com.crecre.admin.models.vote.VoteData;
import com.crecre.admin.retrofit.APIClient;
import com.crecre.admin.retrofit.messages.responses.VoteResponse;
import com.crecre.admin.utils.Alert;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreatorActivity extends AppCompatActivity {
    private static final String TAG = CreatorActivity.class.getSimpleName();

    private ActivityVoteBinding binding;
    private VoteRecyclerViewAdapter voteRecyclerViewAdapter;
    private List<VoteData> voteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_creator);

        voteList = new ArrayList<>();
        voteRecyclerViewAdapter = new VoteRecyclerViewAdapter(voteList);
        binding.rvVoteVotes.setAdapter(voteRecyclerViewAdapter);

        APIClient.getInstance().getVoteService()
                .getSuggestedVotes()
                .enqueue(new Callback<VoteResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<VoteResponse> call, @NonNull Response<VoteResponse> response) {
                        if (response.body() != null) {
                            voteList.addAll(response.body().getData());
                            voteRecyclerViewAdapter.notifyDataSetChanged();
                        } else {
                            Alert.makeText("투표 정보 받아오던 중 에러 발생");
                            Log.e(TAG, response.message());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<VoteResponse> call, @NonNull Throwable t) {
                        Log.e(TAG, "투표정보 받아오던 중 네트워크 에러 발생", t);
                    }
                });
    }
}
