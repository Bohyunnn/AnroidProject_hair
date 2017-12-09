package com.example.hansung.anroidproject.model;

/**
 * Created by USER on 2017-12-06.
 */

public class Product {

    /* Fields */
    private int id;

    private String name;

    private int price;

    /* Constructors */
    public Product(){

    }

    public Product(String name, int price){
        this.name = name;
        this.price = price;
    }

    /* Getters and setters */
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
