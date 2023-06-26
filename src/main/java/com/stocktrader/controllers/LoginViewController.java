package com.stocktrader.controllers;

import com.stocktrader.StocksApplication;
import com.stocktrader.db.UserAuth;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginViewController {
    private static Stage registrationWindow;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;

    @FXML
    protected void onLoginButtonClick() {
        boolean isValidLoginInfo;
        try {
            String userName = usernameField.getText();
            String password = passwordField.getText();
            isValidLoginInfo = new UserAuth().authenticate(userName, password);
            if (isValidLoginInfo) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("welcome-page.fxml"));
                Parent root = loader.load();
                WelcomePageController welcomePageController = loader.getController();
                welcomePageController.setWelcomeText(userName);
                Scene scene = new Scene(root);
                StocksApplication.getStage().setScene(root.getScene());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void loginFieldKeyListener(KeyEvent e) throws IOException, InterruptedException {
        if (e.getCode() == KeyCode.ENTER)
            onLoginButtonClick();
    }

    @FXML
    private void register() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("registration-view.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        registrationWindow = new Stage();
        registrationWindow.setScene(root.getScene());
        registrationWindow.show();
       // registrationWindow.setAlwaysOnTop(true);
    }

//    public static Stage getRegistrationStage(){
//        return registrationWindow;
//    }
    public static void closeRegistrationWindow(){
        registrationWindow.close();
    }
}
