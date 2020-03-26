package com.example.interactivestory.model;

// Created by Taha Siddiqui
// 2020/03/19
public class Page {
    private int imageId;
    private int textId;
    private boolean isFinalPage = false;
    private  Choice choice1;
    private Choice choise2;

    public Page(int imageId, int textId) {
        this.imageId = imageId;
        this.textId = textId;
        this.isFinalPage = true;
    }

    public Page(int imageId, int textId, Choice choice1, Choice choise2) {
        this.imageId = imageId;
        this.textId = textId;
        this.choice1 = choice1;
        this.choise2 = choise2;
    }

    public boolean isFinalPage() {
        return isFinalPage;
    }

    public void setFinalPage(boolean finalPage) {
        isFinalPage = finalPage;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getTextId() {
        return textId;
    }

    public void setTextId(int textId) {
        this.textId = textId;
    }

    public Choice getChoice1() {
        return choice1;
    }

    public void setChoice1(Choice choice1) {
        this.choice1 = choice1;
    }

    public Choice getChoise2() {
        return choise2;
    }

    public void setChoise2(Choice choise2) {
        this.choise2 = choise2;
    }
}
