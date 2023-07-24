package com.stocktrader.db;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class DbConnection {
    //
    //You will need to set up your own MongoDB server in order for this function to work
    //
    //You can create a local server on your PC or use the commented out code to
    //use a remote MongoDB server
    //Just be sure to include your mongo login string in your config file
    static MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017/");
/*    static MongoClient mongoClient;
      static Properties properties = new Properties();
      static {
          try {
              FileInputStream fileInputStream = new FileInputStream("private/config.properties");
              properties.load(fileInputStream);

          } catch (Exception e) {
              e.printStackTrace();
          }
          String connectionString = String.format(properties.getProperty("mongoConnectionString"));
          mongoClient = MongoClients.create(connectionString);
      }*/

   public static MongoClient getMongoClient() {
        return mongoClient;
    }
}
