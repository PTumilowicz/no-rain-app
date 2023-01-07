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
    private final SavedProperties savedProperties;

    public MainWindowController(ViewFactory viewFactory, String fxmlName) throws IOException {
        super(viewFactory, fxmlName);
        savedProperties = new SavedProperties();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        leftNestedController.setLeft(true);
        rightNestedController.setLeft(false);

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

    public void closeAction() {
        Stage stage = (Stage) poweredBy.getScene().getWindow();
        viewFactory.closeStage(stage);
    }
}