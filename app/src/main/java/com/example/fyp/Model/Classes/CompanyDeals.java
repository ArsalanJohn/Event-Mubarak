package com.example.fyp.Model.Classes;

public class CompanyDeals {
    private String Image;
    private String dealName;
    private String CompanyName;
    private String CompanyAddress;
    private String Price;

    public CompanyDeals() {
    }

    public CompanyDeals(String image, String dealName, String companyName, String companyAddress, String price) {
        Image = image;
        this.dealName = dealName;
        CompanyName = companyName;
        CompanyAddress = companyAddress;
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

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getCompanyAddress() {
        return CompanyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        CompanyAddress = companyAddress;
    }
}
