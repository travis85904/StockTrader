package com.stocktrader;

import com.stocktrader.db.UserAuth;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    @FXML
    protected void onLoginButtonClick(){
        try {
            String userName = usernameField.getText();
            String password = passwordField.getText();
            Boolean isValidLoginInfo = new UserAuth().authenticate(userName, password);
        }catch (NullPointerException e){
            StocksApplication.showAlert("User not found");
        }
    }
}
