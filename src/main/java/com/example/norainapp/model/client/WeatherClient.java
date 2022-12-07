package com.example.norainapp.model.client;

import com.example.norainapp.model.Weather;

public interface WeatherClient {
    Weather getWeather(String cityName);
}
