package com.example.demo;

public class imageupload {

    String name,desc,price,imageurl;

    public imageupload(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public imageupload(String imageurl, String price, String name) {
        this.imageurl = imageurl;
        this.name = name;
        this.price = price;

    }

    public imageupload() {
    }

    public imageupload(String name, String desc, String price, String imageurl) {
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.imageurl = imageurl;
    }
}
