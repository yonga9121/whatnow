package com.whatnow.models;

public class User {

    private String id;
    private String first_name;
    private String last_name;
    private String email;

    public User() {
    }

    public User(String id, String first_name, String last_name, String email) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }
}
