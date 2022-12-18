package com.example.norainapp.model.client;

import com.example.norainapp.model.Weather;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

public class OpenWeatherMapClient implements WeatherClient {
    private static final String WEATHER_URL_BEGIN = "https://api.openweathermap.org/data/2.5/weather?";
    private static final String GEO_URL_BEGIN = "https://api.openweathermap.org/geo/1.0/direct?";
    private static final String API_ID = "fc4be267d4aa71af6466c19cef08ca97";
    private static final String LANGUAGE = "pl";
    private static final String UNITS = "metric";
    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public Weather getWeather(String cityName) {
        double temperature;

        String response = restTemplate.getForObject(WEATHER_URL_BEGIN + "q={city}&appid=" + API_ID + "&lang=" + LANGUAGE + "&units=" + UNITS,
                String.class, cityName);

        JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class).getAsJsonObject("main");
        temperature = jsonObject.get("temp").getAsDouble();

        return new Weather(cityName, temperature, LocalDate.now());
    }

    @Override
    public boolean checkCity(String cityName) {
        String response = restTemplate.getForObject(GEO_URL_BEGIN + "q={city}&appid=" + API_ID, String.class, cityName);
        assert response != null;
        return !response.equals("[]");
    }
}
