package com.org.jbossoutreach.Models;

public class UserModel {
    String name, image_url;

    public UserModel() {
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public UserModel(String name, String image_url) {

        this.name = name;
        this.image_url = image_url;
    }
}
