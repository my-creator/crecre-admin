package com.crecre.admin.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.crecre.admin.adapters.viewholders.VoteChoiceViewHolder;
import com.crecre.admin.databinding.ItemVoteChoiceBinding;
import com.crecre.admin.models.vote.VoteChoice;
import com.crecre.admin.models.vote.VoteData;

import java.util.List;

public class VoteItemRecyclerViewAdapter extends RecyclerView.Adapter<VoteChoiceViewHolder> {

    private final VoteData voteData;
    private List<VoteChoice> choiceList;

    public VoteItemRecyclerViewAdapter(VoteData voteData) {
        this.voteData = voteData;
        this.choiceList = voteData.getChoices();
    }

    @NonNull
    @Override
    public VoteChoiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        ItemVoteChoiceBinding itemBinding =
                ItemVoteChoiceBinding.inflate(layoutInflater, parent, false);
        return new VoteChoiceViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull VoteChoiceViewHolder holder, int position) {
        VoteChoice item = choiceList.get(position);

        ItemVoteChoiceBinding binding = holder.bind(item);

        if (voteData.getType() == VoteData.VoteType.NORMAL) {
            binding.imgVoteChoiceItem.setVisibility(View.GONE);
        } else if (item.getCreatorProfileUrl() != null) {
            Glide.with(holder.itemView).load(item.getCreatorProfileUrl()).into(binding.imgVoteChoiceItem);
        }
    }

    @Override
    public int getItemCount() {
        return choiceList.size();
    }


}