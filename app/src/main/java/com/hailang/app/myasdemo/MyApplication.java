package com.hailang.app.myasdemo;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
//import com.squareup.leakcanary.LeakCanary;

/**
 * Created by niantian_huang on 2016/5/9.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
//        LeakCanary.install(this);
    }
}
