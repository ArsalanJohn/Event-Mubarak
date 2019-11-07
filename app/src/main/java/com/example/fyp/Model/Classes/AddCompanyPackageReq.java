package com.example.fyp.Model.Classes;

public class AddCompanyPackageReq {

    private String pkgName;
    private String pkgPrice;
    private String pkgDes;
    private String companyId;
    private String pkgImage;
    private String Type;


    public AddCompanyPackageReq() {
    }

    public AddCompanyPackageReq(String pkgName, String pkgPrice, String pkgDes, String companyId, String pkgImage, String type) {
        this.pkgName = pkgName;
        this.pkgPrice = pkgPrice;
        this.pkgDes = pkgDes;
        this.companyId = companyId;
        this.pkgImage = pkgImage;
        Type = type;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getPkgImage() {
        return pkgImage;
    }

    public void setPkgImage(String pkgImage) {
        this.pkgImage = pkgImage;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public String getPkgPrice() {
        return pkgPrice;
    }

    public void setPkgPrice(String pkgPrice) {
        this.pkgPrice = pkgPrice;
    }

    public String getPkgDes() {
        return pkgDes;
    }

    public void setPkgDes(String pkgDes) {
        this.pkgDes = pkgDes;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
}
