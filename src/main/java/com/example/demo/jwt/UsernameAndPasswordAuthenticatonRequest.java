package com.example.demo.jwt;

import org.apache.catalina.authenticator.SavedRequest;

public class UsernameAndPasswordAuthenticatonRequest {
    private String username;
    private String password;
    public UsernameAndPasswordAuthenticatonRequest(){

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
