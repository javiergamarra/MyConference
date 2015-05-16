package com.nhpatt.myconference.entities;

import com.google.gson.JsonArray;

/**
 * @author Javier Gamarra
 */
public class TalkResponse {
    private final JsonArray talks;

    public TalkResponse(JsonArray talks) {
        this.talks = talks;
    }

    public JsonArray getTalks() {
        return talks;
    }
}
