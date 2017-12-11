package com.example.hansung.anroidproject.model;
/**
 * Created by USER on 2017-12-10.
 */

public class Stylist {
    /*
    profileImageUrl 스타일 리스트 가게 이미지
    stylistId 스타일 리스트 아이디(이메일)
    stylistName 스타일 리스트 이름
    stylistAddress 스타일 리스트 주소
    storeName 스타일 리스트 가게 이름
    stylistPhone 스타일 리스트 전화번호
    //스타일 리스트 비밀번호
     */

    /* Fields */
    private String profileImageUrl;

    private String stylistEmail;

    private String stylistName;

    private String stylistAddress;

    private String shopName;

    private String stylistPhone;

    private String uid;
    private String pushToken;



    /* Constructors */
    public Stylist(){

    }

    /* Getters and setters */

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getStylistEmail() {
        return stylistEmail;
    }

    public void setStylistEmail(String stylistEmail) {
        this.stylistEmail = stylistEmail;
    }

    public String getStylistName() {
        return stylistName;
    }

    public void setStylistName(String stylistName) {
        this.stylistName = stylistName;
    }

    public String getStylistAddress() {
        return stylistAddress;
    }

    public void setStylistAddress(String stylistAddress) {
        this.stylistAddress = stylistAddress;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getStylistPhone() {
        return stylistPhone;
    }

    public void setStylistPhone(String stylistPhone) {
        this.stylistPhone = stylistPhone;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPushToken() {
        return pushToken;
    }

    public void setPushToken(String pushToken) {
        this.pushToken = pushToken;
    }
}