package com.example.hansung.anroidproject.model;

/**
 * Created by USER on 2017-12-10.
 */

public class Customer {

    /* Fields */
    private int id;

    private String email;

    private String username; // 실명?? 일단 google 또는 facebook의 @앞 아이디

    private String profileImagePath;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfileImagePath() {
        return profileImagePath;
    }

    public void setProfileImagePath(String profileImagePath) {
        this.profileImagePath = profileImagePath;
    }
}
