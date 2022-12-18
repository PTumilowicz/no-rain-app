package com.example.norainapp.controller;

import com.example.norainapp.SavedProperties;
import com.example.norainapp.model.WeatherService;
import com.example.norainapp.model.WeatherServiceFactory;
import com.example.norainapp.model.client.WeatherClient;
import com.example.norainapp.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LeftCityModalController extends BaseController implements Initializable {
    @FXML
    private Label errorLabel;
    @FXML
    private TextField leftCityTextField;
    private WeatherService weatherService;

    @FXML
    void chooseLeftCityAction() {
        String cityInput;
        if (leftCityTextField.getText() == "") {
            errorLabel.setText("You didn't choose a city!");
        }

        cityInput = leftCityTextField.getText();
        WeatherClient weatherClient = weatherService.getWeatherClient();

        if (!weatherClient.checkCity(cityInput)) {
            errorLabel.setText("City not found.");
            return;
        }

        SavedProperties.leftCityName = leftCityTextField.getText();

        exitModalAction();
        viewFactory.showMainWindow();
    }

    @FXML
    void exitModalAction() {
        Stage stage = (Stage) leftCityTextField.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    public LeftCityModalController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        weatherService = WeatherServiceFactory.createWeatherService();
    }
}
