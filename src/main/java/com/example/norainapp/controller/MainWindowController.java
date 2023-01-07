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
import javafx.stage.Stage;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainWindowController extends BaseController implements Initializable {
    @FXML Label poweredBy;
    @FXML NestedController leftNestedController;
    @FXML NestedController rightNestedController;

    public MainWindowController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (SavedProperties.leftCityName != null) {
            leftNestedController.showWeatherAction(SavedProperties.leftCityName);
        }

        if (SavedProperties.rightCityName != null) {
            rightNestedController.showWeatherAction(SavedProperties.rightCityName);
        }
    }

    public void closeAction() {
        Stage stage = (Stage) poweredBy.getScene().getWindow();
        viewFactory.closeStage(stage);
    }
}