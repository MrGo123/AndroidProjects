package com.sustart.shdsystem;

import android.app.Application;

import com.sustart.shdsystem.entity.Product;

public class SHDSystemApplication extends Application {

    private static SHDSystemApplication instance;
    private static Product productDetail;



    public static SHDSystemApplication getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
