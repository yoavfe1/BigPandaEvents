/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.bigpanda.bigpandaevents;

import com.bigpanda.bigpandaevents.utils.PropertiesFileHandler;
import io.reactivex.Observable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
/**
 *
 * @author Yoav
 */

public class EventsReader{
    private static EventsReader instance = null;
    private static Object creationLock = new Object();
    
    public static EventsReader getInstance(){
        if(instance == null){
            synchronized(creationLock){
                if(instance == null){
                    instance = new EventsReader();
                }
            }
        }
        return instance;
    }
    
    private EventsReader(){}
    
    public void read() throws IOException{
        String generator_path = PropertiesFileHandler.getInstance().getGeneratorFilePath();
        read(generator_path);
    }
    
    public static void read(String filePath) throws IOException{
        Process process = new ProcessBuilder(filePath).start();
        InputStream processInputStream = process.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(processInputStream));
        Observable.fromIterable(br.lines()::iterator)
                .subscribe(new EventProcessor());
    }
}
