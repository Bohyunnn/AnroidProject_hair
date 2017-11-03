package com.example.hansung.anroidproject;

/**
 * Created by kimsungmin on 2017-11-03.
 */

public class Store {
    private String address; //미용실 위치
    private int storeImage;  //미용실 사진
    private String storeName; //미용사 가게 이름
    private String name;  //미용사 이름

    public Store() {
    }

    public Store(String address, int storeImage, String storeName, String name) {
        this.address = address;
        this.storeImage = storeImage;
        this.storeName = storeName;
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStoreImage() {
        return storeImage;
    }

    public void setStoreImage(int storeImage) {
        this.storeImage = storeImage;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
