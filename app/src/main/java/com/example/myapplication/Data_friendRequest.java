package com.example.myapplication;

public class Data_friendRequest {
    private int id;
    private String name;//신분
    //private String phone;//전화번호 보류
    //private Image img; 이미지는 아직 추가 안함★

    public String getName(){return name;}
    //public String getPhone(){return phone;}
    public int getId(){return id;}
    //public Image getImg(){return img;}★

    public void setId(int id){this.id= id;}
    //public void setPhone(String phone){this.name = phone;}
    public void setName(String name){this.name = name;}
    //public void setImg(Image img){this.img = img;}★

    //생성자
    public Data_friendRequest(int id, String name){this.name = name; this.id = id;
    // public Data_friendRequest(int id, String name, String phone){this.name = name; this.id = id; this.phone=phone;//★이미지는 아직
    }
}
