package com.crecre.admin.models.vote;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VoteData implements Serializable {
    private final int voteIdx;
    private final String thumbnailUrl;
    private final Date createTime;
    private final Date startTime;
    private final Date endTime;
    private final String title;
    private final String contents;
    private final VoteType type;
//    private final boolean isPermitted;
    private final List<VoteChoice> choices;

    public enum VoteType {
        NORMAL, CREATOR
    }
}