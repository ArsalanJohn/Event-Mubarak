package com.example.fyp.Model.Classes;

public class RequestBookings {
    private int bookingImg;
    private String UserName;
    private String CompanyName;
    private String BookingDate;

    public RequestBookings() {
    }

    public RequestBookings(int bookingImg, String userName, String companyName, String bookingDate) {
        this.bookingImg = bookingImg;
        UserName = userName;
        CompanyName = companyName;
        BookingDate = bookingDate;
    }

    public int getBookingImg() {
        return bookingImg;
    }

    public void setBookingImg(int bookingImg) {
        this.bookingImg = bookingImg;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getBookingDate() {
        return BookingDate;
    }

    public void setBookingDate(String bookingDate) {
        BookingDate = bookingDate;
    }
}
