package com.example.hardwareshop2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Driver extends Application {
    private static Stage stage;
    public static void changeScene(String fxml)throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Driver.class.getResource(fxml));
        Scene scene=new Scene(fxmlLoader.load());
        stage.setTitle("Al-Rehman Store");
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void start(Stage stage) throws IOException {
       this.stage=stage;
       changeScene("login-view.fxml");
    }

    public static void main(String[] args) {
        launch();
    }
}