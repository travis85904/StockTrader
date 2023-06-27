Thank you for checking out my project. First of all you should know this is a work in progress and not fully completed yet.

In order to get the project running you will need to make a few changes to the source.

1. (API)
In com.stocktrader.api.GetApiConnection.getApiKey() you will need to add your own api key which you can do by 
making a config.properties file and adding the api.key value.

You could also simply modify the method to just return your API key as a String directly from the method.
The api used here is Twelve Data on the RapidAPI website.

2. (MongoDB)
In com.stocktrader.db.DbConnection you will need to setup a MongoDB client locally from your PC.

Another option is to use the commented out source code to use a remote MongoDB server which would require
you to prove your MongoDB login String in the config.properties file.

Be sure to take a look at the provided Database and Collection names in the db.UserAuth class and make sure they match
up with where you would like to store your user details in your Database.

3. (config.properties)
If you choose to use a config.properties file to hold your API key and MongoDB login String then you will want to
place the file in a folder called "private." the file should look like this:

api.key=Your_API_key

mongoPass=Your_MongoDB_password

mongoConnectionString=Your_MongoDB_connection_string


