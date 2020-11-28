package com.example.myapplication;

public class DB_mission {

    public int miID;
    public String idA;
    public String idB;
    public String logM;
    public boolean auto;
    public String answer;


    public DB_mission(int miID, String idA, String idB, String logM, boolean auto, String answer) {
        this.miID = miID;
        this.idA = idA;
        this.idB = idB;
        this.logM = logM;
        this.auto = auto;
        this.answer = answer;
    }

    public int getMiID() {
        return miID;
    }
    public void setMiID(int miID) {
        this.miID = miID;
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



    public String getLogM() {
        return logM;
    }
    public void setLogM(String logM) {
        this.logM = logM;
    }


    public Boolean getAuto() {
        return auto;
    }
    public void setAuto(boolean auto) {
        this.auto = auto;
    }


    public String getAnswer() {
        return idA;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }

}
