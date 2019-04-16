/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.bigpanda.bigpandaevents;

import com.bigpanda.bigpandaevents.dao.EventsDao;
import com.bigpanda.bigpandaevents.model.Event;
import io.reactivex.functions.Consumer;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Yoav
 */
public class EventProcessor implements Consumer<String>{
    @Override
    public void accept(String t) throws Exception {
        try{
            EventsDao.getInstance().insertNewEvent(convertJsonToEvent(t));
        }catch(JSONException ex){
            System.out.println("Invalid Json Object");
        }
    }
    
    private Event convertJsonToEvent(String jsonString){
        JSONObject jsonObj = new JSONObject(jsonString);
        String eventType = jsonObj.get("event_type").toString();
        String data = jsonObj.get("data").toString();
        Date date;
        try{
            date = new Date(Long.parseLong(jsonObj.get("timestamp").toString()));
        }catch(NumberFormatException ex){
            throw new JSONException("Invalid date");
        }
        return new Event(eventType,data,date);
    }
}