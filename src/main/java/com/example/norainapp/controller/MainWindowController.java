package com.example.norainapp.controller;

import com.example.norainapp.SavedProperties;
import com.example.norainapp.model.Weather;
import com.example.norainapp.model.WeatherService;
import com.example.norainapp.model.WeatherServiceFactory;
import com.example.norainapp.model.client.WeatherClient;
import com.example.norainapp.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController extends BaseController implements Initializable {
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
    private Label leftCityTempFeel;

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
    private Label rightCityTempFeel;

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
    void chooseBaseCityAction() {
        Stage stage = (Stage) leftCityName.getScene().getWindow();
        viewFactory.closeStage(stage);
        viewFactory.showLeftCityModal();
    }

    @FXML
    void chooseDestCityAction() {

    }

    @FXML
    void showWeatherAction(String cityName) {
        WeatherClient weatherClient = weatherService.getWeatherClient();
        leftWeather = weatherClient.getWeather(cityName);
        leftCityName.setVisible(true);
        leftCityName.setText(leftWeather.getCityName());
        leftCityTempMax.setVisible(true);
        leftCityTempMax.setText("" + leftWeather.getTempInCelsius());
    }

    private WeatherService weatherService;
    private Weather leftWeather;

    public MainWindowController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        weatherService = WeatherServiceFactory.createWeatherService();

        if (SavedProperties.leftCityName != null) {
            showWeatherAction(SavedProperties.leftCityName);
        }
    }
}