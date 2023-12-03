package application.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;

public class itemCardController {
    @FXML
    private VBox itemCardTemplate;

    @FXML
    private Label itemID;

    @FXML
    private Label itemName;

    @FXML
    private Label itemDescription;

    @FXML
    private ImageView itemImage;

    private String itemImageLink;
    private String itemIDString;

    private Date dateReported;

    private String locationReported;


    @FXML
    private Button claimButton;

    public VBox getItemCardTemplate() {
        return itemCardTemplate;
    }

    public void setItemID(String id) {
        itemID.setText("Item ID: " + id);
    }

    public void setItemName(String name) {
        itemName.setText("Item Name: " + name);
    }

    public void setItemDescription(String description) {
        itemDescription.setText("Description: " + description);
    }

    public void setItemImage(String imagePath) {
        itemImage.setImage(new javafx.scene.image.Image(imagePath));
    }

    public void setItemImageLink(String imagePath) {
        itemImageLink = imagePath;
    }

    public void setItemIDString(String id) {
        itemIDString = id;
    }

    public void setDateReported(Date date) {
        dateReported = date;
    }

    public void setLocationReported(String location) {
        locationReported = location;
    }


    public void setItemData(String id, String name, String description, String imagePath, Date dateReported, String locationReported) {
        setItemID(id);
        setItemName(name);
        setItemDescription(description);
        setItemImage(imagePath);
        setItemImageLink(imagePath);
        setItemIDString(id);
        setDateReported(dateReported);
        setLocationReported(locationReported);

    }






    public void handleClaimButton() {
        System.out.println("Claim button clicked");
        System.out.println("Item ID: " + itemIDString);




    }

    public void handleMoreInfoButton(){
        System.out.println("More Info button clicked");
        System.out.println("Item ID: " + itemIDString);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PopupContent.fxml"));
            VBox popupContent = loader.load();

            PopupContentController controller = loader.getController();
            controller.setItemData(itemName.getText().replace("Item Name: ", ""), itemDescription.getText().replace("Description: ", ""), itemImageLink, dateReported, locationReported);

            Scene popupScene = new Scene(popupContent, 320, 320);
            Stage popupStage = new Stage();
            popupStage.setScene(popupScene);
            popupStage.setTitle("More Info");

            popupStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getItemIDString() {
        return itemIDString;
    }


    // You can add more methods here for additional functionalities
}
