package com.example.myapplication;

//"하나의 row"가 담기는 그릇이 됨.
public class User {
    int id;
    String password;
    String name;
    int age;

    public User(){

    }

    @Override
    public String toString() {
        return "User{" +
                ", id=" + id +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

    public User(int id, String password, String name, int age, String token) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.age = age;
    }

    private String token;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setNumber(int age) {
        this.age = age;
    }

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
