package com.example.librarysystem;

public abstract class Person {
    protected String name;
    protected String email;
    protected String mobile;
    String massage;

    Person()
    {

    }
    Person(String name , String email , String mobile) {
        this.name=name;
        this.email=email;
        this.mobile =mobile;

    }


    abstract void update(String name, String email, String column);

    abstract void delete(String email);

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getMassage() {
        return massage;
    }
}
