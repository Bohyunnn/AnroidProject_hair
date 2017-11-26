package com.example.hansung.anroidproject;

import java.util.List;


public class Product {
    private List<Product> productList;

    private int productCode;  //제품 코드
    private String productName;  //제품 이름
    private int productPrice;   //제품 가격
    private Store store;

    public Product() {
    }

    public Product(String productName, int productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }
}
