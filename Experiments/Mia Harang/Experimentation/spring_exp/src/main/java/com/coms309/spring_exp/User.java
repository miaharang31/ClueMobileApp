package com.coms309.spring_exp;

public class User {
    String name;
    String number;
    int ID;

    public User(String name, String number, int id) {
        this.name = name;
        this.number = number;
        ID = id;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void changeNumber(String number) {
        this.number = number;
    }

    public void changeID(int id) {
        ID = id;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }
}
