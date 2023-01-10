package com.example.norainapp;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class SavedProperties {
    private String leftCityName;
    private String rightCityName;
    private final Properties properties = new Properties();

    public SavedProperties() throws IOException {
        properties.load(new FileReader("cityNames.properties"));

        this.leftCityName = load("leftCityName");
        this.rightCityName = load("rightCityName");
    }
    public String getLeftCityName() {
        return leftCityName;
    }

    public String getRightCityName() {
        return rightCityName;
    }

    public void setLeftCityName(String leftCityName) throws IOException {
        this.leftCityName = leftCityName;
        saveProperties("leftCityName", leftCityName);
    }

    public void setRightCityName(String rightCityName) throws IOException {
        this.rightCityName = rightCityName;
        saveProperties("rightCityName", rightCityName);
    }

    private void saveProperties(String propertyName, String selectedName) throws IOException {
        properties.setProperty(propertyName, selectedName);
        properties.store(new FileWriter("cityNames.properties", StandardCharsets.UTF_8), "");
    }

    private String load(String propertyName) {
        if (properties.getProperty(propertyName) != null) {
            return properties.getProperty(propertyName);
        }
        return null;
    }
}
