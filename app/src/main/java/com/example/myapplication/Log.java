package com.example.myapplication;

public class Log {

    private int id; //행 구별자
    private byte[] picture; //찍은 사진
    private String taking; //복용중 or 복용종료
    private String pills; //인식한 약들
    private String date; //검색한 날
    private String due; //약 복용 종료일
    private String warning; //주의사항 발견 여부
    private String detail; //주의사항 자세히

    public Log(int id,String tak,String pill,
               String date,String due,String warn,
               String detail,byte[] pic){
        this.id = id;
        this.picture = pic;
        this.taking = tak;
        this.pills = pill;
        this.date = date;
        this.due = due;
        this.warning = warn;
        this.detail = detail;
    }

    public Log(int id,String tak,String pill,
               String date,String warn){
        this.id = id;
        this.picture = null;
        this.taking = tak;
        this.pills = pill;
        this.date = date;
        this.due = null;
        this.warning = warn;
        this.detail = null;
    }

    public int getId(){ return id; }
    public byte[] getPicture(){ return picture; }
    public String gettaking(){return taking;}
    public String getPills(){return pills;}
    public String getdate(){return date;}
    public String getdue(){return due;}
    public String getwarning(){return warning;}
    public String getdetail(){return detail;}


    public void setDate(String date) { this.date = date; }
    public void setId(int id){this.id = id;}
    public void setPicture(byte[] pic){this.picture=pic;}
    public void setTaking(String tak){this.taking = tak;}
    public void setPills(String pill){this.pills=pill;}
    public void setDue(String due){this.due = due;}
    public void setWarning(String warning){this.warning = warning;}
    public void setDetail(String detail){this.detail = detail;}
}