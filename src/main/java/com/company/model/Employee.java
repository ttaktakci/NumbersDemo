package com.company.model;

public class Employee extends Entity {
    private String name;

    public Employee () { }

    public Employee(long number, String name, String insertDate) {
        super(number, insertDate);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
