package com.example.hansung.anroidproject.model;
import java.util.List;

/**
 * Created by USER on 2017-12-09.
 */

public class Shop {

    /* Fields */
    private int id;

    private String name;

    private String imagePath;

    private String content;

    private String location;

    private boolean shopStatus; // 타입 고민..

    /* 아래 3개 shop 가능한 날 최소화 할지 논의 */
    private String time; // 가능 시간

    private String date; // 가능 날짜. 타입 고민

    private String day; // 가능 요일

    private List<Product> products; // 샵 내 제품 리스트

    /* Constructors */
    public Shop(){

    }

    /* getters and setters */
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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isShopStatus() {
        return shopStatus;
    }

    public void setShopStatus(boolean shopStatus) {
        this.shopStatus = shopStatus;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}