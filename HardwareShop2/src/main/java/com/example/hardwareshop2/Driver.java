package com.example.hardwareshop2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Driver extends Application {
    public static Stage stage1;
    public static void changeScene(String str) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Driver.class.getResource(str));
        Scene scene=new Scene(fxmlLoader.load());
        if(str.compareTo("Login-view.fxml")==0){
            stage1.setResizable(false);
        }
        else{
            stage1.setResizable(true);
        }
        stage1.centerOnScreen();
        stage1.setScene(scene);
    }
    @Override
    public void start(Stage stage) throws IOException {
        this.stage1=stage;
        stage1.setTitle("Al-Rehman Store");
        changeScene("Login-view.fxml");
        stage1.show();
    }

    public static void main(String[] args) {
        launch();
    }
}