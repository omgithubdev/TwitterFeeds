package com.omagrahari.demotwitter.entity;

import java.util.ArrayList;

/**
 * Created by omprakash on 26,December,2019
 */
public class TweetsErrorResponse {
    ArrayList<Errors> errors = new ArrayList<>();

    public ArrayList<Errors> getErrors() {
        return errors;
    }

    public void setErrors(ArrayList<Errors> errors) {
        this.errors = errors;
    }
}
