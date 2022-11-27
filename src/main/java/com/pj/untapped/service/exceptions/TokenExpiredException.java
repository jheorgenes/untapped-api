package com.pj.untapped.service.exceptions;

import java.time.Instant;

import com.auth0.jwt.exceptions.JWTVerificationException;

@Deprecated //Depreciação manual
public class TokenExpiredException extends JWTVerificationException {

    private static final long serialVersionUID = -7076928975713577708L;

    private final Instant expiredOn;

    public TokenExpiredException(String message, Instant expiredOn) {
        super(message);
        this.expiredOn = expiredOn;
    }

    public Instant getExpiredOn() {
        return expiredOn;
    }
}
