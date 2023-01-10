package com.example.norainapp.model.client;

import com.example.norainapp.model.Weather;
import com.example.norainapp.model.WeatherDescription;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OpenWeatherMapClientTest {
    @Mock
    private OpenWeatherMapClient openWeatherMapClient = mock(OpenWeatherMapClient.class);

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
    void getWeatherCityNotFoundShouldReturnNull() {
        //Given
        when(openWeatherMapClient.getWeather("Kraków")).thenReturn(null);

        //When
        Weather weather = openWeatherMapClient.getWeather("Kraków");

        //Then
        assertThat(openWeatherMapClient.getWeather("Kraków"), is(nullValue()));
    }

    @Test
    void checkCityShouldReturnTrueIfCityWasFound() {
        //Given
        when(openWeatherMapClient.checkCity("Kraków")).thenReturn(true);

        //When
        boolean ifCityExist = openWeatherMapClient.checkCity("Kraków");

        //Then
        assertThat(ifCityExist, is(true));
    }

    @Test
    void checkCityCityNotFoundShouldReturnFalse() {
        //Given
        when(openWeatherMapClient.checkCity("Kraków")).thenReturn(false);

        //When
        boolean ifCityExist = openWeatherMapClient.checkCity("Kraków");

        //Then
        assertThat(ifCityExist, is(false));
    }

    @Test
    void getEngCityAndCountryNameShouldReturnString() {
        //Given
        when(openWeatherMapClient.getEngCityAndCountryName("Kraków")).thenReturn("Cracov, PL");

        //When
        String engCityAndCountryName = openWeatherMapClient.getEngCityAndCountryName("Kraków");

        //Then
        assertThat(engCityAndCountryName, is("Cracov, PL"));
    }

    @Test
    void getEngCityAndCountryNameShouldHaveOneCommaAndSpace() {
        //Given
        when(openWeatherMapClient.getEngCityAndCountryName("Kraków")).thenReturn("Cracov, PL");

        //When
        String engCityAndCountryName = openWeatherMapClient.getEngCityAndCountryName("Kraków");

        //Then
        assertThat(engCityAndCountryName, CoreMatchers.containsString(", "));

    }

    @Test
    void getEngCityAndCountryNameCityNotFoundShouldReturnNull() {
        //Given
        when(openWeatherMapClient.getEngCityAndCountryName("Kraków")).thenReturn(null);

        //When
        String cityNotFoundName = openWeatherMapClient.getEngCityAndCountryName("Kraków");

        //Then
        assertThat(cityNotFoundName, is(nullValue()));
    }

    @Test
    void getForecastShouldReturnForecastListWithFiveElements() {
        //Given
        List<Weather> sampleForecast = createSampleForecast();
        when(openWeatherMapClient.getForecast("Kraków")).thenReturn(sampleForecast);

        //When
        List<Weather> forecast = openWeatherMapClient.getForecast("Kraków");

        //Then
        assertThat(forecast, hasSize(5));
    }

    @Test
    void getForecastCityNotFoundShouldReturnNull() {
        //Given
        when(openWeatherMapClient.getForecast("Kraków")).thenReturn(null);

        //When
        List<Weather> forecast = openWeatherMapClient.getForecast("Kraków");

        //Then
        assertThat(forecast, is(nullValue()));
    }

    private List<Weather> createSampleForecast() {
        String cityName = "Kraków";

        Weather weatherOne = new Weather(cityName, 10, LocalDateTime.now(), WeatherDescription.CLOUDY);
        Weather weatherTwo= new Weather(cityName, 10, LocalDateTime.now(), WeatherDescription.CLOUDY);
        Weather weatherThree = new Weather(cityName, 10, LocalDateTime.now(), WeatherDescription.CLOUDY);
        Weather weatherFour = new Weather(cityName, 10, LocalDateTime.now(), WeatherDescription.CLOUDY);
        Weather weatherFive = new Weather(cityName, 10, LocalDateTime.now(), WeatherDescription.CLOUDY);

        return Arrays.asList(weatherOne, weatherTwo, weatherThree, weatherFour, weatherFive);
    }
}