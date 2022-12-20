package com.example.norainapp.model.client;

import com.example.norainapp.SavedProperties;
import com.example.norainapp.model.Weather;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.TimeZone;

public class OpenWeatherMapClient implements WeatherClient {
    private static final String WEATHER_URL_BEGIN = "https://api.openweathermap.org/data/2.5/weather?";
    private static final String FORECAST_URL_BEGIN = "https://api.openweathermap.org/data/2.5/forecast?";
    private static final String GEO_URL_BEGIN = "https://api.openweathermap.org/geo/1.0/direct?";
    private static final String API_ID = "fc4be267d4aa71af6466c19cef08ca97";
    private static final String LANGUAGE = "pl";
    private static final String UNITS = "metric";
    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public Weather getWeather(String cityName) {
        int temperature, feelsLike;
        String weatherDescription;

        String response = restTemplate.getForObject(WEATHER_URL_BEGIN + "q={city}&appid=" + API_ID + "&lang=" + LANGUAGE + "&units=" + UNITS,
                String.class, cityName);

        JsonObject main = new Gson().fromJson(response, JsonObject.class).getAsJsonObject("main");
        JsonArray weather = new Gson().fromJson(response, JsonObject.class).getAsJsonArray("weather");

        temperature = (int) Math.round(main.get("temp").getAsDouble());
        feelsLike = (int) Math.round(main.get("feels_like").getAsDouble());
        weatherDescription = weather.get(0).getAsJsonObject().get("main").getAsString();

        return new Weather(cityName, temperature, feelsLike, LocalDateTime.now(), weatherDescription);
    }

    @Override
    public boolean checkCity(String cityName) {
        String response = restTemplate.getForObject(GEO_URL_BEGIN + "q={city}&appid=" + API_ID, String.class, cityName);
        assert response != null;
        return !response.equals("[]");
    }

    @Override
    public ArrayList<Weather> getForecast(String cityName) {
        String response = restTemplate.getForObject(FORECAST_URL_BEGIN + "q={city}&appid=" + API_ID + "&units=" + UNITS, String.class, cityName);
        ArrayList<Weather> forecast = new ArrayList<>();
        ArrayList<Long> unixDateDays = new ArrayList<>();

        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime currentDateNoon = LocalDate.now().atTime(12, 0);
        ZoneId zoneId = ZoneId.of("Europe/London");

        for (int i = 0; i < 5; i++) {
            if (currentDate.isBefore(LocalDate.now().atTime(13, 0))) {
                unixDateDays.add(currentDateNoon.plusDays(i).atZone(zoneId).toEpochSecond());
            } else {
                unixDateDays.add(currentDateNoon.plusDays(i + 1).atZone(zoneId).toEpochSecond());
            }
        }

        try {
            JsonArray forecastList = new Gson().fromJson(response, JsonObject.class).getAsJsonArray("list");

            for (Long unixDateDay : unixDateDays) {
                for (int j = 0; j < forecastList.size(); j++) {
                    JsonObject dailyForecast = forecastList.get(j).getAsJsonObject();
                    if (dailyForecast.get("dt").getAsLong() == unixDateDay) {
                        JsonObject main = dailyForecast.getAsJsonObject("main");
                        JsonArray weather = dailyForecast.getAsJsonArray("weather");

                        LocalDateTime convertedUnixTimeStamp = LocalDateTime.ofInstant(Instant.ofEpochSecond(unixDateDay),
                                TimeZone.getDefault().toZoneId());

                        Weather forecastWeather = new Weather(
                                cityName,
                                (int) Math.round(main.get("temp").getAsDouble()),
                                main.get("feels_like").getAsDouble(),
                                convertedUnixTimeStamp,
                                weather.get(0).getAsJsonObject().get("main").getAsString());
                        forecast.add(forecastWeather);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return forecast;
    }
}
