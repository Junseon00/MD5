package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

//"하나의 row"가 담기는 그릇이 됨.
public class User {
    @SerializedName("id")
    public String id;
    @SerializedName("pw")
    public String pw;
//    @SerializedName("hashedpw")
//    public String hashedpw;
    @SerializedName("phone")
    public String phone;
    @SerializedName("birth")
    public String birth;





//    public String id;
//    public String pw;
//    String hashedpw;
//    String phone;
//    String birth;


    @Override
    public String toString() {
        return "User{" + ", id=" + id + ", pw='" + pw +  ", phone=" + phone + ", birth=" + birth + '\'' +"}";
    }

    public User(String id, String password, String phone, String birth) {
        this.id = id;
        this.pw = password;
//        this.hashedpw = hashedpw;
        this.phone = phone;
        this.birth = birth;
    }

    private String token;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return pw;
    }

    public void setPassword(String password) {
        this.pw = password;
    }






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
