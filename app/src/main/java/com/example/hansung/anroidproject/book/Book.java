package com.example.hansung.anroidproject.book;

import com.example.hansung.anroidproject.deprecated.model.Store;

/**
 * Created by kimsungmin on 2017-12-06.
 */

public class Book {
    private int book_code;
    private String book_date;
    private Store shopcode;
    private int user_id;

    public Book(String book_date) {
        this.book_date = book_date;
    }

    public Book(int book_code, String book_date, Store shopcode, int user_id) {
        this.book_code = book_code;
        this.book_date = book_date;
        this.shopcode = shopcode;
        this.user_id = user_id;
    }

    public Book(String book_date, Store shopcode, int user_id) {
        this.book_date = book_date;
        this.shopcode = shopcode;
        this.user_id = user_id;
    }

    public int getBook_code() {
        return book_code;
    }

    public void setBook_code(int book_code) {
        this.book_code = book_code;
    }

    public String getBook_date() {
        return book_date;
    }

    public void setBook_date(String book_date) {
        this.book_date = book_date;
    }

    public Store getShopcode() {
        return shopcode;
    }

    public void setShopcode(Store shopcode) {
        this.shopcode = shopcode;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
