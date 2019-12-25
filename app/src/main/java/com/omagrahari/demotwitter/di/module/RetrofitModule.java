package com.omagrahari.demotwitter.di.module;

import android.content.SharedPreferences;

import com.omagrahari.demotwitter.remote.ApiReference;
import com.omagrahari.demotwitter.utils.SharedPrefs;

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

import static com.omagrahari.demotwitter.utils.Constants.BASE_URL;

/**
 * Created by omprakash on 25,December,2019
 */
@Module
public class RetrofitModule {

    @Singleton
    @Provides
    public ApiReference providesApiReference(@Named("retrofit") Retrofit retrofit) {
        return retrofit.create(ApiReference.class);
    }

    @Singleton
    @Provides
    @Named("retrofit")
    public Retrofit providesRetrofit(@Named("retrofit") OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Singleton
    @Provides
    @Named("retrofit")
    OkHttpClient getOkHttpCleint(HttpLoggingInterceptor httpLoggingInterceptor, SharedPreferences sharedPrefs) {
        String authKey = SharedPrefs.getToken(sharedPrefs);
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("Authorization", "Bearer " + authKey)
                                .build();
                        return chain.proceed(request);
                    }
                })
                .build();
    }
}
