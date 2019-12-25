package com.omagrahari.demotwitter.login;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.omagrahari.demotwitter.entity.AuthResponse;
import com.omagrahari.demotwitter.remote.ApiReferenceLogin;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.omagrahari.demotwitter.DemoTwitterApplication.getDemoTwitterApplication;

/**
 * Created by omprakash on 25,December,2019
 */
public class LoginRepository {
    Application application;
    MutableLiveData<AuthResponse> authResponseData = new MutableLiveData<>();

    @Inject
    ApiReferenceLogin apiReferenceLogin;

    public LoginRepository(Application application) {
        this.application = application;

        getDemoTwitterApplication().getComponent().inject(this);
    }

    public MutableLiveData<AuthResponse> login() {
        apiReferenceLogin.getToken("client_credentials").enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                AuthResponse authResponse = response.body();
                authResponse.setStatusCode(response.code());
                authResponseData.postValue(authResponse);
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                AuthResponse authResponse = new AuthResponse();
                authResponse.setStatusCode(1);
                authResponse.setMessage(t.getLocalizedMessage());
                authResponseData.postValue(authResponse);
            }
        });

        return authResponseData;
    }
}
