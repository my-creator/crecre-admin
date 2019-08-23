package com.crecre.admin.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.crecre.admin.adapters.viewholders.VoteViewHolder;
import com.crecre.admin.databinding.ItemVoteBinding;
import com.crecre.admin.models.vote.VoteData;

import java.util.Date;
import java.util.List;
import java.util.Locale;

public class VoteRecyclerViewAdapter extends RecyclerView.Adapter<VoteViewHolder> {

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
        VoteData item = voteList.get(position);

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

        if (item.getContents() != null) {
            binding.rvVoteItemContent.setText(item.getContents());
        }

        binding.rvVoteItemChoices.setAdapter(new VoteItemRecyclerViewAdapter(item));
    }

    @Override
    public int getItemCount() {
        return voteList.size();
    }


}