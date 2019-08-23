package com.crecre.admin.retrofit.messages.responses;

import com.crecre.admin.models.vote.VoteData;
import com.crecre.admin.retrofit.messages.BaseMessage;

import java.util.List;

import lombok.Getter;

@Getter
public class VoteResponse extends BaseMessage {
    private final List<VoteData> data;

    public VoteResponse(int status, boolean success, String message, List<VoteData> data) {
        super(status, success, message);
        this.data = data;
    }

}
