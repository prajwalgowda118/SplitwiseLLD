package com.scaler.splitwiselld.Services.PaswordEncoder;

public interface PasswordEncoder {

    String encode(String password);

    boolean matches(String password, String encodedPassword);

}
