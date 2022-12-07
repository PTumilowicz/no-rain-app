package com.example.norainapp.controller;

import com.example.norainapp.model.WeatherService;
import com.example.norainapp.model.WeatherServiceFactory;
import com.example.norainapp.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController extends BaseController implements Initializable {
    @FXML
    private ImageView leftCityImage;

    @FXML
    private Label leftCityName;

    @FXML
    private Label leftCityTemp;

    @FXML
    private ImageView rightCityImage;

    @FXML
    private Label rightCityName;

    @FXML
    private Label rightCitytemp;

    @FXML
    void showWeatherAction() {
        String cityName = "Kraków";


    }

    private WeatherService weatherService;

    public MainWindowController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        weatherService = WeatherServiceFactory.createWeatherService();
        leftCityTemp.setVisible(false);
        leftCityName.setVisible(false);
    }
}