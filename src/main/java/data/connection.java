package data;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class connection {
    private static MongoClient mongoClient;
    private static MongoDatabase database;


    public connection() {



        try{
            MongoConnect();

            //test connection

            if (!testConnection()) {
                showErrorDialog("Connection Error", "Failed to connect to MongoDB", "Please check your internet connection and try again.");
            }

            //system.exit(0);

        }
        catch (Exception e) {
//            System.exit(0);
        }
    }

    private static void MongoConnect() {
        //try this
        try {
            mongoClient = MongoClients.create("mongodb+srv://techforallfeilds:8ZwctHghz5MrYCOJ@lostnfound.wob0q2i.mongodb.net/?retryWrites=true&w=majority");
            MongoDatabase database = mongoClient.getDatabase("lostNFound");

        }
        catch (Exception e) {
            throw new RuntimeException("Failed to connect to MongoDB.");

        }

    }


    public static MongoClient getMongoClient() {
        return mongoClient;
    }

    public static MongoDatabase getDatabase() {
        return database;
    }
    public static void setMongoClient(MongoClient mongoClient) {
        connection.mongoClient = mongoClient;
    }

    public static boolean testConnection() {

        //test connection status
        try {
            mongoClient.listDatabaseNames().first();
        }
        catch (Exception e) {
            return false;
        }

        return true;
    }


    private static void showErrorDialog(String title, String header, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}