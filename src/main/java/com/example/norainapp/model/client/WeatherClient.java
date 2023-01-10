package com.example.norainapp.model.client;

import com.example.norainapp.model.Weather;

import java.util.List;

public interface WeatherClient {
    Weather getWeather(String cityName);
    boolean checkCity(String cityName);
    String getEngCityAndCountryName(String cityName);
    List<Weather> getForecast(String cityName);
}
