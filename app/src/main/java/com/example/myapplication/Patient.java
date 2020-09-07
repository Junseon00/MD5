package com.example.myapplication;

public class Patient {

    private String time;
    private String memo = "의약품"; //초기화
    private String yesno;

    public Patient(String t, String m, String yn){
        this.time = t;
        this.memo = m;
        this.yesno = yn; //X, O
    }

    //getters

    public String getTime(){ return time;}

    public String getMemo(){return memo;}

    public String getYesno(){return yesno;}

    //seters

    public void setTime(String t){this.time = t;}
    public void setMemo(String m){this.memo = m;}
    public void setYesno(String yn){this.yesno= yn;}

}
