package com.example.demo;

public class bookingmodel {
    public bookingmodel() {
    }

    String date,name,email,person;

    public bookingmodel(String date, String name, String email,String person) {
        this.date = date;
        this.name = name;
        this.email = email;
        this.person = person;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
