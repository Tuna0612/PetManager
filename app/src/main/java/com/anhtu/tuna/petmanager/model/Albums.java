package com.anhtu.tuna.petmanager.model;

public class Albums {

    public static String DOG = "dog";
    public static String CAT = "cat";

    private String name;
    private int numOfPet;
    private int thumbnail;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Albums() {
    }

    public Albums(String name, int numOfPet, int thumbnail) {
        this.name = name;
        this.numOfPet = numOfPet;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfPet() {
        return numOfPet;
    }

    public void setNumOfPet(int numOfPet) {
        this.numOfPet = numOfPet;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
