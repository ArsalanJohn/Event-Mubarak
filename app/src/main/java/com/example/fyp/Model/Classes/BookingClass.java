package com.example.fyp.Model.Classes;

public class BookingClass {
    private String userId;
    private String companyId;
    private String dealPackageId;
    private String dd;
    private String mm;
    private String yy;
    private String hr;
    private String mn;
    private String type;

    public BookingClass() {

    }

    public BookingClass(String userId, String companyId, String dealPackageId, String dd, String mm, String yy, String hr, String mn, String type) {
        this.userId = userId;
        this.companyId = companyId;
        this.dealPackageId = dealPackageId;
        this.dd = dd;
        this.mm = mm;
        this.yy = yy;
        this.hr = hr;
        this.mn = mn;
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getDealPackageId() {
        return dealPackageId;
    }

    public void setDealPackageId(String dealPackageId) {
        this.dealPackageId = dealPackageId;
    }

    public String getDd() {
        return dd;
    }

    public void setDd(String dd) {
        this.dd = dd;
    }

    public String getMm() {
        return mm;
    }

    public void setMm(String mm) {
        this.mm = mm;
    }

    public String getYy() {
        return yy;
    }

    public void setYy(String yy) {
        this.yy = yy;
    }

    public String getHr() {
        return hr;
    }

    public void setHr(String hr) {
        this.hr = hr;
    }

    public String getMn() {
        return mn;
    }

    public void setMn(String mn) {
        this.mn = mn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
