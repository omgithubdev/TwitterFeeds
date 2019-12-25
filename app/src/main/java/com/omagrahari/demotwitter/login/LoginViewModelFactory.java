package com.omagrahari.demotwitter.login;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

/**
 * Created by omprakash on 25,December,2019
 */
public class LoginViewModelFactory implements ViewModelProvider.Factory {
    private LoginRepository loginRepository;

    @Inject
    LoginViewModelFactory(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new LoginViewModel(loginRepository);
    }
}
