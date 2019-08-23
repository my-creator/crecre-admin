package com.crecre.admin.retrofit.messages.responses;

import com.crecre.admin.models.auth.SignInData;
import com.crecre.admin.retrofit.messages.BaseMessage;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class SignInResponse extends BaseMessage {
    private final SignInData data;

    public SignInResponse(int status, boolean success, String message, SignInData data) {
        super(status, success, message);
        this.data = data;
    }

    @AllArgsConstructor
    @Getter
    private class Token {
        private String token;
        private String refreshToken;
    }
}
