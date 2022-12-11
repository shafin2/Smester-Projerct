package com.example.hardwareshop2.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class LoginController {
    static File file=new File("src/main/Files/LoginDetails.txt");
    static private ArrayList<User> users=new ArrayList<>();

    static {
        //read user details
        try {
            FileReader fr=new FileReader(file);
            BufferedReader br=new BufferedReader(fr);
            String str="";
            while ((str=br.readLine())!=null){
                String[] data=str.split(" ");
                users.add(new User(data[0],data[1],data[2]));
            }

            br.close();
            fr.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @FXML
    private TextField password;

    @FXML
    private Label passwordMsg;

    @FXML
    private TextField username;

    @FXML
    private Label usernameMsg;

    @FXML
    void submitButton(MouseEvent event) {
        passwordMsg.setTextFill(Color.RED);
        usernameMsg.setTextFill(Color.RED);
        if(username.getText()=="" || password.getText()==""){
            if(username.getText()==""){
                usernameMsg.setText("Enter Username");
            }
            if(password.getText()==""){
                passwordMsg.setText("Enter Password");
            }
        }
        else {
            int userToken=0;
            int psdToken=0;
            for (User user:users) {
                if(user!=null){
                    if(username.getText().compareTo(user.getUserName())!=0){
                        userToken=1;
                    }
                    if(password.getText().compareTo(user.getPassword())!=0){
                        psdToken=1;
                    }
                    if(username.getText().compareTo(user.getUserName())==0 && password.getText().compareTo(user.getPassword())==0){
                        userToken=0;
                        psdToken=0;
                        break;
                    }
                }
            }
            if(userToken==1){
                username.setText("");
                usernameMsg.setText("Username is not correct");
            }
            if(psdToken==1){
                password.setText("");
                passwordMsg.setText("Password is not correct");
            }



        }


    }
    @FXML
    void disapear(MouseEvent event) {
        usernameMsg.setText("");
        passwordMsg.setText("");
    }

}