package com.example.fyp.Model.Classes;

public class AddCompanyPackage {

    private String pkgName;
    private String pkgPrice;
    private String pkgDes;
    private String companyId;
    private String pkgImage;

    public AddCompanyPackage() {
    }

    public AddCompanyPackage(String pkgName, String pkgPrice, String pkgDes, String companyId, String pkgImage) {
        this.pkgName = pkgName;
        this.pkgPrice = pkgPrice;
        this.pkgDes = pkgDes;
        this.companyId = companyId;
        this.pkgImage = pkgImage;
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
