package com.example.norainapp.model;

import com.example.norainapp.model.client.WeatherClient;
import org.springframework.web.client.RestTemplate;

public class WeatherService {
    private final WeatherClient weatherClient;

    public WeatherService(WeatherClient weatherClient) {
        this.weatherClient = weatherClient;
    }

    public WeatherClient getWeatherClient() {
        return weatherClient;
    }
}
