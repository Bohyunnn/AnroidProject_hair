package com.example.hansung.anroidproject.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kimsungmin on 2017-12-12.
 */

public class BookModel {

    /*
       //name = intent.getStringExtra("ProductName");
        //price = intent.getStringExtra("ProductPrice");
       // profileImageUrl = getIntent().getStringExtra("StylistImageUrl");
       // ShopName = getIntent().getStringExtra("StoreName");
        //StylistName = getIntent().getStringExtra("StylistName");
        //stylistuid = intent.getStringExtra("destinationUid");

     */

    private String bookUser;
    private String bookStylist;
    private String StylistName;
    private String ShopName;
    private String ProductName;
    private String ProductPrice;
    private String bookTime;
    private String bookDate;
    private String StoreAddress;
    private String uid;


    public String getStoreAddress() {
        return StoreAddress;
    }

    public void setStoreAddress(String storeAddress) {
        StoreAddress = storeAddress;
    }

    public String getBookUser() {
        return bookUser;
    }

    public String getStylistName() {
        return StylistName;
    }

    public void setStylistName(String stylistName) {
        StylistName = stylistName;
    }

    public String getShopName() {
        return ShopName;
    }

    public void setShopName(String shopName) {
        ShopName = shopName;
    }

    public void setBookUser(String bookUser) {
        this.bookUser = bookUser;
    }

    public String getBookStylist() {
        return bookStylist;
    }

    public void setBookStylist(String bookStylist) {
        this.bookStylist = bookStylist;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(String productPrice) {
        ProductPrice = productPrice;
    }

    public String getBookTime() {
        return bookTime;
    }

    public void setBookTime(String bookTime) {
        this.bookTime = bookTime;
    }

    public String getBookDate() {
        return bookDate;
    }

    public void setBookDate(String bookDate) {
        this.bookDate = bookDate;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
