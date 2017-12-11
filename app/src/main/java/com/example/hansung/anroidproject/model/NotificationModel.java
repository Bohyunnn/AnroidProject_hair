package com.example.hansung.anroidproject.model;


/**
 * Created by USER on 2017-12-10.
 */

public class NotificationModel { // 보현이 파이어베이스 채팅

    /* Fields */
    private String to;

    public Notification notification = new Notification();

    public static class Notification{
        public String title;
        public String text;
    }
}
