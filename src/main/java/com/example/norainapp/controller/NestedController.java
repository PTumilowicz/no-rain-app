package com.example.norainapp.controller;

import com.example.norainapp.SavedProperties;
import com.example.norainapp.model.Weather;
import com.example.norainapp.model.WeatherService;
import com.example.norainapp.model.WeatherServiceFactory;
import com.example.norainapp.model.client.WeatherClient;
import com.example.norainapp.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class NestedController implements Initializable {
    @FXML
    private HBox forecastBox;
    @FXML
    private Label cityDateTime;
    @FXML
    private Label cityFifthDayDate;
    @FXML
    private ImageView cityFifthDayImg;
    @FXML
    private Label cityFifthDayTemp;
    @FXML
    private Label cityFirstDayDate;
    @FXML
    private ImageView cityFirstDayImg;
    @FXML
    private Label cityFirstDayTemp;
    @FXML
    private Label cityFourthDayDate;
    @FXML
    private ImageView cityFourthDayImg;
    @FXML
    private Label cityFourthDayTemp;
    @FXML
    private Label cityName;
    @FXML
    private Label citySecondDayDate;
    @FXML
    private ImageView citySecondDayImg;
    @FXML
    private Label citySecondDayTemp;
    @FXML
    private Label cityTempMax;
    @FXML
    private Label cityThirdDayDate;
    @FXML
    private ImageView cityThirdDayImg;
    @FXML
    private Label cityThirdDayTemp;
    @FXML
    private ImageView cityWeatherImg;
    private WeatherService weatherService;
    private ViewFactory viewFactory = new ViewFactory();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        weatherService = WeatherServiceFactory.createWeatherService();
    }

    @FXML
    void chooseCityAction() {
        viewFactory.showCityModal(this::showWeatherAction);
    }

    void showWeatherAction(String cityName) {
        WeatherClient weatherClient = weatherService.getWeatherClient();
        Weather weather;
        ArrayList<Weather> forecast;
        String cityAndCountryName = weatherClient.getEngCityAndCountryName(cityName);

        // Get the current weather and the forecast for selected city.
        try {
            weather = weatherClient.getWeather(cityName);
            forecast = weatherClient.getForecast(cityName);
        } catch (Exception e) {
            System.out.println("Error happened. No weather available for this city.");
            SavedProperties.leftCityName = "";
            return;
        }

        // Show the current weather for selected city
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM HH:mm");

        this.cityName.setVisible(true);
        this.cityName.setText(cityAndCountryName);
        this.cityTempMax.setVisible(true);
        this.cityTempMax.setText(weather.getTempInCelsius() + " °C");
        this.cityDateTime.setVisible(true);
        this.cityDateTime.setText(weather.getDate().format(dateTimeFormatter));

        Image image = getWeatherImage(weather.getWeatherDescription());
        this.cityWeatherImg.setImage(image);

        // Show the forecast for selected city
        this.forecastBox.setVisible(true);
        this.cityFirstDayTemp.setText(forecast.get(0).getTempInCelsius() + " °C");
        this.citySecondDayTemp.setText(forecast.get(1).getTempInCelsius() + " °C");
        this.cityThirdDayTemp.setText(forecast.get(2).getTempInCelsius() + " °C");
        this.cityFourthDayTemp.setText(forecast.get(3).getTempInCelsius() + " °C");
        this.cityFifthDayTemp.setText(forecast.get(4).getTempInCelsius() + " °C");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE");

        this.cityFirstDayDate.setText(forecast.get(0).getDate().format(formatter));
        image = getWeatherImage(forecast.get(0).getWeatherDescription());
        this.cityFirstDayImg.setImage(image);
        this.citySecondDayDate.setText(forecast.get(1).getDate().format(formatter));
        image = getWeatherImage(forecast.get(1).getWeatherDescription());
        this.citySecondDayImg.setImage(image);
        this.cityThirdDayDate.setText(forecast.get(2).getDate().format(formatter));
        image = getWeatherImage(forecast.get(2).getWeatherDescription());
        this.cityThirdDayImg.setImage(image);
        this.cityFourthDayDate.setText(forecast.get(3).getDate().format(formatter));
        image = getWeatherImage(forecast.get(3).getWeatherDescription());
        this.cityFourthDayImg.setImage(image);
        this.cityFifthDayDate.setText(forecast.get(4).getDate().format(formatter));
        image = getWeatherImage(forecast.get(4).getWeatherDescription());
        this.cityFifthDayImg.setImage(image);
    }

    private Image getWeatherImage(String weatherDescription) {
        return switch (weatherDescription) {
            case "Clear sky", "Clear" -> getImage("sun.png");
            case "Few clouds", "Scattered clouds" -> getImage("cloud_sun.png");
            case "Broken clouds", "Clouds" -> getImage("cloud.png");
            case "Shower rain", "Rain", "Drizzle" -> getImage("rain.png");
            case "Thunderstorm" -> getImage("storm.png");
            case "Snow" -> getImage("snow.png");
            case "Mist", "Smoke", "Haze", "Dust", "Fog" -> getImage("mist.png");
            default -> getImage("pig.png");
        };
    }

    private Image getImage(String iconPath) {
        return new Image(this.getClass().getResourceAsStream("/images/%s".formatted(iconPath)));
    }
}
