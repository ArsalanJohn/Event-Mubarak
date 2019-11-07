package com.example.fyp.Model.Classes;

public class HomeCategory {
    private int HomeCatImg;
    private String HomeCatText;

    public HomeCategory(int homeCatImg, String homeCatText) {
        HomeCatImg = homeCatImg;
        HomeCatText = homeCatText;
    }


    public int getHomeCatImg() {
        return HomeCatImg;
    }

    public void setHomeCatImg(int homeCatImg) {
        HomeCatImg = homeCatImg;
    }

    public String getHomeCatText() {
        return HomeCatText;
    }

    public void setHomeCatText(String homeCatText) {
        HomeCatText = homeCatText;
    }
}
