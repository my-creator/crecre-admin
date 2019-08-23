package com.crecre.admin.retrofit.messages.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SignInRequest {
    private final String id;
    private final String passwd;
}