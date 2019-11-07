package com.example.fyp.Model.Classes;

public class VendorsList {
    private String vendorsImg;
    private String vendorsName;
    private String vendorsAddress;

    public VendorsList() {
    }

    public VendorsList(String vendorsImg, String vendorsName, String vendorsAddress) {
        this.vendorsImg = vendorsImg;
        this.vendorsName = vendorsName;
        this.vendorsAddress = vendorsAddress;
    }


    public String getVendorsImg() {
        return vendorsImg;
    }

    public void setVendorsImg(String vendorsImg) {
        this.vendorsImg = vendorsImg;
    }

    public String getVendorsName() {
        return vendorsName;
    }

    public void setVendorsName(String vendorsName) {
        this.vendorsName = vendorsName;
    }

    public String getVendorsAddress() {
        return vendorsAddress;
    }

    public void setVendorsAddress(String vendorsAddress) {
        this.vendorsAddress = vendorsAddress;
    }
}
