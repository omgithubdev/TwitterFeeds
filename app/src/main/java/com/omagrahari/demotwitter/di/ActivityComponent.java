package com.omagrahari.demotwitter.di;


import com.omagrahari.demotwitter.di.module.ApplicationModule;
import com.omagrahari.demotwitter.di.module.RepositoryModule;
import com.omagrahari.demotwitter.di.module.RetrofitLoginModule;
import com.omagrahari.demotwitter.di.module.RetrofitModule;
import com.omagrahari.demotwitter.feeds.FeedsActivity;
import com.omagrahari.demotwitter.feeds.FeedsRepository;
import com.omagrahari.demotwitter.login.LoginActivity;
import com.omagrahari.demotwitter.login.LoginRepository;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by omprakash on 25,December,2019
 */
@Singleton
@Component(modules = {ApplicationModule.class, RetrofitLoginModule.class, RepositoryModule.class, RetrofitModule.class})
public interface ActivityComponent {

    public void inject(FeedsActivity feedsActivity);

    public void inject(LoginActivity loginActivity);

    public void inject(LoginRepository loginRepository);

    public void inject(FeedsRepository feedsRepository);
}
