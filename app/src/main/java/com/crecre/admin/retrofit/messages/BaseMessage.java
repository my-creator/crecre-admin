package com.crecre.admin.retrofit.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BaseMessage {
    protected final int status;
    protected final boolean success;
    protected final String message;
}
