package com.pqt.phamquangthanh.web_service;

public class User {
    private int id;
    private String name;
    private String username;
    private String email;
    private String phone;
    private String website;
    private String img_avatar;

    public User(int id, String name, String username, String email, String phone, String website, String address_img) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.website = website;
        this.img_avatar = address_img;
    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getImg_avatar() {
        return img_avatar;
    }

    public void setImg_avatar(String img_avatar) {
        this.img_avatar = img_avatar;
    }
}
