package com.omagrahari.demotwitter.di.module;


import com.omagrahari.demotwitter.remote.ApiReferenceLogin;

import java.io.IOException;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.omagrahari.demotwitter.BuildConfig.AUTH_KEY;
import static com.omagrahari.demotwitter.utils.Constants.BASE_URL;


/**
 * Created by omprakash on 25,December,2019
 */
@Module
public class RetrofitLoginModule {

    @Singleton
    @Provides
    public ApiReferenceLogin providesApiReferenceLogin(@Named("retrofitLogin") Retrofit retrofit) {
        return retrofit.create(ApiReferenceLogin.class);
    }

    @Singleton
    @Provides
    @Named("retrofitLogin")
    public Retrofit providesRetrofitLogin(@Named("retrofitLogin") OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Singleton
    @Provides
    @Named("retrofitLogin")
    OkHttpClient getOkHttpCleintLogin(HttpLoggingInterceptor httpLoggingInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("Authorization", "Basic " + AUTH_KEY)
                                .addHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
                                .build();
                        return chain.proceed(request);
                    }
                })
                .build();
    }

    @Singleton
    @Provides
    HttpLoggingInterceptor getHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }
}
