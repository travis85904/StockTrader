package com.stocktrader.controllers;

import com.google.gson.Gson;
import com.stocktrader.StocksApplication;
import com.stocktrader.api.*;
import com.stocktrader.db.DbConnection;
import com.stocktrader.parser.MongoParse;
import com.stocktrader.parser.Stock;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.bson.Document;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

public class WelcomePageController {

    private Stage depositWindow;
    private String username;
    @FXML
    Text welcomeText;
    @FXML
    TextField getQuoteTextField;
    @FXML
    TextField realTimePriceTextField;
    @FXML
    TextField timeSeriesTextField;
    @FXML
    TextField symbolTextField;
    @FXML
    TextArea responseText;
    @FXML
    TextArea myPortfolioText;
    @FXML
    ComboBox<String> timeSeriesIntervalMenu;

    public void setup(String username){
        setUsername(username);
        setWelcomeText();
        displayPortfolio();
    }

    public void displayPortfolio() {
        Document doc = DbConnection.getDocument("username", username);
        MongoParse mongoParse = new Gson().fromJson(doc.toJson(), MongoParse.class);
        DecimalFormat df = new DecimalFormat("###,###,###.##");
        List<Stock> stockList = mongoParse.getStocks();
        myPortfolioText.setText(String.format("Balance: %s\n", df.format(doc.get("balance"))));
        for (int i = 1; i < stockList.size(); i++) {
            myPortfolioText.appendText(stockList.get(i).toString());
        }
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setWelcomeText() {
        welcomeText.setText("Welcome " + username + "!");
    }

    @FXML
    private void getQuote() throws IOException, InterruptedException {
        String symbol = getQuoteTextField.getText();
        Quote quote = new GetApiResponse().quote(symbol);
        responseText.setText(quote.toString());
    }

    @FXML
    private void getTimeSeries() {
        String symbol = timeSeriesTextField.getText();
        String interval = null;
        try {
            interval = timeSeriesIntervalMenu.getValue().toString();
            TimeSeries timeSeries = new GetApiResponse().timeSeries(symbol, interval);
            responseText.setText(timeSeries.toString());
        } catch (Exception e) {
            StocksApplication.showAlert("You must select an interval and enter a valid symbol to lookup a Time-Series");
            e.printStackTrace();
        }
        System.out.println(interval);
    }

    @FXML
    private void getSymbol() throws IOException, InterruptedException {
        String query = symbolTextField.getText();
        Symbol symbol = new GetApiResponse().symbol(query);
        responseText.setText(symbol.toString());
    }

    @FXML
    private void getRealTimePrice() throws IOException, InterruptedException {
        String symbol = realTimePriceTextField.getText();
        RealTimePrice realTimePrice = new GetApiResponse().realTimePrice(symbol);
        responseText.setText(symbol + "\n" + realTimePrice.toString());
    }

    @FXML
    private void depositButton() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("deposit-view.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        DepositViewController depositViewController = loader.getController();
        depositViewController.setup(username, this);
        depositWindow = new Stage();
        depositWindow.setScene(root.getScene());
        depositWindow.show();
    }

    @FXML
    private void buyStocksButton() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("buy-stocks-view.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        BuyStocksController buyStocksController = loader.getController();
        buyStocksController.setup(username, this);

        Stage buyStocksWindow = new Stage();
        buyStocksWindow.setScene(root.getScene());
        buyStocksWindow.show();
    }

    @FXML
    private void realTimePriceListener(KeyEvent e) throws IOException, InterruptedException {
        if (e.getCode() == KeyCode.ENTER)
            getRealTimePrice();
    }

    @FXML
    private void quoteTextFieldListener(KeyEvent e) throws IOException, InterruptedException {
        if (e.getCode() == KeyCode.ENTER)
            getQuote();
    }

    @FXML
    private void timeSeriesListener(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER)
            getTimeSeries();
    }

    @FXML
    private void getSymbolListener(KeyEvent e) throws IOException, InterruptedException {
        if (e.getCode() == KeyCode.ENTER)
            getSymbol();
    }

    void closeDepositWindow() {
        depositWindow.close();
    }

}
