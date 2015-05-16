package com.nhpatt.myconference.network;

import com.google.gson.JsonArray;

import retrofit.http.GET;

/**
 * @author Javier Gamarra
 */
public interface TalkService {

    @GET("/questions")
    JsonArray listTalks();

}
