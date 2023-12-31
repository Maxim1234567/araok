package ru.araok.service;

import io.jsonwebtoken.Claims;

public interface JwtProviderService {
    boolean validateAccessToken(String accessToken);

    Claims getAccessClaims(String token);
}
