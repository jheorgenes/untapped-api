package com.pj.untapped.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.pj.untapped.service.exceptions.InvalidJwtAuthenticationException;

public class JwtTokenFilter extends GenericFilterBean {

    @Autowired
    private JwtTokenProvider tokenProvider;

    public JwtTokenFilter(JwtTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws InvalidJwtAuthenticationException, IOException, ServletException {
        String token = tokenProvider.resolveToken((HttpServletRequest) request); //Obtendo o token
        if (token != null && tokenProvider.validadeToken(token)) { //Valida o token
            Authentication auth = tokenProvider.getAuthentication(token); //Obtém authenticação.
            if (auth != null) {
                SecurityContextHolder.getContext().setAuthentication(auth); //Insere a authenticação no Spring Security
            }
        } 
        chain.doFilter(request, response);
    }
}
