package com.example.hardwareshop2.Controllers;

import com.example.hardwareshop2.Driver;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class MainScreenController {
        static String news="";
        @FXML
        void initialize() {
            if (User.userStatus.compareTo("Admin")==0){
                AddUserBtn.setVisible(true);
            }
            else {
                AddUserBtn.setVisible(false);
            }
            newsTextAreaa.setText(news);
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
        @FXML
        private TextArea newsTextAreaa;

        @FXML
        private Button reloadBtn;
        @FXML
        void reloadBtn(MouseEvent event) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless", "--window-size=1920,1200","--ignore-certificate-errors");
            WebDriver driver=new ChromeDriver(options);

            //navigate to website
            driver.navigate().to("https://www.thenews.com.pk/latest-stories");

            //pause to load the website properly
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //taking all elements of news
            List<WebElement> myList=driver.findElements(By.className("latest-right"));
            List<String> all_elements_text=new ArrayList<>();
            //taking text out of web elements and store in list

            for(int i=0; i<myList.size(); i++){
                news+=i+1 + ")" +"\n" + myList.get(i).getText() + "\n";
//                all_elements_text.add(myList.get(i).getText());
            }
            newsTextAreaa.setText(news);
            driver.close();
        }
}
