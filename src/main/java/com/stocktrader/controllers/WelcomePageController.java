package com.stocktrader.controllers;

import com.stocktrader.api.GetApiResponse;
import com.stocktrader.api.Quote;
import com.stocktrader.api.RealTimePrice;
import com.stocktrader.api.TimeSeriesInterval;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

import java.io.IOException;

public class WelcomePageController {

    @FXML
    Text welcomeText;
    @FXML
    TextField getQuoteTextField;
    @FXML
    TextArea responseText;
    @FXML
    Button getQuoteButton;
    @FXML
    Button realTimePriceButton;
    @FXML
    TextField realTimePriceTextField;

    private Scene scene;

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setWelcomeText(String username) {
        welcomeText.setText("Welcome " + username + "!");
    }

    public void lookupSymbol() {

    }

    @FXML
    private void getQuote() throws IOException, InterruptedException {
        String symbol = getQuoteTextField.getText();
        Quote quote = new GetApiResponse().quote(symbol);
        responseText.setText(quote.toString());
    }

    @FXML
    private void getTimeSeries() {
        //TODO
        //String symbol
        //TimeSeriesInterval interval
        //
        //implement button, textfield, and dropdown menu to select interval
    }

    @FXML
    private void getRealTimePrice() throws IOException, InterruptedException {
        String symbol = realTimePriceTextField.getText();
        RealTimePrice realTimePrice = new GetApiResponse().realTimePrice(symbol);
        responseText.setText(symbol + "\n" + realTimePrice.toString());
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

}
