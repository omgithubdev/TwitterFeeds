package com.omagrahari.demotwitter.entity;

import android.net.Uri;

import androidx.databinding.BindingAdapter;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.facebook.drawee.view.SimpleDraweeView;

import org.jetbrains.annotations.NotNull;

/**
 * Created by omprakash on 25,December,2019
 */
@Entity(tableName = "tweets")
public class Tweets {
    String created_at;

    @PrimaryKey
    @NotNull
    String id;
    String id_str;
    String text;
    String truncated;
    @Embedded
    User user;

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    @NotNull
    public String getId() {
        return id;
    }

    public void setId(@NotNull String id) {
        this.id = id;
    }

    public String getId_str() {
        return id_str;
    }

    public void setId_str(String id_str) {
        this.id_str = id_str;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTruncated() {
        return truncated;
    }

    public void setTruncated(String truncated) {
        this.truncated = truncated;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @BindingAdapter("imageFresco")
    public static void setImageFresco(SimpleDraweeView view, String profile_image_url) {
        if (profile_image_url != null) {
            Uri uri = Uri.parse(profile_image_url);
            view.setImageURI(uri);
        }
    }
}
