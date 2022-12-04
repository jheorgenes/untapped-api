package com.pj.untapped.service.exceptions;

import com.auth0.jwt.exceptions.JWTVerificationException;

public class TokenExpiredException extends JWTVerificationException {

    private static final long serialVersionUID = -7076928975713577708L;

    public TokenExpiredException(String message) {
        super(message);
    }
}
