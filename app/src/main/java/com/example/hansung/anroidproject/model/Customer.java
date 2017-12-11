package com.example.hansung.anroidproject.model;

/**
 * Created by USER on 2017-12-10.
 */

public class Customer {

    /* Fields */
    private int id;

    private String email;

    private String name; // 이름? 일단 google 또는 facebook의 이메일계정 @앞 아이디

    private String profileImagePath;

    private String uid; // 보현이 파이어베이스 채팅

    private String pushToken; // 보현이 파이어베이스 채팅

//    private String principal; // Google / Facebook 키. Firebase에 필요한지 모르겠음.일단보류

//    private SocialType socialType; // Google 인지 Facebook인지

//    private AuthorityType authorityType; // customer인지 stylist인지

    /* Constructors */
    public Customer(){

    }

    /* Getters and seters */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImagePath() {
        return profileImagePath;
    }

    public void setProfileImagePath(String profileImagePath) {
        this.profileImagePath = profileImagePath;
    }

    public String getUid(){
        return this.uid;
    }

    public void setUid(String uid){
        this.uid = uid;
    }

    public String getPushToken(){
        return this.pushToken;
    }

    public void setPushToken(String pushToken){
        this.pushToken = pushToken;
    }
}