package com.example.norainapp.controller;

import com.example.norainapp.SavedProperties;
import com.example.norainapp.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController extends BaseController implements Initializable {
    @FXML Label poweredBy;
    @FXML NestedController leftNestedController;
    @FXML NestedController rightNestedController;
    private final SavedProperties savedProperties = new SavedProperties();

    public MainWindowController(ViewFactory viewFactory, String fxmlName) throws IOException {
        super(viewFactory, fxmlName);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            leftNestedController.showWeatherAction(savedProperties.getLeftCityName());
        } catch (Exception e) {
            leftNestedController.showCityNotChosen();
        }

        try {
            rightNestedController.showWeatherAction(savedProperties.getRightCityName());
        } catch (Exception e) {
            rightNestedController.showCityNotChosen();
        }
    }

    public void chooseLeftCityAction() throws IOException {
        leftNestedController.chooseCityAction(this::saveLeftName);
    }

    public void chooseRightCityAction() throws IOException {
        rightNestedController.chooseCityAction(this::saveRightName);
    }

    public void closeAction() {
        Stage stage = (Stage) poweredBy.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    private void saveLeftName(String cityName) {
        try {
            savedProperties.setLeftCityName(cityName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveRightName(String cityName) {
        try {
            savedProperties.setRightCityName(cityName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}