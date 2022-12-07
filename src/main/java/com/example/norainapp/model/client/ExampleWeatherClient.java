package com.example.norainapp.model.client;

import com.example.norainapp.model.Weather;

import java.time.LocalDate;

public class ExampleWeatherClient implements WeatherClient {
    @Override
    public Weather getWeather(String cityName) {
        return new Weather("Kraków", 10, LocalDate.now());
    }
}
