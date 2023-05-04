package com.example.prodon.ui.sqliteHelper;
public class Payment {
    private int id;
    private int playerId;
    private int year;
    private int month;
    private int amount;

    public Payment(int id, int playerId, int year, int month, int amount) {
        this.id = id;
        this.playerId = playerId;
        this.year = year;
        this.month = month;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

