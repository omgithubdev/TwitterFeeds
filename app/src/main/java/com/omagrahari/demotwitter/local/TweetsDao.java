package com.omagrahari.demotwitter.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.omagrahari.demotwitter.entity.Tweets;

import java.util.List;

/**
 * Created by omprakash on 25,December,2019
 */
@Dao
public interface TweetsDao {
    @Query("SELECT * FROM tweets")
    List<Tweets> getTweetsDB();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTweetsDB(List<Tweets> tweets);

    @Query("DELETE FROM tweets")
    public void delete();
}
