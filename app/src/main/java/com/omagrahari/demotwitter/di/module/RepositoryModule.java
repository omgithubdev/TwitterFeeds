package com.omagrahari.demotwitter.di.module;

import android.app.Application;

import com.omagrahari.demotwitter.feeds.FeedsRepository;
import com.omagrahari.demotwitter.local.AppDatabase;
import com.omagrahari.demotwitter.local.TweetsDao;
import com.omagrahari.demotwitter.login.LoginRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by omprakash on 16,December,2019
 */
@Module
public class RepositoryModule {

    @Singleton
    @Provides
    public LoginRepository providesLoginRespository(Application application) {
        return new LoginRepository(application);
    }


    @Singleton
    @Provides
    public FeedsRepository providesFeedsRespository(Application application) {
        return new FeedsRepository(application);
    }

    @Singleton
    @Provides
    TweetsDao providesPlayoutDao(Application application) {
        AppDatabase db = AppDatabase.getAppDatabase(application.getApplicationContext());
        return db.tweetsDao();
    }
}
