package com.androidlibrary.test;

import android.app.Application;
import android.content.Context;

import com.mylibrary.BuildConfig;
import com.mylibrary.MyApplication;

public class BaseApplication extends Application {

    public static Context app;


    @Override
    public void onCreate() {
        super.onCreate();
        app = getApplicationContext();
       MyApplication.init(this, BuildConfig.DEBUG);
    }
}
