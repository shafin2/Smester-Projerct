package com.example.hardwareshop2;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class SignInFormScene {
    public static Scene getScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Driver.class.getResource("SignInForm"));
        Scene scene=new Scene(fxmlLoader.load());
        return scene;
    }
}
