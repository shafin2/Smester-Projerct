package com.example.hardwareshop2;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class LoginScene {

    public static Scene getScene()throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginScene.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        return scene;
    }
}
