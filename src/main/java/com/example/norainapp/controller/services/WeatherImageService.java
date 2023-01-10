package com.example.norainapp.controller.services;

import com.example.norainapp.model.WeatherDescription;
import javafx.scene.image.Image;

public class WeatherImageService {
    public Image getWeatherImage(WeatherDescription weatherDescription) {
        return switch (weatherDescription) {
            case CLEAR -> getImage("sun.png");
            case CLOUDY -> getImage("cloud_sun.png");
            case CLOUDS -> getImage("cloud.png");
            case RAIN -> getImage("rain.png");
            case STORM -> getImage("storm.png");
            case SNOW -> getImage("snow.png");
            case MIST -> getImage("mist.png");
            default -> getImage("pig.png");
        };
    }

    private Image getImage(String iconPath) {
        return new Image(this.getClass().getResourceAsStream("/images/%s".formatted(iconPath)));
    }
}
