package application.controllers;

import application.models.*;
import application.utils.UIutils;
import business.ReportService;
import data.imageUpload;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class adminDashboardController {

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
    public TextField lostItemName;

    @FXML
    public TextArea lostItemDesc;

    @FXML
    public Label lostItemLocation;

    @FXML
    public DatePicker lostItemDate;

    @FXML
    public ListView fileList;


    @FXML
    private ComboBox reportType;

    //for found items list
    @FXML
    private ListView foundItemContainer;

    @FXML
    private Tab foundItemTab;





    User currentAuthenticatedUser;
    ReportService reportService = new ReportService();

    ArrayList<String> ImageURLs = new ArrayList<String>();
    @FXML
    private void initialize() {
        // Initialize the controller (if needed)

        foundItemTab.setOnSelectionChanged(event -> {
            if (foundItemTab.isSelected()) {

//                foundItemContainer.getChildren().clear();

                // Logic for handling found items
                ArrayList<FoundReport> reports = reportService.getFoundReports();

                for (FoundReport report : reports) {
                    System.out.println(report);
                    // Create a new item card
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ItemCard.fxml"));
                        VBox itemCard = loader.load();

                        itemCardController controller = loader.getController();

                        controller.setItemData(report.getReportID(), report.getFoundItem().getName(), report.getFoundItem().getDescription(), report.getFoundItem().getImages().get(0), report.getFoundItem().getDateFound(), report.getFoundItem().getLocation());


//                        foundItemContainer.getChildren().add(itemCard);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }



            }
        });

//


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

//    @FXML
//    private void showPopup(ActionEvent event) {
//        Button clickedButton = (Button) event.getSource();
//        HBox parentHBox = (HBox) clickedButton.getParent().getParent(); // Use HBox instead of VBox
//
//        Label itemIDLabel = (Label) parentHBox.lookup("#itemID");
//        String itemID = itemIDLabel.getText().replace("Item ID: ", "");
//
//        // Get the item data
//
////        ListItem selectedItem = getItemById(itemID);
//
//        if (true)// != null) {
//        {try {
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PopupContent.fxml"));
//                VBox popupContent = loader.load();
//
//                PopupContentController controller = loader.getController();
//                controller.setItemData();
//
//                Scene popupScene = new Scene(popupContent, 320, 320);
//                Stage popupStage = new Stage();
//                popupStage.setScene(popupScene);
//                popupStage.setTitle("More Info");
//                popupStage.show();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

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
        String type = reportType.getValue().toString();

        if (name.isEmpty() || description.isEmpty() || location.isEmpty() || date == null || ImageURLs.isEmpty()) {
            UIutils.showAlert(Alert.AlertType.ERROR, "Error", "Report Submission Failed", "Please fill all the fields");
            return;
        }

        if (date1.after(new Date())) {
            UIutils.showAlert(Alert.AlertType.ERROR, "Error", "Report Submission Failed", "Date cannot be in the future");
            return;
        }



        Report report = null;

        if (type.equals("found")) {
            report = new FoundReport(currentAuthenticatedUser,  new FoundItem(name, location, description, ImageURLs, date1,null));
        } else if (type.equals("lost")) {
            report = new LostReport(currentAuthenticatedUser,  new LostItem(name, location,description, ImageURLs, date1,null));
        }



        Report res = reportService.submitReport(report);



        if (res != null) {
            System.out.println("Report submitted successfully");
            UIutils.showAlert(Alert.AlertType.INFORMATION, "Success", "Report Submission Successful", "Report submitted successfully");

        } else {
            System.out.println("Report submission failed");
            UIutils.showAlert(Alert.AlertType.ERROR, "Error", "Report Submission Failed", "Report submission failed");
        }







    }


    public void handleFeedbackButton(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FeedbackForm.fxml"));
            VBox feedbackForm = loader.load();

            FeedbackFormController controller = loader.getController();
            controller.initData(currentAuthenticatedUser);

            Scene feedbackScene = new Scene(feedbackForm, 320, 320);
            Stage feedbackStage = new Stage();
            feedbackStage.setScene(feedbackScene);
            feedbackStage.setTitle("Feedback Form");
            feedbackStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//    public void handleViewFoundItems() {
//        // Logic for handling found items
//        System.out.println("Loading found items");
//
//        ArrayList<Report> reports = reportService.getReportsByType("lost");
//
//        for (Report report : reports) {
//            System.out.println(report);
//            // Create a new item card
//            try {
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ItemCard.fxml"));
//                HBox itemCard = loader.load();
//
//                ItemCardController controller = loader.getController();
//                controller.setItemData(report);
//
//                foundItemContainer.getChildren().add(itemCard);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    // Other methods and code
}