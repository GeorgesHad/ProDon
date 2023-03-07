package com.example.prodon.ui.sqliteHelper;

public class CoachModel {
    private String coachFName,coachLName;
    private int Id;

    public CoachModel(String coachFName, String coachLName, int id) {
        this.coachFName = coachFName;
        this.coachLName = coachLName;
        Id = id;
    }

    public String getCoachFName() {
        return coachFName;
    }

    public void setCoachFName(String coachFName) {
        this.coachFName = coachFName;
    }

    public String getCoachLName() {
        return coachLName;
    }

    public void setCoachLName(String coachLName) {
        this.coachLName = coachLName;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
