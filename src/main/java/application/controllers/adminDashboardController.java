package application.controllers;

import application.models.*;
import application.utils.UIutils;
import business.NotificationService;
import business.ReportService;
import business.UserService;
import data.imageUpload;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class adminDashboardController {

    @FXML
    public TableView<User> userTable;
    @FXML private TableColumn<User, String> userEmail;
    @FXML private TableColumn<User, String> userPassword;
    @FXML private TableColumn<User, String> userStatus;
    @FXML private TableColumn<User, String> userType;

    @FXML
    public Tab userTab;
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

    @FXML
    private FXCollections reportList;

    @FXML
    private Label itemNameLabelReport;

    @FXML
    private ImageView itemImageReport;


    @FXML
    private Button approveButton;

    @FXML
    private Button rejectButton;


    @FXML
    private ImageView itemImageViewReport;
    @FXML private Label itemNameViewReport;
    @FXML private ListView reportsListView;
    @FXML private Tab viewReportsTab;



    ArrayList<User> usersList;
    ArrayList<Report> reportsList;


    private NotificationService notificationService = new NotificationService();






    User currentAuthenticatedUser;
    ReportService reportService = new ReportService();
    UserService userService = new UserService();

    ArrayList<String> ImageURLs = new ArrayList<String>();

    ArrayList<Report> pendingReports = new ArrayList<Report>();

    @FXML
    private void initialize() {
        // Initialize the controller (if needed)

        userTab.setOnSelectionChanged(event -> {
            if (userTab.isSelected()) {

                ///table columns are
                //email, password, name, status
                userTable.getItems().clear();

                usersList = userService.getUsers();

                //settting up the columns
                userEmail.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
                userPassword.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
                userStatus.setCellValueFactory(new PropertyValueFactory<User, String>("status"));
                userType.setCellValueFactory(new PropertyValueFactory<User, String>("type"));

                userTable.getItems().setAll(usersList);

//





            }
        });

        foundItemContainer.setOnMouseClicked(this::handleItemSelection);

        foundItemContainer.setOnKeyPressed(keyEvent -> {
           //if up or down arrow is pressed
            if (keyEvent.getCode().equals(KeyCode.UP) || keyEvent.getCode().equals(KeyCode.DOWN)) {
                handleItemSelection(keyEvent);
            }
        });


        reportsListView.setOnMouseClicked(this::handleItemSelectionView);
        reportsListView.setOnKeyPressed(keyEvent -> {
            //if up or down arrow is pressed
            if (keyEvent.getCode().equals(KeyCode.UP) || keyEvent.getCode().equals(KeyCode.DOWN)) {
                handleItemSelectionView(keyEvent);
            }
        });


        foundItemTab.setOnSelectionChanged(event -> {
            if (foundItemTab.isSelected()) {

                foundItemContainer.getItems().clear();
                pendingReports.clear();



                // Logic for handling found items
                ArrayList<Report> reports = reportService.getPendingReports();

                pendingReports.addAll(reports);

                for (Report report : reports) {

                    if (report.getType().equals("found")) {
                        System.out.println(report);

                        FoundReport foundReport = (FoundReport) report;
                        foundItemContainer.getItems().add(foundReport.getFoundItem().getName());
                    } else if (report.getType().equals("lost")) {
                        System.out.println(report);

                        LostReport lostReport = (LostReport) report;
                        foundItemContainer.getItems().add(lostReport.getLostItem().getName());
                    }
                }





            }
        });


        viewReportsTab.setOnSelectionChanged(event -> {
            if (viewReportsTab.isSelected()) {

                reportsListView.getItems().clear();
                reportsList = reportService.getReports();

                for (Report report : reportsList) {
                    if (report.getType().equals("found")) {
                        FoundReport foundReport = (FoundReport) report;
                        reportsListView.getItems().add(foundReport.getFoundItem().getName());
                    } else if (report.getType().equals("lost")) {
                        LostReport lostReport = (LostReport) report;
                        reportsListView.getItems().add(lostReport.getLostItem().getName());
                    }
                }

            }
        });





    }

    private void handleItemSelectionView(Event event) {
        //clear the previous data

        //get the selected item
        int selectedIndex = reportsListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            return;
        }

        Report selectedReport = reportsList.get(selectedIndex);

        String itemName = "";
        String itemImage = "";
        String itemDescription = "";
        String itemLocation = "";

        if (selectedReport.getType().equals("found")) {
            FoundReport foundReport = (FoundReport) selectedReport;
            itemName = foundReport.getFoundItem().getName();
            itemImage = foundReport.getFoundItem().getImages().get(0);
            itemDescription = foundReport.getFoundItem().getDescription();
            itemLocation = foundReport.getFoundItem().getLocation();
        } else if (selectedReport.getType().equals("lost")) {
            LostReport lostReport = (LostReport) selectedReport;
            itemName = lostReport.getLostItem().getName();
            itemImage = lostReport.getLostItem().getImages().get(0);
            itemDescription = lostReport.getLostItem().getDescription();
            itemLocation = lostReport.getLostItem().getLocation();
        }

        itemNameViewReport.setText(itemName);
        itemImageViewReport.setImage(new javafx.scene.image.Image(itemImage));


    }


    public void handleItemSelection(Event event) {

        //clear the previous data



        //get the selected item
        int selectedIndex = foundItemContainer.getSelectionModel().getSelectedIndex();

        if (selectedIndex == -1) {
            return;
        }

        Report selectedReport = pendingReports.get(selectedIndex);

        String itemName = "";
        String itemImage = "";
        String itemDescription = "";
        String itemLocation = "";
        String itemDate = "";


        if (selectedReport.getType().equals("found")) {
            FoundReport foundReport = (FoundReport) selectedReport;
            itemName = foundReport.getFoundItem().getName();
            itemImage = foundReport.getFoundItem().getImages().get(0);
            itemDescription = foundReport.getFoundItem().getDescription();


        } else if (selectedReport.getType().equals("lost")) {
            LostReport lostReport = (LostReport) selectedReport;
            itemName = lostReport.getLostItem().getName();
            itemImage = lostReport.getLostItem().getImages().get(0);
            itemDescription = lostReport.getLostItem().getDescription();
            itemImage = lostReport.getLostItem().getImages().get(0);
        }



        itemNameLabelReport.setText(itemName);
        itemImageReport.setImage(new javafx.scene.image.Image(itemImage));
//itemDescriptionLabel.setText(itemDescription);

        //get the item id

    }



    public void initData(User user) {
        currentAuthenticatedUser = user;
        // Initialize the UI with the user data
        System.out.println("User logged in " + user.getEmail());
        usernameLabel.setText(user.getEmail());
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


    public void handleApproveButton(ActionEvent actionEvent) {

        int selectedIndex = foundItemContainer.getSelectionModel().getSelectedIndex();

        if (selectedIndex == -1) {
            return;
        }

        Report selectedReport = pendingReports.get(selectedIndex);

        System.out.println("Approved; "+ selectedReport);

        Report res = reportService.setReportStatus(selectedReport.getReportID(), "approved");

        if (res != null) {
            System.out.println("Report approved successfully");
            UIutils.showAlert(Alert.AlertType.INFORMATION, "Success", "Report Approved Successfully", "Report approved successfully");

        } else {
            System.out.println("Report approval failed");
            UIutils.showAlert(Alert.AlertType.ERROR, "Error", "Report Approval Failed", "Report approval failed");
        }

    }

    public void handleRejectButton(ActionEvent actionEvent) {

        int selectedIndex = foundItemContainer.getSelectionModel().getSelectedIndex();

        if (selectedIndex == -1) {
            return;
        }

        Report selectedReport = pendingReports.get(selectedIndex);

        System.out.println("Rejected; "+ selectedReport);

        Report res = reportService.setReportStatus(selectedReport.getReportID(), "rejected");

        if (res != null) {
            System.out.println("Report rejected successfully");
            UIutils.showAlert(Alert.AlertType.INFORMATION, "Success", "Report Rejected Successfully", "Report rejected successfully");

        } else {
            System.out.println("Report rejection failed");
            UIutils.showAlert(Alert.AlertType.ERROR, "Error", "Report Rejection Failed", "Report rejection failed");
        }

    }

    public void handleUserBan()
    {
        int selectedIndex = userTable.getSelectionModel().getSelectedIndex();

        if (selectedIndex == -1) {
            return;
        }

        User selectedUser = usersList.get(selectedIndex);

        System.out.println("Banned; "+ selectedUser);

        selectedUser.setStatus("banned");

        User res = userService.updateUser(selectedUser.getEmail(),selectedUser);


        if (res != null) {
            System.out.println("User banned successfully");
            UIutils.showAlert(Alert.AlertType.INFORMATION, "Success", "User Banned Successfully", "User banned successfully");

        } else {
            System.out.println("User ban failed");
            UIutils.showAlert(Alert.AlertType.ERROR, "Error", "User Ban Failed", "User ban failed");
        }


    }

    public void handleUserDelete(ActionEvent actionEvent) {

        int selectedIndex = userTable.getSelectionModel().getSelectedIndex();

        if (selectedIndex == -1) {
            return;
        }

        userService.deleteUser(usersList.get(selectedIndex).getEmail());

userTable.getItems().remove(selectedIndex);

UIutils.showAlert(Alert.AlertType.INFORMATION, "Success", "User Deleted Successfully", "User deleted successfully");


    }

    public void handleUserContact(ActionEvent actionEvent) throws UnsupportedEncodingException {
        int selectedIndex = userTable.getSelectionModel().getSelectedIndex();

        if (selectedIndex == -1) {
            return;
        }

        User selectedUser = usersList.get(selectedIndex);

        System.out.println("Contacted; "+ selectedUser);

        //open mailTo link
        String email = selectedUser.getEmail();
        String subject = "Regarding your account";
        String encodedS = java.net.URLEncoder.encode(subject,"UTF-8");

        String mailTo = "mailto:" + email + "?subject=" + encodedS;

        //open in default browser
        try {
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(mailTo));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void markAsSolvedHandler(ActionEvent actionEvent) {

        int selectedIndex = reportsListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            return;
        }

        Report selectedReport = reportsList.get(selectedIndex);

        Report res = reportService.setReportStatus(selectedReport.getReportID(), "completed");

        if (res != null) {
            System.out.println("Report marked as solved successfully");
            UIutils.showAlert(Alert.AlertType.INFORMATION, "Success", "Report Marked as Solved Successfully", "Report marked as solved successfully");
            notificationService.sendNotification(selectedReport.getReporter().getEmail(), "Your report has been marked as solved", "Your report has been marked as solved");

        } else {
            System.out.println("Report marking as solved failed");
            UIutils.showAlert(Alert.AlertType.ERROR, "Error", "Report Marking as Solved Failed", "Report marking as solved failed");
        }


    }

    // Other methods and code
}