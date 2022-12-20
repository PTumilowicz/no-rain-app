package com.example.norainapp.model.client;

import com.example.norainapp.model.Weather;

import java.io.IOException;
import java.util.ArrayList;

public interface WeatherClient {
    Weather getWeather(String cityName);
    boolean checkCity(String cityName);
    ArrayList<Weather> getForecast(String cityName);
}
