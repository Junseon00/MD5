package com.example.myapplication;

public class DB_request {

    public String idA;
    public String idB;


    public DB_request(String idA, String idB) {
        this.idA = idA;
        this.idB = idB;
    }


    public String getIdA() {
        return idA;
    }
    public void setIdA(String idA) {
        this.idA = idA;
    }


    public String getIdB() {
        return idB;
    }
    public void setIdB(String idB) {
        this.idB = idB;
    }

}