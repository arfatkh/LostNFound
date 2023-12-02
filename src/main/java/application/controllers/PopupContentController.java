package application.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PopupContentController {

    @FXML
    private Label itemNameLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private ImageView itemImageView;

    @FXML
    private Button claimButton;

    // Method to set item data dynamically
    public void setItemData(String itemName, String description, String imagePath) {
        itemNameLabel.setText("Item Name: " + itemName);
        descriptionLabel.setText("Description: " + description);
        itemImageView.setImage(new Image(imagePath));
        // You can also handle other elements or actions related to the item here
    }

    // Method to handle actions related to claiming the item, if needed
    @FXML
    private void claimItem() {
        // Implement claim item functionality here
    }
}
