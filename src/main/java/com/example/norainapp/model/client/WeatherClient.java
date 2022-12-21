package com.example.norainapp.model.client;

import com.example.norainapp.model.Weather;

import java.util.ArrayList;

public interface WeatherClient {
    Weather getWeather(String cityName);
    boolean checkCity(String cityName);
    String getEngCityAndCountryName(String cityName);
    ArrayList<Weather> getForecast(String cityName);
}
