package com.example.hardwareshop2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Driver extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Al-Rehman Store");
        stage.setScene(LoginScene.getScene());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}