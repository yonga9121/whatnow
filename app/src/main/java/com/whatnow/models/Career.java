package com.whatnow.models;

public class Career {

    private String id;
    private String career;

    public Career() {
    }

    public Career(String id, String career) {
        this.id = id;
        this.career = career;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }
}
