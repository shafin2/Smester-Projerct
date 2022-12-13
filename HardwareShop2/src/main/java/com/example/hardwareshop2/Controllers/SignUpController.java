package com.example.hardwareshop2.Controllers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class SignUpController {
    static File file=new File("src/main/Files/LoginDetails.txt");
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label cnfrmpswrdError;

    @FXML
    private PasswordField confirmpasswordField;

    @FXML
    private DatePicker dateField;

    @FXML
    private Label nameError;

    @FXML
    private TextField nameField;

    @FXML
    private Label passwordError;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitButton;

    @FXML
    private Label usernameError;

    @FXML
    private TextField usernameField;


    @FXML
    private RadioButton Empradio;

    @FXML
    private RadioButton adminradio;
    @FXML
    private Label userTypeError;

    @FXML
    void clearinvalidMsg(MouseEvent event) {
        nameError.setOpacity(0);
        passwordError.setOpacity(0);
        cnfrmpswrdError.setOpacity(0);
        usernameError.setOpacity(0);
    }
    @FXML
    void submitFunction(MouseEvent event) {
//        System.out.println(dateField.getValue());
        nameError.setTextFill(Color.RED);
        passwordError.setTextFill(Color.RED);
        cnfrmpswrdError.setTextFill(Color.RED);
        usernameError.setTextFill(Color.RED);
        userTypeError.setTextFill(Color.RED);
        dateField.setValue(LocalDate.now());
        if(Empradio.isSelected() && adminradio.isSelected()){
            userTypeError.setOpacity(1);
        }
        else{
            userTypeError.setOpacity(0);
            String user=Empradio.isSelected()?"Emplpoyee":"Admin";
            if(nameField.getText()!="" && usernameField.getText()!="" && passwordField.getText()!=""  && confirmpasswordField.getText()!="" ){
                if(passwordField.getText().compareTo(confirmpasswordField.getText())==0){
                    try {
                        FileWriter fw=new FileWriter(file,true);
                        BufferedWriter bw=new BufferedWriter(fw);
                        String str=user+" " +usernameField.getText()+" "+passwordField.getText()+" "+nameField.getText()+" "+dateField.getValue()+"\n";
                        bw.write(str);
                        bw.close();
                        fw.close();
                    }
                    catch (Exception e){

                    }
                }
                else{
                    passwordError.setOpacity(1);
                    cnfrmpswrdError.setOpacity(1);
                }
            }
            else {
                if (nameField.getText()==""){
                    nameError.setOpacity(1);
                }
                if (usernameField.getText()==""){
                    usernameError.setOpacity(1);
                }
                if (passwordField.getText()=="" || confirmpasswordField.getText()==""){
                    passwordError.setOpacity(1);
                    cnfrmpswrdError.setOpacity(1);
                }
            }
        }

    }





}
