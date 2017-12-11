package com.example.hansung.anroidproject.model;

/**
 * Created by USER on 2017-12-10.
 */

public class Customer {
    /*
    userEmail 사용자 리스트 아이디(이메일)
    userName 사용자 리스트 이름
    profileImageUrl 사용자 프로필 사진
    //사용자 리스트 비밀번호
     */


    /* Fields */

    private String userEmail;

    private String userName; // 이름? 일단 google 또는 facebook의 이메일계정 @앞 아이디

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

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProfileImagePath() {
        return profileImagePath;
    }

    public void setProfileImagePath(String profileImagePath) {
        this.profileImagePath = profileImagePath;
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