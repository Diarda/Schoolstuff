package com.example.d851.myproject;

/**
 * Created by D851 on 21/11/2019.
 */

public class Product
{
    private String id;
    private String name;
    private int amountofCarbs;

    public Product(String id, String name, int amountofCarbs) {
        this.id = id;
        this.name = name;
        this.amountofCarbs = amountofCarbs;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmountofCarbs() {
        return amountofCarbs;
    }

    public void setAmountofCarbs(int amountofCarbs) {
        this.amountofCarbs = amountofCarbs;
    }
}
