package com.packagename.myapp.spring;


public class User {
    private long id;
    private String token;

    public User(String token, Long id) {
        this.token = token;
        this.id = id;
    }
}
