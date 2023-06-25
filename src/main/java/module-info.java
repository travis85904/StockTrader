module com.stocks.stocktrader {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.google.gson;
    requires org.mongodb.driver.core;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.bson;

    opens com.stocktrader to javafx.fxml;
    opens com.stocktrader.api to javafx.fxml;
    opens com.stocktrader.db to javafx.fxml;
    exports com.stocktrader;
    exports com.stocktrader.api;
    exports com.stocktrader.db;

}