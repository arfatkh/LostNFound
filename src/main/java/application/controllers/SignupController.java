package application.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



import business.UserService;
import application.models.User;

import java.io.IOException;

public class SignupController {


    @FXML
    private Button loginButton;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneField;

    @FXML
    private PasswordField passwordField;

    //TO display the error message
    @FXML
    private Label errorField;

    UserService userService;

    @FXML
    private void initialize() {
        // Initialize the controller (if needed)
        this.userService = new UserService();

        // You can add event handlers or other initialization logic here
        loginButton.setOnAction(event -> handleLogin());
        signUpButton.setOnAction(event -> handleSignUp());
    }

    private void handleLogin() {
        Stage stage = (Stage) loginButton.getScene().getWindow();

        try {
            // Load the new scene from FXML file
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));

            // Create a new scene with the loaded root node
            Scene scene = new Scene(root);

            // Set the new scene on the stage
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleSignUp() {
        // Add logic to handle sign up
        String name = nameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String password = passwordField.getText();


        String error = "";
        errorField.setText("");

        if (name.isEmpty()) {
            error += "Name is required.\n";
        }
        if (email.isEmpty()) {
            error += "Email is required.\n";
        }
        else if (!isValidEmail(email)) {
            error += "Email is invalid.\n";
        }
        if (phone.isEmpty()) {
            error += "Phone is required.\n";
        }
        else if (!isValidPhone(phone)) {
            error += "Phone is invalid.\n";
        }
        if (password.isEmpty()) {
            error += "Password is required.\n";
        }
        if (!error.isEmpty()) {
            errorField.setText(error);
            return;
        }


        userService.register(name, email, phone, password);


        // Perform sign up process or any other required actions
        System.out.println("Sign Up: Name=" + name + ", Email=" + email + ", Phone=" + phone + ", Password=" + password);
    }


    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
    }

    private boolean isValidPhone(String phoneNumber) {
        String phoneRegex = "^(03\\d{2})(\\d{7})$";
        return phoneNumber.matches(phoneRegex);
    }

}