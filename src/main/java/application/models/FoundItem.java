package application.models;

import application.models.User;
//date
import java.util.ArrayList;
import java.util.Date;
import org.bson.Document;

public class FoundItem extends Item {

    //date found
    private Date dateFound;
    private User finder;
    private User owner;

    private String foundLocation;

    //constructor
    public FoundItem(String name, String location, String description, Date dateFound, User finder, String foundLocation, ArrayList<String> images) {
        super(name, location, description, images, "found");

        this.dateFound = dateFound;
        this.finder = finder;
        this.foundLocation = foundLocation;
    }

    //constructor for the item from the databased document
    public FoundItem(Document document)
    {
        super(document);
        this.dateFound = document.getDate("dateFound");
        this.finder = new User((Document) document.get("finder"));
        this.foundLocation = document.getString("foundLocation");
        this.owner = new User((Document) document.get("owner"));


    }


    // Getters and setters
    public Date getDateFound() {
        return dateFound;
    }
    public User getFinder() {
        return finder;
    }
    public String getFoundLocation() {
        return foundLocation;
    }
    public User getOwner() {
        return owner;
    }

    //to convert the item to a document
    public Document toDocument() {
        Document document = super.toDocument();
        document.append("dateFound", dateFound);
        document.append("finder", finder.toDocument());
        document.append("foundLocation", foundLocation);
        document.append("owner", owner.toDocument());
        return document;
    }




}
