package com.omagrahari.demotwitter.feeds;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

/**
 * Created by omprakash on 25,December,2019
 */
public class FeedsViewModelFactory implements ViewModelProvider.Factory {
    FeedsRepository feedsRepository;

    @Inject
    public FeedsViewModelFactory(FeedsRepository feedsRepository){
        this.feedsRepository = feedsRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new FeedsViewModel(feedsRepository);
    }
}
