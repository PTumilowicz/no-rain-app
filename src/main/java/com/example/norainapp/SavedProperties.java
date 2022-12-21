package com.example.norainapp;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class SavedProperties {
    public static String leftCityName;
    public static String rightCityName;
    public static final Properties properties = new Properties();

    public static void saveProperties() throws IOException {
        if (leftCityName != null) {
            properties.setProperty("leftCityName", leftCityName);
        }

        if (rightCityName != null) {
            properties.setProperty("rightCityName", rightCityName);
        }

        properties.store(new FileWriter("cityNames.properties", StandardCharsets.UTF_8), "");
    }

    public static void loadProperties() throws IOException {
        properties.load(new FileReader("cityNames.properties"));

        if (properties.getProperty("leftCityName") != null) {
            leftCityName = properties.getProperty("leftCityName");
        }

        if (properties.getProperty("rightCityName") != null) {
            rightCityName = properties.getProperty("rightCityName");
        }
    }
}
