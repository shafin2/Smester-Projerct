package com.example.hardwareshop2.Controllers;

import com.example.hardwareshop2.Driver;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class MainScreenController {
        @FXML
        void initialize() {
            if (User.userStatus.compareTo("Admin")==0){
                AddUserBtn.setVisible(true);
            }
            else {
                AddUserBtn.setVisible(false);
            }
        }
        @FXML
        private Button AddUserBtn;

        @FXML
        void HomeClick(MouseEvent event) throws IOException {
            Driver.changeScene("MainView.fxml");
        }
        @FXML
        void AddUser(MouseEvent event) throws IOException {
            Driver.changeScene("SignUp.fxml");
        }

        @FXML
        void LogOut(MouseEvent event) throws IOException {
            Driver.changeScene("login-view.fxml");
        }

        @FXML
        void CalculationClick(MouseEvent event) throws IOException {
            Driver.changeScene("Calculations.fxml");
        }


        @FXML
        void InventoryClick(MouseEvent event) throws IOException {
            InventoryController.sceneStatus=0;
            Driver.changeScene("Inventory.fxml");
        }

        @FXML
        void SellClick(MouseEvent event) throws IOException {
            InventoryController.sceneStatus=1;
            Driver.changeScene("Inventory.fxml");
        }
}
