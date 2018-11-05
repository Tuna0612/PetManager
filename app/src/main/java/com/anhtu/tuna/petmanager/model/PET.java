package com.anhtu.tuna.petmanager.model;

public class PET {
    String mID,mLoai,mWeight,mHealth,mTiem,mPrice;

    public PET() {
    }

    public PET(String mLoai, String mWeight, String mHealth, String mTiem, String mPrice) {
        this.mLoai = mLoai;
        this.mWeight = mWeight;
        this.mHealth = mHealth;
        this.mTiem = mTiem;
        this.mPrice = mPrice;
    }

    public PET(String mID, String mLoai, String mWeight, String mHealth, String mTiem, String mPrice) {
        this.mID = mID;
        this.mLoai = mLoai;
        this.mWeight = mWeight;
        this.mHealth = mHealth;
        this.mTiem = mTiem;
        this.mPrice = mPrice;
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

    public String getmTiem() {
        return mTiem;
    }

    public void setmTiem(String mTiem) {
        this.mTiem = mTiem;
    }

    public String getmPrice() {
        return mPrice;
    }

    public void setmPrice(String mPrice) {
        this.mPrice = mPrice;
    }
}
