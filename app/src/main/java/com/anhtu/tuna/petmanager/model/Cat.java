package com.anhtu.tuna.petmanager.model;

public class Cat {
    String mID,mLoai,mWeight,mHealth,mInjected,mPrice;



    public Cat(String mID, String mLoai, String mWeight, String mHealth, String mInjected, String mPrice) {
        this.mID = mID;
        this.mLoai = mLoai;
        this.mWeight = mWeight;
        this.mHealth = mHealth;
        this.mInjected = mInjected;
        this.mPrice = mPrice;
    }

    public Cat(String mID, String mLoai, String mWeight, String mHealth, String mPrice) {
        this.mID = mID;
        this.mLoai = mLoai;
        this.mWeight = mWeight;
        this.mHealth = mHealth;
        this.mPrice = mPrice;
    }

    public Cat() {
    }

    public String getmID() {
        return mID;
    }

    public void setmID(String mID) {
        this.mID = mID;
    }

    public String getmLoai() {
        return mLoai;
    }

    public void setmLoai(String mLoai) {
        this.mLoai = mLoai;
    }

    public String getmWeight() {
        return mWeight;
    }

    public void setmWeight(String mWeight) {
        this.mWeight = mWeight;
    }

    public String getmHealth() {
        return mHealth;
    }

    public void setmHealth(String mHealth) {
        this.mHealth = mHealth;
    }

    public String getmInjected() {
        return mInjected;
    }

    public void setmInjected(String mInjected) {
        this.mInjected = mInjected;
    }

    public String getmPrice() {
        return mPrice;
    }

    public void setmPrice(String mPrice) {
        this.mPrice = mPrice;
    }
}
