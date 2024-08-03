package com.scaler.splitwiselld.Services.PaswordEncoder;

public class BCryptEncoder implements PasswordEncoder {

    @Override
    public String encode(String password) {
        return "";
    }

    @Override
    public boolean matches(String password, String encodedPassword) {
        return false;
    }
}
