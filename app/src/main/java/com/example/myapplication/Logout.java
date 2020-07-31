package com.example.myapplication;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//"하나의 row"가 담기는 그릇이 됨.
public class Logout {

    @SerializedName("success")
    @Expose
    private String success;

    public Logout(){
    }


    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}


