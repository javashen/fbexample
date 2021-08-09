package com.miantiao.fbexample;

public class FbUser {

    protected String userName;
    protected String sex;
    protected Integer age;
    protected String address;
    protected String email;

    public FbUser(String userName, String sex, Integer age, String address, String email) {
        this.userName = userName;
        this.sex = sex;
        this.age = age;
        this.address = address;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public Integer getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getSex() {
        return sex;
    }
}
