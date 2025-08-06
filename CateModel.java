package com.example.webtoon;

public class CateModel {

    private String cateName;

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String getCateName() {
        return cateName;
    }

    public CateModel(String cateName) {
        this.cateName = cateName;
    }
}
