package com.whatnow.models;

public class Offer {

    private String id;
    private String name;
    private String desc;
    private String desc_video_url;
    private Company company;

    public Offer() {
    }

    public Offer(String id, String name, String desc, String desc_video_url, Company company) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.desc_video_url = desc_video_url;
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

    public String getDesc_video_url() {
        return desc_video_url;
    }

    public void setDesc_video_url(String desc_video_url) {
        this.desc_video_url = desc_video_url;
    }
}
