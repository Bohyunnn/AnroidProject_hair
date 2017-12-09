package com.example.hansung.anroidproject.model;

/**
 * Created by USER on 2017-12-10.
 */

public class Stylist {

    /* Fields */
    private int id;

    private String name;

    private String phone;

    private String licenseImagePath;

    private String location;

    /* Constructors */
    public Stylist(){

    }

    /* Getters and setters */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLicenseImagePath() {
        return licenseImagePath;
    }

    public void setLicenseImagePath(String licenseImagePath) {
        this.licenseImagePath = licenseImagePath;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
