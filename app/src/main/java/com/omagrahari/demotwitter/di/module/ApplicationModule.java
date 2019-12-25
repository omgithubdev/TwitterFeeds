package com.omagrahari.demotwitter.di.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.omagrahari.demotwitter.DemoTwitterApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by omprakash on 25,December,2019
 */
@Module
public class ApplicationModule {
    private static final String APP_PREFS = "APP_PREFS";

    DemoTwitterApplication demoTwitterApplication;

    public ApplicationModule(DemoTwitterApplication demoTwitterApplication) {
        this.demoTwitterApplication = demoTwitterApplication;
    }

    @Singleton
    @Provides
    public Application providesApplication() {
        return demoTwitterApplication;
    }

    @Singleton
    @Provides
    public SharedPreferences providesSharedPreference(Application application) {
        return application.getApplicationContext().getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
    }
}
