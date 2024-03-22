package com.test.tnpconnect.Model;

import com.google.firebase.Timestamp;

public class UserModel {
    private String phone;
    private String userName;
    private Timestamp createdTimeStamp;
    private String userId;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;

    public UserModel() {

    }

    public UserModel(String userId, String phone, String userName, Timestamp createdTimeStamp, String mail) {
        this.userId = userId;
        this.phone = phone;
        this.userName = userName;
        this.createdTimeStamp = createdTimeStamp;
        this.email = mail;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Timestamp getCreatedTimeStamp() {
        return createdTimeStamp;
    }

    public void setCreatedTimeStamp(Timestamp createdTimeStamp) {
        this.createdTimeStamp = createdTimeStamp;
    }
}
