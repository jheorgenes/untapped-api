package com.pj.untapped.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pj.untapped.dtos.security.AccountCredentialsDTO;
import com.pj.untapped.service.AuthServices;

@RestController
@RequestMapping("/auth")
public class AuthResource {
    
    @Autowired
    AuthServices authServices;
    
    @SuppressWarnings("rawtypes")
    @PostMapping(value = "/signin")
    public ResponseEntity signin(@RequestBody AccountCredentialsDTO data) {
        if (chekIfParamsIsNotNull(data)) 
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        
        var token = authServices.signin(data);
        if(token == null) 
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        
        return token;
    }

    private boolean chekIfParamsIsNotNull(AccountCredentialsDTO data) {
        return data == null || data.getUsername() == null || data.getUsername().isBlank() || data.getPassword() == null || data.getPassword().isBlank();
    }
}
