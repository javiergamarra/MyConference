package com.nhpatt.myconference.network;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * @author Javier Gamarra
 */
public interface TalkService {

    @GET("/questions")
    void listTalks(
            Callback<JsonArray> callback);

}
