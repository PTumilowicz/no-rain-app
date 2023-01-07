package com.example.norainapp.view;

import com.example.norainapp.Launcher;
import com.example.norainapp.controller.BaseController;
import com.example.norainapp.controller.CityModalController;
import com.example.norainapp.controller.MainWindowController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Consumer;

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

        scene.getStylesheets().clear();
        scene.getStylesheets().add(getClass().getResource("css/styles.css").toExternalForm());

        stage.initStyle(StageStyle.UNDECORATED);

        stage.setScene(scene);
        stage.show();
        activeStages.add(stage);
    }

    public void showMainWindow() throws IOException {
        BaseController controller = new MainWindowController(this, "mainWindowView.fxml");
        initializeStage(controller);
    }

    public void showCityModal(Consumer<String> cityCallback, boolean isLeft) throws IOException {
        BaseController controller = new CityModalController(this, "cityModalView.fxml", cityCallback, isLeft);
        initializeStage(controller);
    }

    public void closeStage(Stage stageToClose) {
        stageToClose.close();
        activeStages.remove(stageToClose);
    }
}
