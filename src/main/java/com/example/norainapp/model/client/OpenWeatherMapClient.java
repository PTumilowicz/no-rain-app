package com.example.norainapp.model.client;

import com.example.norainapp.model.Weather;
import com.example.norainapp.model.WeatherDescription;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public class OpenWeatherMapClient implements WeatherClient {
    private static final String WEATHER_URL_BEGIN = "https://api.openweathermap.org/data/2.5/weather?";
    private static final String FORECAST_URL_BEGIN = "https://api.openweathermap.org/data/2.5/forecast?";
    private static final String GEO_URL_BEGIN = "https://api.openweathermap.org/geo/1.0/direct?";
    private static final String API_ID = OpenWeatherMapConfig.API_ID;
    private static final String LANGUAGE = "pl";
    private static final String UNITS = "metric";
    private final RestTemplate restTemplate = new RestTemplate();
    private final Gson gson = new Gson();
    private static final ZoneId zoneId = ZoneId.of("Europe/London");

    @Override
    public Weather getWeather(String cityName) {
        int temperature;
        WeatherDescription weatherDescription;
        String response = null;

        try {
            response = restTemplate.getForObject(WEATHER_URL_BEGIN + "q={city}&appid=" + API_ID + "&lang=" + LANGUAGE + "&units=" + UNITS,
                    String.class, cityName);
        } catch (Exception e) {
            System.out.println("City not found");
            return null;
        }

        JsonObject main = gson.fromJson(response, JsonObject.class).getAsJsonObject("main");
        JsonArray weather = gson.fromJson(response, JsonObject.class).getAsJsonArray("weather");

        temperature = (int) Math.round(main.get("temp").getAsDouble());
        weatherDescription = turnWeatherDescriptionToEnum(weather.get(0).getAsJsonObject().get("main").getAsString());

        return new Weather(cityName, temperature, LocalDateTime.now(), weatherDescription);
    }

    @Override
    public boolean checkCity(String cityName) {
        String response = null;
        try {
            response = restTemplate.getForObject(GEO_URL_BEGIN + "q={city}&appid=" + API_ID, String.class, cityName);
        } catch (Exception e) {
            System.out.println("City not found");
            response = "[]";
        }

        return !response.equals("[]");
    }

    @Override
    public String getEngCityAndCountryName(String cityName) {
        String response = null;
        try {
            response = restTemplate.getForObject(GEO_URL_BEGIN + "q={city}&appid=" + API_ID, String.class, cityName);
        } catch (Exception e) {
            return null;
        }

        String engCityName = null;

        try {
            engCityName = gson.fromJson(response, JsonArray.class).
                    get(0).
                    getAsJsonObject().
                    getAsJsonObject("local_names").
                    get("en").
                    toString().
                    replaceAll("\"", "");
        } catch (Exception e) {
            engCityName = gson.fromJson(response, JsonArray.class).
                    get(0).
                    getAsJsonObject().
                    get("name").
                    toString().
                    replaceAll("\"", "");
        }

        String countryName = gson.fromJson(response, JsonArray.class).
                get(0).
                getAsJsonObject().
                get("country").
                toString().
                replaceAll("\"", "");

        return engCityName  + ", " + countryName;
    }

    @Override
    public List<Weather> getForecast(String cityName) {
        String response = null;
        try {
            response = restTemplate.getForObject(FORECAST_URL_BEGIN + "q={city}&appid=" + API_ID + "&units=" + UNITS, String.class, cityName);
        } catch (Exception e) {
            return null;
        }

        List<Weather> forecast = new ArrayList<>();
        List<Long> unixDateDays = new ArrayList<>();

        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime currentDateNoon = LocalDate.now().atTime(12, 0);


        for (int i = 0; i < 5; i++) {
            if (currentDate.isBefore(LocalDate.now().atTime(13, 0))) {
                unixDateDays.add(currentDateNoon.plusDays(i).atZone(zoneId).toEpochSecond());
            } else {
                unixDateDays.add(currentDateNoon.plusDays(i + 1).atZone(zoneId).toEpochSecond());
            }
        }

        try {
            JsonArray forecastList = gson.fromJson(response, JsonObject.class).getAsJsonArray("list");

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
                                convertedUnixTimeStamp,
                                turnWeatherDescriptionToEnum(weather.get(0).getAsJsonObject().get("main").getAsString()));
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

    private WeatherDescription turnWeatherDescriptionToEnum(String weatherDescription) {
        return switch (weatherDescription) {
            case "Clear sky", "Clear" -> WeatherDescription.CLEAR;
            case "Few clouds", "Scattered clouds" -> WeatherDescription.CLOUDY;
            case "Broken clouds", "Clouds" -> WeatherDescription.CLOUDS;
            case "Shower rain", "Rain", "Drizzle" -> WeatherDescription.RAIN;
            case "Thunderstorm" -> WeatherDescription.STORM;
            case "Snow" -> WeatherDescription.SNOW;
            case "Mist", "Smoke", "Haze", "Dust", "Fog" -> WeatherDescription.MIST;
            default -> WeatherDescription.UNKNOWN;
        };
    }
}
