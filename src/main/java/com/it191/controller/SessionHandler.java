package com.it191.controller;

import com.it191.model.UserModel;

public class SessionHandler {
    private static SessionHandler s_instance;

    private UserModel userModel;

    private SessionHandler() {}
    public static SessionHandler getSessionHandler() {
        if (s_instance == null) s_instance = new SessionHandler();
        return s_instance;
    }

    public void setUsedUser(UserModel userModel) {
        this.userModel = userModel;
    }

    public UserModel getUsedUser() {
        return this.userModel;
    }
}
