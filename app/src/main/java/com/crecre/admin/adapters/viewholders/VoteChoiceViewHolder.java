package com.crecre.admin.adapters.viewholders;

import android.support.v7.widget.RecyclerView;

import com.crecre.admin.databinding.ItemVoteBinding;
import com.crecre.admin.databinding.ItemVoteChoiceBinding;
import com.crecre.admin.models.vote.VoteChoice;
import com.crecre.admin.models.vote.VoteData;

import lombok.Getter;

@Getter
public class VoteChoiceViewHolder extends RecyclerView.ViewHolder {
    private final ItemVoteChoiceBinding binding;

    public VoteChoiceViewHolder(ItemVoteChoiceBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public ItemVoteChoiceBinding bind(VoteChoice item) {
        binding.setItem(item);
        binding.executePendingBindings();
        return binding;
    }
}