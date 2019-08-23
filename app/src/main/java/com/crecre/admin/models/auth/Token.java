package com.crecre.admin.models.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Token {
    private String token;
    private String refreshToken;
}