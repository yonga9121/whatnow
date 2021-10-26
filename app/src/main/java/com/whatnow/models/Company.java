package com.whatnow.models;

public class Company {

    private String id;
    private String name;
    private String logo_url;

    public Company() {
    }

    public Company(String id, String name, String logo_url) {
        this.id = id;
        this.name = name;
        this.logo_url = logo_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo_url() { return this.logo_url; }

    public void setLogo_url(String logo_url) {
        this.logo_url = logo_url;
    }
}
