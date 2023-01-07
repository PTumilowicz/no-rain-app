package com.example.norainapp.controller;

import com.example.norainapp.model.WeatherService;
import com.example.norainapp.model.WeatherServiceFactory;
import com.example.norainapp.model.client.WeatherClient;
import com.example.norainapp.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class CityModalController extends BaseController implements Initializable {
    private final Consumer<String> cityCallback;
    @FXML
    private Label errorLabel;
    @FXML
    private TextField cityTextField;
    private WeatherService weatherService;

    public CityModalController(ViewFactory viewFactory, String fxmlName, Consumer<String> cityCallback) {
        super(viewFactory, fxmlName);
        this.cityCallback = cityCallback;
    }

    @FXML
    void chooseCityAction() {
        String cityInput;
        if (cityTextField.getText().isEmpty()) {
            errorLabel.setText("You didn't choose a city!");
            return;
        }

        cityInput = cityTextField.getText();
        WeatherClient weatherClient = weatherService.getWeatherClient();

        if (!weatherClient.checkCity(cityInput)) {
            errorLabel.setText("City not found. Try again.");
            return;
        }

        cityCallback.accept(cityInput);
        exitModalAction();
    }

    @FXML
    void exitModalAction() {
        Stage stage = (Stage) cityTextField.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        weatherService = WeatherServiceFactory.createWeatherService();
    }
}
