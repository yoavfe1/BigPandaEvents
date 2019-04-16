/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.bigpanda.bigpandaevents.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Yoav
 */

public class PropertiesFileHandler {
    private static PropertiesFileHandler instance = null;
    private static Object creationLock = new Object();
    
    public static PropertiesFileHandler getInstance(){
        //Double-Checked Locking
        if(instance == null){
            synchronized(creationLock){
                if(instance == null){
                    instance = new PropertiesFileHandler();
                }
            }
        }
        return instance;
    }
    
    private PropertiesFileHandler(){}
    
    public String getGeneratorFilePath() throws IOException{
        InputStream input = getClass().getClassLoader().getResourceAsStream("big_panda_events.properties");
        Properties prop = new Properties();
        prop.load(input);
        return prop.getProperty("generator_path");
    }
}