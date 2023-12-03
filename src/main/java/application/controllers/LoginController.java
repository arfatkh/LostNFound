package application.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


import business.UserService;
import application.models.User;
import javafx.stage.Stage;

public class LoginController {

    UserService userService = new UserService();

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    @FXML
    private Button loginButton;

    @FXML
    private void login() {
        // Add login logic here
        String email = emailField.getText();
        String password = passwordField.getText();

        messageLabel.setText("");
        // TODO: Implement login authentication

//
//        email = "arfat@duck.com";
//        password = "arfat1";
//
//        email = "admin";
//        password = "admin";



        User curr_user = userService.authenticate(email, password);

          if (curr_user != null) {



                String userType = curr_user.getType();

                String userStatus = curr_user.getStatus();

                if(userStatus.equals("banned"))
                {
                    messageLabel.setText("You have been banned");
                    return;
                }


                if (userType.equals("admin")) {

                    try {

                        //load dashboard
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/adminDashboard.fxml"));
                        Stage stage = (Stage) loginButton.getScene().getWindow();
                        Scene scene = new Scene(loader.load());
                        stage.setScene(scene);
                        adminDashboardController controller = loader.<adminDashboardController>getController();
                        controller.initData(curr_user);
                        stage.show();

                    } catch (Exception e) {
                        System.out.println(e);
                    }


                    return;
                }







        else if (userType.equals("user")) {

            try
            {

                //load dashboard
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/userDashboard.fxml"));
                Stage stage = (Stage) loginButton.getScene().getWindow();
                Scene scene = new Scene(loader.load());
                stage.setScene(scene);
                userDashboardController controller = loader.<userDashboardController>getController();
                controller.initData(curr_user);
                stage.show();


//                messageLabel.setText("Login Successful");

            }
            catch(Exception e)
            {
                System.out.println(e);
            }



        }





    }
          else {
              messageLabel.setText("Invalid credentials");
          }
}

}