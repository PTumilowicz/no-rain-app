package com.example.norainapp.model.client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
class OpenWeatherMapClientTest {
    OpenWeatherMapClient openWeatherMapClient = new OpenWeatherMapClient();
    @Test
    void getWeatherCityNotFoundShouldNotBreakTheMethod() {
        //Then
        assertDoesNotThrow(() -> openWeatherMapClient.getWeather(""));
    }

    @Test
    void checkCityShouldNotBreakTheMethod() {
        //Then
        assertDoesNotThrow(() -> openWeatherMapClient.checkCity(""));
    }

    @Test
    void getEngCityAndCountryNameCityNotFoundShouldNotBreakTheMethod() {
        //Then
        assertDoesNotThrow(() -> openWeatherMapClient.getEngCityAndCountryName(""));
    }

    @Test
    void getForecastCityNotFoundShouldNoyBreakTheMethod() {
        //Then
        assertDoesNotThrow(() -> openWeatherMapClient.getForecast(""));
    }
}