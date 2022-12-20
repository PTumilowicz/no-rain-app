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
import javafx.stage.Stage;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainWindowController extends BaseController implements Initializable {
    private WeatherService weatherService;
    private Weather leftWeather;
    private Weather rightWeather;
    private ArrayList<Weather> leftForecast;
    private ArrayList<Weather> rightForecast;

    @FXML
    private ImageView closeImage;
    @FXML
    private Label leftCityDateTime;
    @FXML
    private Label rightCityDateTime;
    @FXML
    private Label leftCityFifthDayDate;
    @FXML
    private ImageView leftCityFifthDayImg;
    @FXML
    private Label leftCityFifthDayTemp;
    @FXML
    private Label leftCityFirstDayDate;
    @FXML
    private ImageView leftCityFirstDayImg;
    @FXML
    private Label leftCityFirstDayTemp;
    @FXML
    private Label leftCityFourthDayDate;
    @FXML
    private ImageView leftCityFourthDayImg;
    @FXML
    private Label leftCityFourthDayTemp;
    @FXML
    private Label leftCityName;
    @FXML
    private Label leftCitySecondDayDate;
    @FXML
    private ImageView leftCitySecondDayImg;
    @FXML
    private Label leftCitySecondDayTemp;
    @FXML
    private Label leftCityTempMax;
    @FXML
    private Label leftCityThirdDayDate;
    @FXML
    private ImageView leftCityThirdDayImg;
    @FXML
    private Label leftCityThirdDayTemp;
    @FXML
    private ImageView leftCityWeatherImg;
    @FXML
    private Label rightCityFifthDayDate;
    @FXML
    private ImageView rightCityFifthDayImg;
    @FXML
    private Label rightCityFifthDayTemp;
    @FXML
    private Label rightCityFirstDayDate;
    @FXML
    private ImageView rightCityFirstDayImg;
    @FXML
    private Label rightCityFirstDayTemp;
    @FXML
    private Label rightCityFourthDayDate;
    @FXML
    private ImageView rightCityFourthDayImg;
    @FXML
    private Label rightCityFourthDayTemp;
    @FXML
    private Label rightCityName;
    @FXML
    private Label rightCitySecondDayDate;
    @FXML
    private ImageView rightCitySecondDayImg;
    @FXML
    private Label rightCitySecondDayTemp;
    @FXML
    private Label rightCityTempMax;
    @FXML
    private Label rightCityThirdDayDate;
    @FXML
    private ImageView rightCityThirdDayImg;
    @FXML
    private Label rightCityThirdDayTemp;
    @FXML
    private ImageView rightCityWeatherImg;
    @FXML
    void chooseLeftCityAction() {
        Stage stage = (Stage) leftCityName.getScene().getWindow();
        viewFactory.closeStage(stage);
        viewFactory.showLeftCityModal();
    }

    @FXML
    void chooseRightCityAction() {
        Stage stage = (Stage) rightCityName.getScene().getWindow();
        viewFactory.closeStage(stage);
        viewFactory.showRightCityModal();
    }

    @FXML
    void closeAction() {
        Stage stage = (Stage) rightCityName.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    void showLeftWeatherAction(String cityName) {
        WeatherClient weatherClient = weatherService.getWeatherClient();

        // Get the current weather and the forecast for selected city.
        leftWeather = weatherClient.getWeather(cityName);
        leftForecast = weatherClient.getForecast(cityName);

        // Show the current weather for selected city
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM HH:mm");

        leftCityName.setVisible(true);
        leftCityName.setText(leftWeather.getCityName());
        leftCityTempMax.setVisible(true);
        leftCityTempMax.setText(leftWeather.getTempInCelsius() + " °C");
        leftCityDateTime.setText(leftWeather.getDate().format(dateTimeFormatter));

        Image image = switch (leftWeather.getWeatherDescription()) {
            case "Clear sky", "Clear" -> new Image("sun.png");
            case "Few clouds" -> new Image("cloud_sun.png");
            case "Scattered clouds", "Broken clouds", "Clouds" -> new Image("cloud.png");
            case "Shower rain", "Rain", "Drizzle" -> new Image("rain.png");
            case "Thunderstorm" -> new Image("storm.png");
            case "Snow" -> new Image("snow.png");
            case "Mist", "Smoke", "Haze", "Dust", "Fog" -> new Image("mist.png");
            default -> new Image("sun.png");
        };

        leftCityWeatherImg.setImage(image);

        // Show the forecast for selected city
        leftCityFirstDayTemp.setText(leftForecast.get(0).getTempInCelsius() + " °C");
        leftCitySecondDayTemp.setText(leftForecast.get(1).getTempInCelsius() + " °C");
        leftCityThirdDayTemp.setText(leftForecast.get(2).getTempInCelsius() + " °C");
        leftCityFourthDayTemp.setText(leftForecast.get(3).getTempInCelsius() + " °C");
        leftCityFifthDayTemp.setText(leftForecast.get(4).getTempInCelsius() + " °C");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE");

        leftCityFirstDayDate.setText(leftForecast.get(0).getDate().format(formatter));
        leftCitySecondDayDate.setText(leftForecast.get(1).getDate().format(formatter));
        leftCityThirdDayDate.setText(leftForecast.get(2).getDate().format(formatter));
        leftCityFourthDayDate.setText(leftForecast.get(3).getDate().format(formatter));
        leftCityFifthDayDate.setText(leftForecast.get(4).getDate().format(formatter));
    }

    void showRightWeatherAction(String cityName) {
        WeatherClient weatherClient = weatherService.getWeatherClient();

        // Get the current weather and the forecast for selected city.
        rightWeather = weatherClient.getWeather(cityName);
        rightForecast = weatherClient.getForecast(cityName);

        // Show the current weather for selected city
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM HH:mm");

        rightCityName.setVisible(true);
        rightCityName.setText(rightWeather.getCityName());
        rightCityTempMax.setVisible(true);
        rightCityTempMax.setText(rightWeather.getTempInCelsius() + " °C");
        rightCityDateTime.setText(rightWeather.getDate().format(dateTimeFormatter));

        System.out.println(rightWeather.getWeatherDescription());

        Image image = switch (rightWeather.getWeatherDescription()) {
            case "Clear sky", "Clear" -> new Image("sun.png");
            case "Few clouds" -> new Image("cloud_sun.png");
            case "Scattered clouds", "Broken clouds", "Clouds" -> new Image("cloud.png");
            case "Shower rain", "Rain", "Drizzle" -> new Image("rain.png");
            case "Thunderstorm" -> new Image("storm.png");
            case "Snow" -> new Image("snow.png");
            case "Mist", "Smoke", "Haze", "Dust", "Fog" -> new Image("mist.png");
            default -> new Image("sun.png");
        };

        rightCityWeatherImg.setImage(image);

        // Show the forecast for selected city
        rightCityFirstDayTemp.setText(rightForecast.get(0).getTempInCelsius() + " °C");
        rightCitySecondDayTemp.setText(rightForecast.get(1).getTempInCelsius() + " °C");
        rightCityThirdDayTemp.setText(rightForecast.get(2).getTempInCelsius() + " °C");
        rightCityFourthDayTemp.setText(rightForecast.get(3).getTempInCelsius() + " °C");
        rightCityFifthDayTemp.setText(rightForecast.get(4).getTempInCelsius() + " °C");

        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("EEE");

        rightCityFirstDayDate.setText(rightForecast.get(0).getDate().format(dayFormatter));
        rightCitySecondDayDate.setText(rightForecast.get(1).getDate().format(dayFormatter));
        rightCityThirdDayDate.setText(rightForecast.get(2).getDate().format(dayFormatter));
        rightCityFourthDayDate.setText(rightForecast.get(3).getDate().format(dayFormatter));
        rightCityFifthDayDate.setText(rightForecast.get(4).getDate().format(dayFormatter));
    }

    public MainWindowController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        weatherService = WeatherServiceFactory.createWeatherService();

        if (SavedProperties.leftCityName != null) {
            showLeftWeatherAction(SavedProperties.leftCityName);
        }

        if (SavedProperties.rightCityName != null) {
            showRightWeatherAction(SavedProperties.rightCityName);
        }
    }
}