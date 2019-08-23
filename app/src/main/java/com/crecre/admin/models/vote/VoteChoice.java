package com.crecre.admin.models.vote;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class VoteChoice implements Serializable {
    private final int choiceIdx;
    private final String name;
    private final int count;
    private final String creatorProfileUrl;
    private final int followerGradeIdx;
    private final String followerGradeName;
    private final int followerGradeLevel;
    private final String followerGradeImgUrl;
    private final String viewGradeImgUrl;
    private final int rank;
}
