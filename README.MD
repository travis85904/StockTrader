Thank you for checking out my project. First of all you should know this is a work in progress and not full completed yet.

In order to get the project runnin you will need to make a few changes to the source.

1.
In com.stocktrader.api.GetApiConnection.getApiKey() you will need to add your own api key which you can do by making a config.properties file and adding the api.key value.

You could also simply modify the method to just return your API key as a String directly from the method.
The api used here is Twelve Data on the RapidAPI website.

2.
In com.stocktrader.db.DbConnection you will need to setup a MongoDB client locally from your PC.
Another option is to use the commented out source code to use a remote MongoDB server which would require
you to prove your MongoDB login String in the config.propertied file.


