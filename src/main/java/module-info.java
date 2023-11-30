module com.example.demo6 {
    requires javafx.controls;
    requires javafx.fxml;

    opens application.controllers to javafx.fxml;
    exports application;

    // ...
}