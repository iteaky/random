package com.packagename.myapp.spring;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usr")
public class User {
    @Id
    private long id;
    private String token;

    public User(String token, Long id) {
        this.token = token;
        this.id = id;
    }
}
