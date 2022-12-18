package com.example.norainapp.view;

import com.example.norainapp.Launcher;
import com.example.norainapp.controller.BaseController;
import com.example.norainapp.controller.LeftCityModalController;
import com.example.norainapp.controller.MainWindowController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ViewFactory {
    private ArrayList<Stage> activeStages;

    public ViewFactory() {
        activeStages = new ArrayList<>();
    }

    private void initializeStage(BaseController controller) {
        FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource(controller.getFxmlName()));
        fxmlLoader.setController(controller);

        Parent parent;

        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scene scene = new Scene(parent);
        Stage stage = new Stage();

        stage.setTitle("Complain that there's... - NoRain App");
        stage.setScene(scene);
        stage.show();
        activeStages.add(stage);
    }

    public void showMainWindow() {
        BaseController controller = new MainWindowController(this, "mainWindowView.fxml");
        initializeStage(controller);
    }

    public void showLeftCityModal() {
        BaseController controller = new LeftCityModalController(this, "leftCityModalView.fxml");
        initializeStage(controller);
    }

    public void closeStage(Stage stageToClose) {
        stageToClose.close();
        activeStages.remove(stageToClose);
    }
}
