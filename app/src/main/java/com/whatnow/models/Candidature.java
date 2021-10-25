package com.whatnow.models;

public class Candidature {

    private String id;
    private Offer offer;

    public Candidature() {
    }

    public Candidature(String id, Offer offer) {
        this.id = id;
        this.offer = offer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }
}
