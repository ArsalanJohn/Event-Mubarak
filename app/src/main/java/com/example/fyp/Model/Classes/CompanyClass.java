package com.example.fyp.Model.Classes;

import java.util.ArrayList;

public class CompanyClass {
   private String Comp_Name;
    private  String  Comp_Address;
    private String  Comp_Num;
    private  String  Comp_Owner;
    private String  Comp_Type;
    private  String  Comp_Estab;
    private  String  Comp_Sp;
    private  ArrayList<String> Comp_images;


    public CompanyClass() {
    }

    public CompanyClass(String comp_Name, String comp_Address, String comp_Num, String comp_Owner, String comp_Type, String comp_Estab, String comp_Sp, ArrayList<String> comp_images) {
        Comp_Name = comp_Name;
        Comp_Address = comp_Address;
        Comp_Num = comp_Num;
        Comp_Owner = comp_Owner;
        Comp_Type = comp_Type;
        Comp_Estab = comp_Estab;
        Comp_Sp = comp_Sp;
        Comp_images = comp_images;
    }

    public String getComp_Name() {
        return Comp_Name;
    }

    public void setComp_Name(String comp_Name) {
        Comp_Name = comp_Name;
    }

    public String getComp_Address() {
        return Comp_Address;
    }

    public void setComp_Address(String comp_Address) {
        Comp_Address = comp_Address;
    }

    public String getComp_Num() {
        return Comp_Num;
    }

    public void setComp_Num(String comp_Num) {
        Comp_Num = comp_Num;
    }

    public String getComp_Owner() {
        return Comp_Owner;
    }

    public void setComp_Owner(String comp_Owner) {
        Comp_Owner = comp_Owner;
    }

    public String getComp_Type() {
        return Comp_Type;
    }

    public void setComp_Type(String comp_Type) {
        Comp_Type = comp_Type;
    }

    public String getComp_Estab() {
        return Comp_Estab;
    }

    public void setComp_Estab(String comp_Estab) {
        Comp_Estab = comp_Estab;
    }

    public String getComp_Sp() {
        return Comp_Sp;
    }

    public void setComp_Sp(String comp_Sp) {
        Comp_Sp = comp_Sp;
    }

    public ArrayList<String> getComp_images() {
        return Comp_images;
    }

    public void setComp_images(ArrayList<String> comp_images) {
        Comp_images = comp_images;
    }
}
