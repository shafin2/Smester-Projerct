package com.example.hardwareshop2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Driver extends Application {
    public static Stage stage;
    @Override
    public void start(Stage stage) throws IOException {
       this.stage=stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Driver.class.getResource("login-view.fxml"));
        Scene scene=new Scene(fxmlLoader.load());
        stage.setTitle("Al-Rehman Store");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}