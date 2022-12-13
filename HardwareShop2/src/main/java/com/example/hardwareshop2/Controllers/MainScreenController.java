package com.example.hardwareshop2.Controllers;

import com.example.hardwareshop2.Driver;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class MainScreenController {
    @FXML
    void addUserWin(MouseEvent event) throws IOException {
        Driver.changeScene("SignUp.fxml");
    }

    @FXML
    void logOutWin(MouseEvent event) throws IOException {
        Driver.changeScene("login-view.fxml");
    }
}
