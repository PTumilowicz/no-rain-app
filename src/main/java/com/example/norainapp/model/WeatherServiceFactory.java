package com.example.norainapp.model;

import com.example.norainapp.model.client.ExampleWeatherClient;
import com.example.norainapp.model.client.WeatherClient;

public class WeatherServiceFactory {
    public static WeatherService createWeatherService() {
        return new WeatherService(createWeatherClient());
    }

    public static WeatherClient createWeatherClient() {
        return new ExampleWeatherClient();
    }

}
