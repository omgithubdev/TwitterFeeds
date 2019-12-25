package com.omagrahari.demotwitter.feeds;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.omagrahari.demotwitter.entity.Tweets;

import java.util.List;

/**
 * Created by omprakash on 25,December,2019
 */
public class FeedsViewModel extends ViewModel {
    FeedsRepository feedsRepository;
    LiveData<List<Tweets>> tweets = new MutableLiveData<>();

    public FeedsViewModel(FeedsRepository feedsRepository) {
        this.feedsRepository = feedsRepository;
    }

    public void init() {
        feedsRepository.fetchFeeds();
    }

    public LiveData<List<Tweets>> getFeeds() {
        return feedsRepository.getFeeds();
    }

    public void insertFeeds(int position) {
        feedsRepository.new InsertTweets(position).execute();
    }


    public void deleteFeeds() {
        feedsRepository.new DeleteTweets().execute();
    }
}
