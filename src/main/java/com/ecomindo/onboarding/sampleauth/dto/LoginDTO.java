package com.ecomindo.onboarding.sampleauth.dto;

public class LoginDTO {
    private String username;
    private String password;

    LoginDTO(){

    }
    
    public String getPassword() {
        return password;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
