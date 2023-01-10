package com.example.norainapp.controller;

import com.example.norainapp.controller.services.WeatherImageService;
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

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;

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
    private DateTimeFormatter dateTimeFormatter;
    private final WeatherImageService weatherImageService = new WeatherImageService();
    private final ViewFactory viewFactory = new ViewFactory();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        weatherService = WeatherServiceFactory.createWeatherService();
    }

    @FXML
    void chooseCityAction(Consumer<String> cityNameToSave) throws IOException {
        viewFactory.showCityModal(this::showWeatherAction, cityNameToSave);
    }

    void showWeatherAction(String cityName) {
        WeatherClient weatherClient = weatherService.getWeatherClient();
        Weather weather;
        List<Weather> forecast;
        String cityAndCountryName = weatherClient.getEngCityAndCountryName(cityName);

        // Get the current weather and the forecast for selected city.
        try {
            weather = weatherClient.getWeather(cityName);
            forecast = weatherClient.getForecast(cityName);
        } catch (Exception e) {
            System.out.println("Error happened. No weather available for this city.");
            return;
        }

        // Show the current weather for selected city
        dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM HH:mm");

        this.cityName.setVisible(true);
        this.cityName.setText(cityAndCountryName);
        this.cityTempMax.setVisible(true);
        this.cityTempMax.setText(weather.getTempInCelsius() + " °C");
        this.cityDateTime.setVisible(true);
        this.cityDateTime.setText(weather.getDate().format(dateTimeFormatter));

        Image image = weatherImageService.getWeatherImage(weather.getWeatherDescription());
        this.cityWeatherImg.setImage(image);

        // Show the forecast for selected city
        this.forecastBox.setVisible(true);
        this.cityFirstDayTemp.setText(forecast.get(0).getTempInCelsius() + " °C");
        this.citySecondDayTemp.setText(forecast.get(1).getTempInCelsius() + " °C");
        this.cityThirdDayTemp.setText(forecast.get(2).getTempInCelsius() + " °C");
        this.cityFourthDayTemp.setText(forecast.get(3).getTempInCelsius() + " °C");
        this.cityFifthDayTemp.setText(forecast.get(4).getTempInCelsius() + " °C");

        dateTimeFormatter = DateTimeFormatter.ofPattern("EEE");

        this.cityFirstDayDate.setText(forecast.get(0).getDate().format(dateTimeFormatter));
        image = weatherImageService.getWeatherImage(forecast.get(0).getWeatherDescription());
        this.cityFirstDayImg.setImage(image);
        this.citySecondDayDate.setText(forecast.get(1).getDate().format(dateTimeFormatter));
        image = weatherImageService.getWeatherImage(forecast.get(1).getWeatherDescription());
        this.citySecondDayImg.setImage(image);
        this.cityThirdDayDate.setText(forecast.get(2).getDate().format(dateTimeFormatter));
        image = weatherImageService.getWeatherImage(forecast.get(2).getWeatherDescription());
        this.cityThirdDayImg.setImage(image);
        this.cityFourthDayDate.setText(forecast.get(3).getDate().format(dateTimeFormatter));
        image = weatherImageService.getWeatherImage(forecast.get(3).getWeatherDescription());
        this.cityFourthDayImg.setImage(image);
        this.cityFifthDayDate.setText(forecast.get(4).getDate().format(dateTimeFormatter));
        image = weatherImageService.getWeatherImage(forecast.get(4).getWeatherDescription());
        this.cityFifthDayImg.setImage(image);
    }

    void showCityNotChosen() {
        this.cityTempMax.setText("City not chosen");
    }
}
