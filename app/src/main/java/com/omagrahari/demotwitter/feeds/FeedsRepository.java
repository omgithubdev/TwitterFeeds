package com.omagrahari.demotwitter.feeds;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.omagrahari.demotwitter.R;
import com.omagrahari.demotwitter.entity.Tweets;
import com.omagrahari.demotwitter.local.TweetsDao;
import com.omagrahari.demotwitter.remote.ApiReference;
import com.omagrahari.demotwitter.utils.SharedPrefs;
import com.omagrahari.demotwitter.utils.Utils;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.omagrahari.demotwitter.DemoTwitterApplication.getDemoTwitterApplication;

/**
 * Created by omprakash on 25,December,2019
 */
public class FeedsRepository {
    Application application;
    public MutableLiveData<List<Tweets>> tweetsData = new MutableLiveData<>();

    @Inject
    ApiReference apiReference;
    @Inject
    SharedPreferences preferences;
    @Inject
    TweetsDao tweetsDao;

    Context context;

    public FeedsRepository(Application application) {
        this.application = application;
        context = application.getApplicationContext();

        getDemoTwitterApplication().getComponent().inject(this);
    }

    public void fetchFeeds() {

        if (Utils.isNetworkConnected(context)) {
            requestFeeds();
        } else {
            new QueryTweetsDB().execute();
            Toast.makeText(context, context.getString(R.string.error_no_internet), Toast.LENGTH_LONG).show();
        }
    }

    public void requestFeeds() {
        apiReference.getUserTimeline(SharedPrefs.getUserName(preferences))
                .enqueue(new Callback<List<Tweets>>() {
                    @Override
                    public void onResponse(Call<List<Tweets>> call, Response<List<Tweets>> response) {
                        if (response.code() == 200) {
                            List<Tweets> tweets = response.body();
                            tweetsData.setValue(tweets);
                        } else {
                            Toast.makeText(context, context.getString(R.string.error_tweet_response), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Tweets>> call, Throwable t) {
                        Log.d("CHECKPOINT", "CHECK ERROR" + t.getLocalizedMessage());
                    }
                });
    }

    public class QueryTweetsDB extends AsyncTask<Void, Void, List<Tweets>> {
        @Override
        protected List<Tweets> doInBackground(Void... voids) {
            return tweetsDao.getTweetsDB();
        }

        @Override
        protected void onPostExecute(List<Tweets> tweets) {
            super.onPostExecute(tweets);
            tweetsData.setValue(tweets);
        }
    }

    public MutableLiveData<List<Tweets>> getFeeds() {
        return tweetsData;
    }


    public class InsertTweets extends AsyncTask<Void, Void, Void> {
        int position;

        public InsertTweets(int position) {
            this.position = position;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if (position > tweetsData.getValue().size()) {
                return null;
            }

            tweetsDao.insertTweetsDB(tweetsData.getValue().subList(0, position));
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    public class DeleteTweets extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            tweetsDao.delete();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}
