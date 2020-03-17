package com.example.d851.myproject;

/**
 * Created by D851 on 04/12/2019.
 */

public class UserInformation {
    private int id;
    private String name;
    private int age;
    private double height;
    private String type;
    private String password;

    public UserInformation(int id, String name, int age, double height, String type, String password) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.height = height;
        this.type = type;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
