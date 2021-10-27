package com.whatnow.models;

public class Session {

    private String id;
    private String token;
    private String owner_id;

    public Session() {
    }

    public Session(String id, String token, String owner_id) {
        this.id = id;
        this.token = token;
        this.owner_id = owner_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
    }
}
