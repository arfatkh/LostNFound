package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import data.connection;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        connection connection = new connection();

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Signup.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/fxml/userDashboard.fxml"));


        //add stylesheets
//        root.getStylesheets().add(getClass().getResource("/fxml/userDashboard.css").toExternalForm());

        primaryStage.setScene(new Scene(root));
        primaryStage.show();







    }

    public static void main(String[] args) {
        launch(args);
    }
}