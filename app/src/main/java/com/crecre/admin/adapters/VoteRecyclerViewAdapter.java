package com.crecre.admin.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.crecre.admin.adapters.viewholders.VoteViewHolder;
import com.crecre.admin.databinding.ItemVoteBinding;
import com.crecre.admin.models.vote.VoteData;
import com.crecre.admin.retrofit.APIClient;
import com.crecre.admin.retrofit.messages.responses.SimpleResponse;
import com.crecre.admin.utils.Alert;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VoteRecyclerViewAdapter extends RecyclerView.Adapter<VoteViewHolder> {
    private static final String TAG = VoteRecyclerViewAdapter.class.getSimpleName();

    private List<VoteData> voteList;

    public VoteRecyclerViewAdapter(List<VoteData> voteList) {
        this.voteList = voteList;
    }

    @NonNull
    @Override
    public VoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        ItemVoteBinding itemBinding =
                ItemVoteBinding.inflate(layoutInflater, parent, false);
        return new VoteViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull VoteViewHolder holder, int position) {
        final VoteData item = voteList.get(position);

        ItemVoteBinding binding = holder.bind(item);

        if (item.getThumbnailUrl() != null) {
            binding.imgVoteItemThumbnail.setPadding(0, 0, 0, 0);
            Glide.with(holder.itemView).load(item.getThumbnailUrl()).into(binding.imgVoteItemThumbnail);
        }

        if (item.getEndTime() == null) {
            binding.txtVoteItemDayLeft.setVisibility(View.GONE);
            binding.txtVoteItemStatus.setText("투표 대기중");
        } else {
            Date now = new Date();

            int diffDay = item.getEndTime().getDay() - now.getDay();
            binding.txtVoteItemDayLeft.setText(String.format(Locale.KOREA, "%d일 후 개표", diffDay));
            binding.txtVoteItemStatus.setText(now.compareTo(item.getEndTime()) > 0 ? "진행중" : "투표 종료");
        }

        if (item.getContents() == null || item.getContents().isEmpty()) {
            binding.rvVoteItemContent.setText("# 이 투표에 대한 운영자의 서브 설명");
        } else {
            binding.rvVoteItemContent.setText(item.getContents());
        }

        binding.rvVoteItemChoices.setAdapter(new VoteChoiceRecyclerViewAdapter(item));

        binding.btnVoteItemPermit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APIClient.getInstance().getVoteService()
                        .permitVote(item.getVoteIdx())
                        .enqueue(new Callback<SimpleResponse>() {
                            @Override
                            public void onResponse(@NonNull Call<SimpleResponse> call,
                                                   @NonNull Response<SimpleResponse> response) {
                                if (response.body() != null) {
                                    if (response.body().isSuccess()) {
                                        Alert.makeText("투표 올리기 성공");
                                    } else {
                                        Alert.makeText("투표 올리기 실패");
                                    }
                                } else {
                                    Alert.makeText("투표 올리던 중 에러 발생");
                                }
                            }

                            @Override
                            public void onFailure(@NonNull Call<SimpleResponse> call,
                                                  @NonNull Throwable t) {
                                Log.e(TAG, "투표 올리던 중 네트워크 에러 발생", t);
                            }
                        });
            }
        });
    }

    @Override
    public int getItemCount() {
        return voteList.size();
    }


}