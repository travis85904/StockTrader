package com.stocktrader.controllers;

import com.stocktrader.StocksApplication;
import com.stocktrader.api.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomePageController {

    private static Stage depositWindow;
    private String userName;
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
    ComboBox<String> timeSeriesIntervalMenu;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setWelcomeText(String username) {
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
        depositViewController.setUserName(userName);
        depositWindow = new Stage();
        depositWindow.setScene(root.getScene());
        depositWindow.show();
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

    static void closeDepositWindow() {
        depositWindow.close();
    }

}
