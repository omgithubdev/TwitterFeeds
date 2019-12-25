package com.omagrahari.demotwitter;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.omagrahari.demotwitter.di.ActivityComponent;
import com.omagrahari.demotwitter.di.DaggerActivityComponent;
import com.omagrahari.demotwitter.di.module.ApplicationModule;


/**
 * Created by omprakash on 25,December,2019
 */
public class DemoTwitterApplication extends Application {
    ActivityComponent activityComponent;
    public static DemoTwitterApplication demoTwitterApplication;

    @Override
    public void onCreate() {
        super.onCreate();

        Fresco.initialize(this);

        demoTwitterApplication = this;

        activityComponent = DaggerActivityComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public static DemoTwitterApplication getDemoTwitterApplication() {
        return demoTwitterApplication;
    }

    public ActivityComponent getComponent() {
        return activityComponent;
    }
}
