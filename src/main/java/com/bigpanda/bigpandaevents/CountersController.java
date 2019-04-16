/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bigpanda.bigpandaevents;

import com.bigpanda.bigpandaevents.dao.EventsDao;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class CountersController {
    
    @RequestMapping(value = "/get_events_count", method = RequestMethod.GET)
    public String getEventsCountByType(@RequestParam(value="eventType") String eventType) {
        return EventsDao.getInstance().getEventsCounter(eventType).toString();
    }
    
    @RequestMapping(value = "/get_word_count", method = RequestMethod.GET)
    public String getWordCount(@RequestParam(value="word") String word) {
        return EventsDao.getInstance().getWordCounter(word).toString();
    }
}
