package com.example.fyp.Model.Classes;

public class RequestListClass {

    private String PkgName;
    private String Address;
    private String Img;

    public RequestListClass() {
    }

    public RequestListClass(String pkgName, String address, String img) {
        PkgName = pkgName;
        Address = address;
        Img = img;
    }


    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
        Img = img;
    }

    public String getPkgName() {
        return PkgName;
    }

    public void setPkgName(String pkgName) {
        PkgName = pkgName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
