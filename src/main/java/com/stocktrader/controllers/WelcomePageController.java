package com.stocktrader.controllers;

import com.stocktrader.StocksApplication;
import com.stocktrader.api.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
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

    void displayPortfolio() {
        myPortfolioText.setText(StocksApplication.printBalance(username));
        myPortfolioText.appendText(StocksApplication.printPortfolio(username));
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
        String symbol = timeSeriesTextField.getText().toUpperCase();
        String interval;
        try {
            interval = timeSeriesIntervalMenu.getValue();
            List<Value> valueList = new ArrayList<>(new GetApiResponse().timeSeries(symbol, interval).values);
            final LineChart lineChart = buildChartForTimeSeries(valueList, symbol, interval);

            for (Value v: valueList) {
                responseText.appendText(String.format("%s : %s\n",v.open(),v.datetime()));
            }

            Scene scene = new Scene(lineChart, 800, 600);
            Stage chartStage = new Stage();
            chartStage.setScene(scene);
            chartStage.show();
        } catch (Exception e) {
            StocksApplication.showAlert("You must select an interval and enter a valid symbol to lookup a Time-Series");
            e.printStackTrace();
        }
    }

    private LineChart buildChartForTimeSeries(List<Value> valueList, String symbol, String interval) {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        XYChart.Series series = new XYChart.Series();
        double maxStockPrice = 0D;
        double minStockPrice = 10000D;
        xAxis.setLabel("Time");
        lineChart.setTitle(String.format("Time Series - %s - Interval: %s", symbol, interval));
        series.setName("Interval: " + interval);
        lineChart.getYAxis().setAutoRanging(false);

        for (int i = valueList.size() - 1; i >= 0; i--) {
            double stockPrice = Double.parseDouble(valueList.get(i).open());
            series.getData().add(new XYChart.Data(valueList.get(i).datetime(), stockPrice));

            if (stockPrice > maxStockPrice)
                maxStockPrice = stockPrice;
            if (stockPrice < minStockPrice)
                minStockPrice = stockPrice;
        }
        minStockPrice = Math.floor(minStockPrice);
        maxStockPrice = Math.ceil(maxStockPrice);
        yAxis.setLowerBound(minStockPrice);
        yAxis.setUpperBound(maxStockPrice);
        lineChart.getData().add(series);
        return lineChart;
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

    void setup(String username) {
        setUsername(username);
        setWelcomeText();
        displayPortfolio();
    }
}
