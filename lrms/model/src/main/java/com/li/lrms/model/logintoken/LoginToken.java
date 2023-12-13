package com.li.lrms.model.logintoken;

import lombok.Data;

import static com.li.lrms.model.loginrole.logins.resul;


public class LoginToken {
    private String token;

    @Override
    public String toString() {
        return "LoginToken{" +
                "token='" + token + '\'' +
                '}';
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}