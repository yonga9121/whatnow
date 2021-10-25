package com.whatnow.models;

public class Offer {

    private String id;
    private String name;
    private String desc;
    private String descVideoUrl;
    private Company company;

    public Offer() {
    }

    public Offer(String id, String name, String desc, String desc_video_url, Company company) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.descVideoUrl = desc_video_url;
        this.company = company;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getDescVideoUrl() {
        return descVideoUrl;
    }

    public void setDescVideoUrl(String descVideoUrl) {
        this.descVideoUrl = descVideoUrl;
    }
}
