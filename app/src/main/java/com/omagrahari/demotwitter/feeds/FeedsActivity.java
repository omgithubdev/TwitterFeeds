package com.omagrahari.demotwitter.feeds;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.omagrahari.demotwitter.R;
import com.omagrahari.demotwitter.databinding.ActivityFeedsBinding;
import com.omagrahari.demotwitter.entity.Tweets;
import com.omagrahari.demotwitter.feeds.adapter.FeedsAdapter;
import com.omagrahari.demotwitter.login.LoginActivity;
import com.omagrahari.demotwitter.utils.SharedPrefs;

import java.util.List;

import javax.inject.Inject;

import static com.omagrahari.demotwitter.DemoTwitterApplication.getDemoTwitterApplication;

public class FeedsActivity extends AppCompatActivity {
    ActivityFeedsBinding binding;

    @Inject
    FeedsViewModelFactory feedsViewModelFactory;
    FeedsViewModel viewModel;

    @Inject
    SharedPreferences sharedPreferences;

    FeedsAdapter feedsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_feeds);

        getDemoTwitterApplication().getComponent().inject(this);

        viewModel = new ViewModelProvider(this, feedsViewModelFactory).get(FeedsViewModel.class);
        viewModel.init();

        setListeners();
        initObserver();
    }

    private void setListeners() {
        binding.itoolbar.ivLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.deleteFeeds();
                SharedPrefs.clearPref(sharedPreferences);
                finish();
                startActivity(new Intent(FeedsActivity.this, LoginActivity.class));
            }
        });

        binding.rvFeeds.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) //check for scroll down
                {
                    LinearLayoutManager layoutManager = (LinearLayoutManager) binding.rvFeeds.getLayoutManager();
                    int visibleItemCount = layoutManager.getChildCount();
                    int pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();

                    viewModel.insertFeeds(visibleItemCount + pastVisiblesItems);
                }
            }
        });
    }

    private void initObserver() {
        binding.progress.setVisibility(View.VISIBLE);
        viewModel.getFeeds().observe(this, new Observer<List<Tweets>>() {
            @Override
            public void onChanged(List<Tweets> tweets) {
                binding.progress.setVisibility(View.GONE);
                feedsAdapter = new FeedsAdapter(tweets);
                binding.rvFeeds.setAdapter(feedsAdapter);

                if (tweets.size() == 0) {
                    Toast.makeText(FeedsActivity.this, getString(R.string.error_no_tweets), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
