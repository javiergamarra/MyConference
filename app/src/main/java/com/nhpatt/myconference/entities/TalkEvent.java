package com.nhpatt.myconference.entities;

import com.google.gson.JsonArray;

/**
 * @author Javier Gamarra
 */
public class TalkEvent {
    private final JsonArray talks;

    public TalkEvent(JsonArray talks) {
        this.talks = talks;
    }

    public JsonArray getTalks() {
        return talks;
    }
}
