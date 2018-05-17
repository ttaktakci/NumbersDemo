package com.company.model;

public class Entity {
    private long number;
    private String insertDate;

    public Entity() { }

    public Entity(long number, String insertDate) {
        this.number = number;
        this.insertDate = insertDate;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(String insertDate) {
        this.insertDate = insertDate;
    }
}
