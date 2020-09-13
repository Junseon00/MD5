package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

//"하나의 row"가 담기는 그릇이 됨.
public class Drug {


    private String injection;
    private int num;
    private String component_code;
    private int drug_code;
    private String drug_name;
    private String company;
    private int size;
    private String unit;
    private String price;
    private String sj;



    //    @SerializedName("injection")
//    public String injection;
//    @SerializedName("num")
//    public String num;
//    @SerializedName("component_code")
//    public String component_code;
//    @SerializedName("durg_code")
//    public String drug_code;
//    @SerializedName("durg_name")
//    public String drug_name;
//    @SerializedName("company")
//    public String company;
//    @SerializedName("size")
//    public String size;
//    @SerializedName("unit")
//    public String unit;
//    @SerializedName("price")
//    public String price;
//    @SerializedName("sj")
//    public String sj;



//    @Override
//    public String toString() {
//        return "Drug{" + ", injection=" + injection + ", num=" + num +  ", component_code=" + component_code + ", drug_code=" + drug_code + ", drug_name=" + drug_name  + ", company=" + company + ", size=" + size + ", unit=" + unit + ", price=" + price + ", sj=" + sj + "}";
//    }

//    public Drug(String injection, String num, String component_code, String drug_code, String drug_name, String company, String size, String unit, String price, String sj) {
//        this.injection = injection;
//        this.num = num;
//        this.component_code = component_code;
//        this.drug_code = drug_code;
//        this.drug_name = drug_name;
//        this.company = company;
//        this.size = size;
//        this.unit = unit;
//        this.price = price;
//        this.sj = sj;
//    }

    public String getInjection() {
        return injection;
    }
    public int getNum() {
        return num;
    }

    public String getComponent_code() {
        return component_code;
    }

    public int getDrug_code() {
        return drug_code;
    }

    public String getDrug_name() {
        return drug_name;
    }

    public String getCompany() {
        return company;
    }

    public int getSize() {
        return size;
    }

    public String getUnit() {
        return unit;
    }

    public String getPrice() {
        return price;
    }

    public String getSj() {
        return sj;
    }


//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getPassword() {
//        return pw;
//    }
//
//    public void setPassword(String password) {
//        this.pw = password;
//    }






//    public String getHashedpw() {
//        return hashedpw;
//    }
//
//    public void setHashedpw(String name) {
//        this.hashedpw = hashedpw;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public String getbirth() {
//        return birth;
//    }
//
//    public void setbirth(String birth) {
//        this.birth = birth;
//    }

//    public String getOs() {
//        return os;
//    }
//
//    public void setOs(String os) {
//        this.os = os;
//    }
//
//    public String getModel() {
//        return model;
//    }
//
//    public void setModel(String model) {
//        this.model = model;
//    }
//
//    public String getUuid() {
//        return uuid;
//    }
//
//    public void setUuid(String uuid) {
//        this.uuid = uuid;
//    }

//    public String getToken() {
//        return token;
//    }
//
//    public void setToken(String token) {
//        this.token = token;
//    }
}
