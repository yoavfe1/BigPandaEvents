/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.bigpanda.bigpandaevents.dao;

import com.bigpanda.bigpandaevents.model.Event;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Yoav
 */
public class EventsDao {
    private Map<String,List> eventsMap;
    private Map<String,Integer> wordsCountersMap;
    private static EventsDao instance = null;
    private static Object creationLock = new Object();
    private static Object insertationLock = new Object();
    
    public static EventsDao getInstance(){
        //Double-Checked Locking
        if(instance == null){
            synchronized(creationLock){
                if(instance == null){
                    instance = new EventsDao();
                }
            }
        }
        return instance;
    }
    
    private EventsDao(){
        eventsMap = new ConcurrentHashMap<>();
        wordsCountersMap = new ConcurrentHashMap<>();
    }

    public void insertNewEvent(Event event){
        String eventType = event.getEventType();
        String word = event.getData();
        synchronized(insertationLock){
            //1. Insert event to Events Map:
            if(!eventsMap.containsKey(event.getEventType())){
                eventsMap.put(eventType, new LinkedList<Event>());
            }
            eventsMap.get(eventType).add(event);
            
            //2. Insert word to Word Counters Map:
            if(!wordsCountersMap.containsKey(word)){
                wordsCountersMap.put(word, 0);
            }
            wordsCountersMap.put(word, wordsCountersMap.get(word) + 1);
        }
    }
    
    public Integer getEventsCounter(String eventType){
        if(eventsMap.containsKey(eventType)){
            return eventsMap.get(eventType).size();
        }else{
            return 0;
        }
    }
    
    public Integer getWordCounter(String word){
        return wordsCountersMap.getOrDefault(word, 0);
    }
}
