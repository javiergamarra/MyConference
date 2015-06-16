package com.nhpatt.myconference.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Javier Gamarra
 */
public class Talk implements Serializable {

    private Date start = new Date();
    private Date end = new Date();
    private String room;
    private String title;
    private String description;
    private String speaker;
    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

    public Talk() {

    }

    public Talk(String title, String room) {
        this.title = title;
        this.room = room;
    }

    public String getStart() {
        return sdf.format(start);
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public String getEnd() {
        return sdf.format(end);
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }
}
