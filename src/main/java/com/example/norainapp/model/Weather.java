package com.example.norainapp.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Weather {
    private final String cityName;
    private final int tempInCelsius;
    private final LocalDateTime date;
    private final WeatherDescription weatherDescription;

    public Weather(String cityName, int tempInCelsius, LocalDateTime date, WeatherDescription weatherDescription) {
        this.cityName = cityName;
        this.tempInCelsius = tempInCelsius;
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

    public WeatherDescription getWeatherDescription() {
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
                ", date=" + date +
                ", weatherDescription='" + weatherDescription + '\'' +
                '}';
    }
}
