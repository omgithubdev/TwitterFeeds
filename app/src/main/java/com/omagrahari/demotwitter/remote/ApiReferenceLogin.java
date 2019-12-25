package com.omagrahari.demotwitter.remote;

import com.omagrahari.demotwitter.entity.AuthResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by omprakash on 25,December,2019
 */
public interface ApiReferenceLogin {
    @FormUrlEncoded
    @POST("/oauth2/token")
    Call<AuthResponse> getToken(@Field("grant_type") String grantType);
}
