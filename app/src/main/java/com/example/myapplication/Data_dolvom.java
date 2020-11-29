package com.example.myapplication;

public class Data_dolvom {
    private int id;
    private String name;//신분
    //private Image img; 이미지는 아직 추가 안함★

    public String getName(){return name;}
    public int getId(){return id;}
    //public Image getImg(){return img;}★

    public void setId(int id){this.id= id;}
    public void setName(String name){this.name = name;}
    //public void setImg(Image img){this.img = img;}★

    //생성자
    public Data_dolvom(int id,String name){this.name = name; this.id = id; //★이미지는 아직
    }
}
