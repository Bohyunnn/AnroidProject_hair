package com.example.hansung.anroidproject.model;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by USER on 2017-12-10.
 */

public class ChatModel { // 보현이 파이어베이스 채팅

    public Map<String, Boolean> users = new HashMap<>(); // 채팅방의 유저들

    public Map<String, Comment> comments = new HashMap<>(); // 채팅방의 대화 내용

    public static class Comment {
        public String uid;
        public String message;
        public Object timestamp;
    }
}
