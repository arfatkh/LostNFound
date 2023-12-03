package application.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Date;

public class PopupContentController {

    @FXML
    private Label itemNameLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private ImageView itemImageView;

    @FXML
    private Button claimButton;

    @FXML
    private Label dateFoundLabel;

    @FXML
    private Label locationFoundLabel;


    // Method to set item data dynamically
    public void setItemData(String itemName, String description, String imagePath, Date dateFound, String locationFound) {
        itemNameLabel.setText("Item Name: " + itemName);
        descriptionLabel.setText("Description: " + description);
        itemImageView.setImage(new Image(imagePath));
        if (dateFound != null) {
            dateFoundLabel.setText("Date Found: " + dateFound.toString());

        }
        else {
            dateFoundLabel.setText("Date Found: N/A");
        }
        locationFoundLabel.setText("Location Found: " + locationFound);


        //print the date found
        //print the location found

        System.out.println("Item Name: " + itemName);
        System.out.println("Description: " + description);
        System.out.println("Image Path: " + imagePath);
        System.out.println("Date Found: " + dateFound);
        System.out.println("Location Found: " + locationFound);


        // You can also handle other elements or actions related to the item here
    }


    // Method to handle actions related to claiming the item, if needed
    @FXML
    private void claimItem() {
        // Implement claim item functionality here
    }
}
