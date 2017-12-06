package com.example.hansung.anroidproject;

/**
 * Created by kimsungmin on 2017-12-06.
 */

public class User {
    private int id;
    private boolean stylist;

    public User(int id, boolean stylist) {
        this.id = id;
        this.stylist = stylist;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isStylist() {
        return stylist;
    }

    public void setStylist(boolean stylist) {
        this.stylist = stylist;
    }
}
