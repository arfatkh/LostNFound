package application.controllers;

import application.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


import application.utils.UIutils;

import business.FeedbackService;
public class FeedbackFormController {

    User currentUser;
    FeedbackService FeedbackService = new FeedbackService();

    @FXML
    private TextArea feedbackText;

    @FXML
    private ChoiceBox feedbackType;


    public void submitFeedback() {
        // TODO implement here
    }

    public void  handleSubmit() {

        System.out.println("Feedback Submitted");
        System.out.println(feedbackText.getText());
        System.out.println(feedbackType.getValue());

        if (FeedbackService.submitFeedback(feedbackType.getValue().toString(), feedbackText.getText(), currentUser.getEmail()) != null) {
            System.out.println("Feedback submitted");
            UIutils.showAlert(javafx.scene.control.Alert.AlertType.INFORMATION, "Success", "Feedback Submitted", "Feedback submitted successfully");
        } else {
            UIutils.showAlert(javafx.scene.control.Alert.AlertType.ERROR, "Error", "Feedback Submission Failed", "Feedback submission failed");
            System.out.println("Feedback not submitted");
        }


    }

    public void initData(User user) {
        this.currentUser = user;
    }
}
