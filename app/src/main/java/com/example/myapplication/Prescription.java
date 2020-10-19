package com.example.myapplication;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import okhttp3.MultipartBody;

//"하나의 row"가 담기는 그릇이 됨.
public class Prescription {

    public int idi;
    public String user_id;
    public String doctor_number;
    public String drug_name;
    public float dose_size;
    public int dose_num;
    public int dose_day;


    public Prescription(int idi, String user_id, String doctor_number, String drug_name, float dose_size, int dose_num, int dose_day) {
        this.idi = idi;
        this.user_id = user_id;
        this.doctor_number = doctor_number;
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
    public String getDoctor_number(){
        return doctor_number;
    }

    public String getDrug_name(){
        return drug_name;
    }

    public float getDose_size(){
        return dose_size;
    }

    public int getDose_num(){
        return dose_num;
    }

    public int getDose_day(){
        return dose_day;
    }


}
