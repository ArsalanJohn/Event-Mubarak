package com.example.fyp.Model.Classes;

import java.util.ArrayList;

public class Users {
    private String name;
    private String email;
    private String cnic;
    private String phone;
    private String userType;
    private String city;
    private String Profileimage;
    private String address;




    public Users() {
    }

    public Users(String name, String email, String cnic, String phone, String userType, String city, String profileimage, String address) {
        this.name = name;
        this.email = email;
        this.cnic = cnic;
        this.phone = phone;
        this.userType = userType;
        this.city = city;
        Profileimage = profileimage;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProfileimage() {
        return Profileimage;
    }

    public void setProfileimage(String profileimage) {
        Profileimage = profileimage;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }


}
