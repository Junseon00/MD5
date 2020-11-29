package com.example.myapplication;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import okhttp3.MultipartBody;

//"하나의 row"가 담기는 그릇이 됨.
public class ImageType {

    public int id;
    public String uid;
    //public JSONObject image;
    public MultipartBody.Part img;


    public ImageType(int id, MultipartBody.Part img, String uid) {
        this.id = id;
        this.img = img;
        this.uid = uid;
    }


    /*
    public ImageType(int id, JSONObject image) {
        this.id = id;
        this.image = image;
    }

     */


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUid(String uid) { this.uid = uid;}

    /*
    public JSONObject getImage() {
        return image;
    }

    public void setImage(JSONObject image) {
        this.image = image;
    }

     */

}
