package business;

import application.models.User;
import javafx.scene.control.Alert;


import data.repositories.UsersRepository;
import application.utils.UIutils;

import java.util.ArrayList;


public class UserService {

    static UsersRepository usersRepository = new UsersRepository();




    public User authenticate(String email, String password) {


        return usersRepository.authenticate(email, password);

    }

    public void register(String name, String email, String phone, String password) {
        // TODO: Implement registration logic

        // Example usage
        User user = new User(name, password, phone, email);
        // Save the user to the database or any other storage
        // ...



        User curr_user = usersRepository.register(user);


        if (curr_user != null) {

            UIutils.showAlert(Alert.AlertType.INFORMATION, "Success", "User Registration Successful", "User registered successfully");
        } else {
            UIutils.showAlert(Alert.AlertType.ERROR, "Error", "User Registration Failed", "User registration failed");
        }






    }

    public ArrayList<User> getUsers() {
        return usersRepository.getUsers();
    }

    public User updateUser(String email,User user) {
        return usersRepository.updateUser(email,user);
    }


    public void deleteUser(String email) {
        usersRepository.deleteUser(email);
    }
}