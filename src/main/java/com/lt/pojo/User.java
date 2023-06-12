package com.lt.pojo;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;


public class User {
    private  String petName;
    private String userId;
    private String userAccount;
    private String userEmail;
    private String userSex;
    private Integer userGender;
    private Integer userAuth;
    private String userSchool;
    private String userAvatar;
    private BigDecimal balance;

    public User() {
    }

    public User(String petName, String userId, String userAccount, String userEmail, String userSex, Integer userGender, Integer userAuth, String userSchool, String userAvatar, BigDecimal balance) {
        this.petName = petName;
        this.userId = userId;
        this.userAccount = userAccount;
        this.userEmail = userEmail;
        this.userSex = userSex;
        this.userGender = userGender;
        this.userAuth = userAuth;
        this.userSchool = userSchool;
        this.userAvatar = userAvatar;
        this.balance = balance;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public Integer getUserGender() {
        return userGender;
    }

    public void setUserGender(Integer userGender) {
        this.userGender = userGender;
    }

    public Integer getUserAuth() {
        return userAuth;
    }

    public void setUserAuth(Integer userAuth) {
        this.userAuth = userAuth;
    }

    public String getUserSchool() {
        return userSchool;
    }

    public void setUserSchool(String userSchool) {
        this.userSchool = userSchool;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "User{" +
                "petName='" + petName + '\'' +
                ", userId='" + userId + '\'' +
                ", userAccount='" + userAccount + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userSex='" + userSex + '\'' +
                ", userGender=" + userGender +
                ", userAuth=" + userAuth +
                ", userSchool='" + userSchool + '\'' +
                ", userAvatar='" + userAvatar + '\'' +
                ", balance=" + balance +
                '}';
    }
}
