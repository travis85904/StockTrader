package com.stocktrader.controllers;

import com.stocktrader.api.GetApiResponse;
import com.stocktrader.api.Quote;
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
    Quote quote;

    @FXML
    Text welcomeText;
    @FXML
    TextField getQuoteTextField;
    @FXML
    TextArea responseText;
    @FXML
    Button getQuoteButton;

    private Scene scene;
    public void setScene(Scene scene) { this.scene = scene; }

    public void setWelcomeText(String username){
        welcomeText.setText("Welcome "+username+"!");
    }
    public void lookupSymbol(){

    }
    @FXML
    private void getQuote() throws IOException, InterruptedException {
        String symbol = getQuoteTextField.getText();
        this.quote = new GetApiResponse().quote(symbol);
        responseText.setText(quote.toString());
    }

    @FXML
    private void quoteTextFieldListener(KeyEvent e) throws IOException, InterruptedException {
        if (e.getCode() == KeyCode.ENTER)
            getQuote();
    }

}
