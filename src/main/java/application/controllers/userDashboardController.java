package application.controllers;

import application.models.LostItem;
import application.models.LostReport;
import application.models.Report;
import application.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import application.utils.UIutils;
import data.imageUpload;

//for Alert
import javafx.scene.control.Alert;


import business.ReportService;

public class userDashboardController {

    @FXML
    private Button submitReportButton;

    @FXML
    private Button giveFeedbackButton;

    @FXML
    private ListView<String> notificationsListView;

    @FXML
    public Label usernameLabel;

    //submit report fields
    @FXML
    public Label lostItemName;

    @FXML
    public Label lostItemDesc;

    @FXML
    public Label lostItemLocation;

    @FXML
    public DatePicker lostItemDate;

    @FXML
    public ListView fileList;




    User currentAuthenticatedUser;
    ReportService reportService = new ReportService();

    ArrayList<String> ImageURLs = new ArrayList<String>();
    @FXML
    private void initialize() {
        // Initialize the controller (if needed)

        // Attach event handlers to buttons
//        submitReportButton.setOnAction(event -> handleSubmitReport());
//        giveFeedbackButton.setOnAction(event -> handleGiveFeedback());
//        claimItem1Button.setOnAction(event -> handleClaimItem("Item 1"));
//        claimItem2Button.setOnAction(event -> handleClaimItem("Item 2"));
//        claimItem3Button.setOnAction(event -> handleClaimItem("Item 3"));
    }

    public void initData(User user) {
        currentAuthenticatedUser = user;
        // Initialize the UI with the user data
        System.out.println("User logged in " + user.getEmail());
        usernameLabel.setText(user.getEmail());
    }

    private void handleSubmitReport() {
        // Logic for handling report submission
    }

    private void handleGiveFeedback() {
        // Logic for handling feedback submission
    }

    private void handleClaimItem(String item) {
        // Logic for handling item claim
    }

    @FXML
    private void showPopup(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        HBox parentHBox = (HBox) clickedButton.getParent().getParent(); // Use HBox instead of VBox

        Label itemIDLabel = (Label) parentHBox.lookup("#itemID");
        String itemID = itemIDLabel.getText().replace("Item ID: ", "");

//        ListItem selectedItem = getItemById(itemID);

        if (true)// != null) {
        {try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PopupContent.fxml"));
                VBox popupContent = loader.load();

                PopupContentController controller = loader.getController();
                controller.setItemData("Item Name", "Item Description", "https://static-cse.canva.com/blob/997918/tools_feature_svg-to-png_hero_mobile2x.jpg");

                Scene popupScene = new Scene(popupContent, 320, 320);
                Stage popupStage = new Stage();
                popupStage.setScene(popupScene);
                popupStage.setTitle("More Info");
                popupStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void openFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image File");
        // Set extension filters if you want to restrict to specific image types
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            // Handle the selected image file here
            //upload to
            try {
                String url = imageUpload.uploadImage(selectedFile);
                ImageURLs.add(url);
                System.out.println(url);
                fileList.getItems().add(selectedFile.getName());
            } catch (Exception e) {
                e.printStackTrace();
            }







        }
    }

    public void submitReport(ActionEvent actionEvent) {

        String name = lostItemName.getText();
        String description = lostItemDesc.getText();
        String location = "location";
        //get date from date picker
        LocalDate date = lostItemDate.getValue();
        //convert to date
        Date date1 = java.sql.Date.valueOf(date);
        String type = "lost";

        LostItem lostItem = new LostItem(name, location,description, ImageURLs, type, date1);

        LostReport lostReport = new LostReport(currentAuthenticatedUser, lostItem);


        Report res = reportService.submitReport(lostReport);



        if (res != null) {
            System.out.println("Report submitted successfully");
            UIutils.showAlert(Alert.AlertType.INFORMATION, "Success", "Report Submission Successful", "Report submitted successfully");

        } else {
            System.out.println("Report submission failed");
            UIutils.showAlert(Alert.AlertType.ERROR, "Error", "Report Submission Failed", "Report submission failed");
        }





    }


    // Other methods and code
}