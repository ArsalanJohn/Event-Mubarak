package com.example.fyp.Model.Classes;

public class CompanyPackages {
    private String Image;
    private String dealName;
    private String Price;

    public CompanyPackages() {
    }

    public CompanyPackages(String image, String dealName, String price) {
        Image = image;
        this.dealName = dealName;
        Price = price;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getDealName() {
        return dealName;
    }

    public void setDealName(String dealName) {
        this.dealName = dealName;
    }


}
