package com.example.norainapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Weather {
    private String cityName;
    private double tempInCelsius;
    private LocalDate date;

    public Weather(String cityName, double tempInCelsius, LocalDate date) {
        this.cityName = cityName;
        this.tempInCelsius = tempInCelsius;
        this.date = date;
    }

    public String getCityName() {
        return cityName;
    }

    public double getTempInCelsius() {
        return tempInCelsius;
    }

    public LocalDate getDate() {
        return date;
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
                '}';
    }
}
