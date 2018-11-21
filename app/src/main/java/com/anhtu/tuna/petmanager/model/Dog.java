package com.anhtu.tuna.petmanager.model;

public class Dog {
    String mID,mLoai,mWeight,mHealth,mInjected,mPrice;
    private byte[] image;


    public Dog() {
    }

    public Dog(String mID,  String mWeight, String mHealth, String mInjected, String mPrice, byte[] image) {
        this.mID = mID;
        this.mWeight = mWeight;
        this.mHealth = mHealth;
        this.mInjected = mInjected;
        this.mPrice = mPrice;
        this.image = image;
    }

    public Dog(String mID, String mLoai, String mWeight, String mHealth, String mInjected, String mPrice) {
        this.mID = mID;
        this.mLoai = mLoai;
        this.mWeight = mWeight;
        this.mHealth = mHealth;
        this.mInjected = mInjected;
        this.mPrice = mPrice;
    }

    public Dog(String mID, String mLoai, String mWeight, String mHealth, String mInjected, String mPrice, byte[] image) {
        this.mID = mID;
        this.mLoai = mLoai;
        this.mWeight = mWeight;
        this.mHealth = mHealth;
        this.mInjected = mInjected;
        this.mPrice = mPrice;
        this.image = image;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
