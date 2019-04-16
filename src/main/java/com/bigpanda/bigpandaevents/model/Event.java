/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bigpanda.bigpandaevents.model;

import java.util.Date;

/**
 *
 * @author Yoav
 */
public class Event {
    private String eventType;
    private String data;
    private Date timestamp;

    public Event(String eventType, String data, Date timestamp) {
        this.eventType = eventType;
        this.data = data;
        this.timestamp = timestamp;
    }

    public String getEventType() {
        return eventType;
    }

    public String getData() {
        return data;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
