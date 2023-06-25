package com.stocktrader;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class StocksApplication extends Application {

    private static Stage mainStage;
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("login-view.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Baskeldo Stonks Trader");
        stage.setScene(scene);
        stage.show();
        mainStage = stage;
    }

    public static void showAlert(String alertText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(alertText);
        alert.show();
    }

    public static Stage getStage(){
        return mainStage;
    }

}


