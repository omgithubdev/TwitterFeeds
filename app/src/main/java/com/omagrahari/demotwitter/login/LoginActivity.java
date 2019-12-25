package com.omagrahari.demotwitter.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.omagrahari.demotwitter.R;
import com.omagrahari.demotwitter.databinding.ActivityLoginBinding;
import com.omagrahari.demotwitter.entity.AuthResponse;
import com.omagrahari.demotwitter.feeds.FeedsActivity;
import com.omagrahari.demotwitter.utils.SharedPrefs;
import com.omagrahari.demotwitter.utils.Utils;
import com.omagrahari.demotwitter.utils.event.LoginClicked;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import static com.omagrahari.demotwitter.DemoTwitterApplication.getDemoTwitterApplication;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;

    @Inject
    SharedPreferences sharedPref;

    @Inject
    LoginViewModelFactory loginViewModelFactory;
    LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        getDemoTwitterApplication().getComponent().inject(this);

        viewModel = new ViewModelProvider(this, loginViewModelFactory).get(LoginViewModel.class);

        init();
    }

    public void onLogin(View view) {
        if (viewModel.isValid()) {
            if (Utils.isNetworkConnected(this)) {
                binding.progress.setVisibility(View.VISIBLE);
                binding.btnLogin.setVisibility(View.INVISIBLE);
                EventBus.getDefault().postSticky(new LoginClicked());
            } else {
                Toast.makeText(this, getString(R.string.error_no_internet), Toast.LENGTH_LONG).show();
            }
        } else
            binding.etUserName.setError(getString(R.string.error_no_username));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginClicked(LoginClicked loginClicked) {
        viewModel.login().observe(this, new Observer<AuthResponse>() {
            @Override
            public void onChanged(AuthResponse authResponse) {
                binding.progress.setVisibility(View.GONE);
                binding.btnLogin.setVisibility(View.VISIBLE);
                if (authResponse.getStatusCode() == 200) {
                    SharedPrefs.setoken(sharedPref, authResponse.getAccess_token());
                    SharedPrefs.setUserName(sharedPref, binding.etUserName.getText().toString());

                    showFeeds();
                } else {
                    Toast.makeText(LoginActivity.this, authResponse.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void init() {
        binding.etUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                viewModel.userName = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        if (SharedPrefs.getToken(sharedPref) != null && !SharedPrefs.getToken(sharedPref).equalsIgnoreCase("")) {
            showFeeds();
        }
    }

    public void showFeeds() {
        finish();
        startActivity(new Intent(LoginActivity.this, FeedsActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

}
