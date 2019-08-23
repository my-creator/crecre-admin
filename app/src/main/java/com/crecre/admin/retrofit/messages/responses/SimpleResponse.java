package com.crecre.admin.retrofit.messages.responses;

import com.crecre.admin.retrofit.messages.BaseMessage;

import lombok.Getter;

@Getter
public class SimpleResponse extends BaseMessage {
    private final String data;

    public SimpleResponse(int status, boolean success, String message, String data) {
        super(status, success, message);
        this.data = data;
    }

}
