package com.anhtu.tuna.petmanager.model;

public class Dog {
    private String tvID,tvPrice,tvWeight;

    public Dog(String tvID, String tvPrice, String tvWeight) {
        this.tvID = tvID;
        this.tvPrice = tvPrice;
        this.tvWeight = tvWeight;
    }

    public String getTvID() {
        return tvID;
    }

    public void setTvID(String tvID) {
        this.tvID = tvID;
    }

    public String getTvPrice() {
        return tvPrice;
    }

    public void setTvPrice(String tvPrice) {
        this.tvPrice = tvPrice;
    }

    public String getTvWeight() {
        return tvWeight;
    }

    public void setTvWeight(String tvWeight) {
        this.tvWeight = tvWeight;
    }
}
