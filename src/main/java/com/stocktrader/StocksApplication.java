package com.stocktrader;

import com.google.gson.Gson;
import com.stocktrader.db.DbConnection;
import com.stocktrader.parser.MongoParse;
import com.stocktrader.parser.Stock;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.bson.Document;
import java.text.DecimalFormat;
import java.util.List;

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
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(alertText);
        alert.show();
    }

    public static Stage getStage(){
        return mainStage;
    }

    public static String printPortfolio(String username) {
        Document doc = DbConnection.getDocument("username", username);
        MongoParse mongoParse = new Gson().fromJson(doc.toJson(), MongoParse.class);
        List<Stock> stockList = mongoParse.getStocks();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < stockList.size(); i++) {
            sb.append(stockList.get(i).toString());
        }
        return sb.toString();
    }

    public static String printBalance(String username){
        Document doc = DbConnection.getDocument("username", username);
        DecimalFormat df = new DecimalFormat("###,###,###.##");
        return String.format("Balance: %s\n", df.format(doc.get("balance")));
    }

}


