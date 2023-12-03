package application.models;

import application.models.User;
//date
import java.util.ArrayList;
import java.util.Date;
import org.bson.Document;

public class FoundItem extends Item {

    //date found
    private Date dateFound;



    //constructor
    public FoundItem(String name, String location, String description, ArrayList<String> images, Date dateFound,User owner) {
        super(name, location, description, images, "found",owner);
        this.dateFound = dateFound;
    }

    //public constructor


    public FoundItem(Item item, Date dateFound) {
        super(item);
        this.dateFound = dateFound;
    }

    public FoundItem() {
        super();
    }

    //constructor for the item from the databased document
    public FoundItem(Document document)
    {
        super(document);
        this.dateFound = document.getDate("dateFound");

    }


    // Getters and setters
    public Date getDateFound() {
        return dateFound;
    }

    //to convert the item to a document
    public Document toDocument() {
        Document document = super.toDocument();
        document.append("dateFound", dateFound);

        return document;
    }




}
