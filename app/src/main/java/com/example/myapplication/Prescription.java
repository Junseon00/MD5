package com.example.myapplication;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import okhttp3.MultipartBody;

//"하나의 row"가 담기는 그릇이 됨.
public class Prescription {

    public int idi;
    public String user_id;
    public String drug_name;
    public String dose_size;
    public String dose_num;
    public String dose_day;


    public Prescription(int idi, String user_id, String drug_name, String dose_size, String dose_num, String dose_day) {
        this.idi = idi;
        this.user_id = user_id;
        this.drug_name = drug_name;
        this.dose_size = dose_size;
        this.dose_num = dose_num;
        this.dose_day = dose_day;
    }


    /*
    public ImageType(int id, JSONObject image) {
        this.id = id;
        this.image = image;
    }

     */


    public int getIdi() {
        return idi;
    }

    public String getUser_id() { return user_id; }

    public String getDrug_name(){
        return drug_name;
    }

    public String getDose_size(){
        return dose_size;
    }

    public String getDose_num(){
        return dose_num;
    }

    public String getDose_day(){
        return dose_day;
    }


}
