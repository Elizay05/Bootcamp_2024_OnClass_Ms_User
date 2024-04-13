package com.example.bootcamp_2024_onclass_ms_user.domain.model;

public class Authentication {

    private final String email;

    private final String password;

    public Authentication(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
