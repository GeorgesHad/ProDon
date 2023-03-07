package com.example.prodon.ui.sqliteHelper;

import androidx.annotation.Nullable;

public class PlayerModel {
    private String fName,lName,parentName,parentPhone,playerPhone;
    private int year;

    public PlayerModel(String fName, String lName, String parentName, @Nullable String parentPhone,@Nullable String playerPhone, int year) {
        this.fName = fName;
        this.lName = lName;
        this.parentName = parentName;
        this.parentPhone = parentPhone;
        this.playerPhone = playerPhone;
        this.year = year;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getParentPhone() {
        return parentPhone;
    }

    public void setParentPhone(String parentPhone) {
        this.parentPhone = parentPhone;
    }

    public String getPlayerPhone() {
        return playerPhone;
    }

    public void setPlayerPhone(String playerPhone) {
        this.playerPhone = playerPhone;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
