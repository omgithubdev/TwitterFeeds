package com.omagrahari.demotwitter.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.omagrahari.demotwitter.entity.AuthResponse;

/**
 * Created by omprakash on 25,December,2019
 */
public class LoginViewModel extends ViewModel {
    private LoginRepository loginRepository;
    String userName = "";

    public LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public LiveData<AuthResponse> login() {
        return loginRepository.login();
    }

    public boolean isValid() {
        boolean isValid = true;

        if (userName.equalsIgnoreCase("")) {
            isValid = false;
        }

        return isValid;
    }
}
