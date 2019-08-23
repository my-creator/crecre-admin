package com.crecre.admin.adapters.viewholders;

import android.support.v7.widget.RecyclerView;

import com.crecre.admin.databinding.ItemVoteBinding;
import com.crecre.admin.models.vote.VoteData;

import lombok.Getter;

@Getter
public class VoteViewHolder extends RecyclerView.ViewHolder {
    private final ItemVoteBinding binding;

    public VoteViewHolder(ItemVoteBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public ItemVoteBinding bind(VoteData item) {
        binding.setItem(item);
        binding.executePendingBindings();
        return binding;
    }
}