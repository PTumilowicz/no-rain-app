module com.example.norainapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.norainapp to javafx.fxml;
    exports com.example.norainapp;
    exports com.example.norainapp.controller;
    opens com.example.norainapp.controller to javafx.fxml;
}