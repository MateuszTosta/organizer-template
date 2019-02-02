package com.sda.files;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {

    public void updateConfigProperties(ConfigDTO configDTO) {
        Properties properties = new Properties();
        try {
            FileWriter fw = new FileWriter("config.properties");
            properties.setProperty("username", configDTO.getUserName());
            properties.setProperty("localization", configDTO.getLocalization());
            properties.setProperty("calories", configDTO.getCaloriesLimit().toString());
            properties.store(fw, "");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}