package com.example.norainapp.model.client;

import com.example.norainapp.model.Weather;
import com.example.norainapp.model.WeatherDescription;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OpenWeatherMapClientTest {
    @Mock
    OpenWeatherMapClient openWeatherMapClient = mock(OpenWeatherMapClient.class);

    @Test
    void getWeatherReturnsWeather() {
        //Given
        Weather givenWeather = new Weather("Kraków", 10, LocalDateTime.now(), WeatherDescription.CLOUDY);
        when(openWeatherMapClient.getWeather("Kraków")).thenReturn(givenWeather);

        //When
        Weather weather = openWeatherMapClient.getWeather("Kraków");

        //Then
        assertThat(weather, is(notNullValue()));
    }

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