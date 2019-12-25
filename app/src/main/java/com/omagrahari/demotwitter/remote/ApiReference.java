package com.omagrahari.demotwitter.remote;

import com.omagrahari.demotwitter.entity.Tweets;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by omprakash on 25,December,2019
 */
public interface ApiReference {
    @GET("/1.1/statuses/user_timeline.json")
    Call<List<Tweets>> getUserTimeline(@Query("screen_name") String screenName);
}
