module com.example.demo6 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mongodb.driver.core;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.bson;
    requires java.sql;
    requires cloudinary.http44;
    requires cloudinary.core;
    requires java.net.http;
    requires java.desktop;

    opens application.controllers to javafx.fxml;
    exports application;
    opens application.models to javafx.base, javafx.fxml;


    // ...
}