package com.example.norainapp.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Weather {
    private String cityName;
    private int tempInCelsius;
    private double feelsLike;
    private LocalDateTime date;
    private String weatherDescription;

    public Weather(String cityName, int tempInCelsius, double feelsLike, LocalDateTime date, String weatherDescription) {
        this.cityName = cityName;
        this.tempInCelsius = tempInCelsius;
        this.feelsLike = feelsLike;
        this.date = date;
        this.weatherDescription = weatherDescription;
    }

    public String getCityName() {
        return cityName;
    }

    public int getTempInCelsius() {
        return tempInCelsius;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public double getFeelsLike() {
        return feelsLike;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Weather weather)) return false;
        return Double.compare(weather.tempInCelsius, tempInCelsius) == 0 && Objects.equals(cityName, weather.cityName) && Objects.equals(date, weather.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityName, tempInCelsius, date);
    }

    @Override
    public String toString() {
        return "Weather{" +
                "cityName='" + cityName + '\'' +
                ", tempInCelsius=" + tempInCelsius +
                ", feelsLike=" + feelsLike +
                ", date=" + date +
                ", weatherDescription='" + weatherDescription + '\'' +
                '}';
    }
}
