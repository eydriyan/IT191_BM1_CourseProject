package com.it191.model;

public class AuthModel {
    public String username;
    public String hashedPassword;
    public UserModel userInfo;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getHashedPassword() {
        return hashedPassword;
    }
    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
    public UserModel getUserInfo() {
        return userInfo;
    }
    public void setUserInfo(UserModel userInfo) {
        this.userInfo = userInfo;
    }
}
