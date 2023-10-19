package com.example.demo;

public class storedata {
    public String getIsuser() {
        return isuser;
    }

    public void setIsuser(String isuser) {
        this.isuser = isuser;
    }

    public storedata(String username, String email, String mobile, String name, String password, String isuser) {
        this.username = username;
        this.email = email;
        this.mobile = mobile;
        this.name = name;
        this.password = password;
        this.isuser = isuser;
    }

    public storedata() {
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    String username,email,mobile,name,password,isuser;

}
