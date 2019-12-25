package com.omagrahari.demotwitter.entity;

import androidx.room.Embedded;

/**
 * Created by omprakash on 25,December,2019
 */
public class User {
    @Embedded(prefix = "user")
    String id;
    String name;
    String screen_name;
    String description;
    String profile_image_url;

    public String getProfile_image_url() {
        return profile_image_url;
    }

    public void setProfile_image_url(String profile_image_url) {
        this.profile_image_url = profile_image_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScreen_name() {
        return screen_name;
    }

    public void setScreen_name(String screen_name) {
        this.screen_name = screen_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
