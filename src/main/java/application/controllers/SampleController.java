package application.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SampleController {

    @FXML
    private Label messageLabel;

    @FXML
    private void initialize() {
        messageLabel.setText("Hello, World!");
    }
}