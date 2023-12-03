package application.models;
import application.models.User;
import org.bson.Document;
//list of imports
import java.util.ArrayList;

public class Item {

    //itemID will be the document ID in the database
    private String itemID;

    private String name;
    private String description;
    private String location;

    //images will be stored as a string of the path to the image
    private ArrayList<String> images;

    private String type;

    private User owner;




    //constructor

    public Item() {
    }

    public Item(String name, String location, String description, ArrayList<String> images, String type,User _owner) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.images = images;
        this.type = type;
        if (_owner != null)
            this.owner = new User(_owner.toDocument());
        else this.owner = null;
    }

    //constructor for the item from the databased document
    public Item(Document document)
    {
        this.name = document.getString("name");
        this.location = document.getString("location");
        this.description = document.getString("description");
        this.images = (ArrayList<String>) document.get("images");
        this.type = document.getString("type");
        this.owner = new User(document.get("owner",Document.class));







    }

    public Item(Item item) {
        this.name = item.getName();
        this.location = item.getLocation();
        this.description = item.getDescription();
        this.images = item.getImages();
        this.type = item.getType();
    }




    // Getters and setters
    public String getType() {
        return type;
    }
    public String getName() {
        return name;
    }
    public String getLocation() {
        return location;
    }
    public String getDescription() {
        return description;
    }
    public ArrayList<String> getImages() {
        if (images == null) {
            images = new ArrayList<>();
        }
        //placeholder
        images.add("https://via.placeholder.com/300");

        return images;
    }
    public String getItemID() {
        return itemID;
    }


    //setters
    public void setName(String name) {
        this.name = name;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setImages(ArrayList<String> images) {
        this.images = images;
    }
    public void setItemID(String itemID) {
        this.itemID = itemID;
    }


    //to convert the item to a document
    public Document toDocument() {
        Document document = new Document();
        document.append("name", name);
        document.append("location", location);
        document.append("description", description);
        document.append("images", images);
        document.append("type", type);
        if (owner != null)
            document.append("owner",owner.toDocument());
        else document.append("owner",null);


        return document;
    }


    //print the item
    public void printItem() {
        System.out.println("Item ID: " + itemID);
        System.out.println("Name: " + name);
        System.out.println("Location: " + location);
        System.out.println("Description: " + description);
        System.out.println("Images: " + images);
        System.out.println("Type: " + type);

    }


    public String getImageURL() {
        return images.get(0);
    }
}
