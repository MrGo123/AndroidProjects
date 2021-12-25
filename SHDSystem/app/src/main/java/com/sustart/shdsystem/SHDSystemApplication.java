package com.sustart.shdsystem;

import android.app.Application;

import com.sustart.shdsystem.entity.User;

public class SHDSystemApplication extends Application {

    private static SHDSystemApplication instance;
    //当前登录用户
    public User loginUser;


    public static SHDSystemApplication getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
