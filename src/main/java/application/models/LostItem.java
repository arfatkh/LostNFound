package application.models;

import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;

public class LostItem extends Item{

    private Date lostDate;

    public LostItem(String name, String location, String description, ArrayList<String> images, String type, Date lostDate)
    {
        super(name, location, description, images, "lost");
        this.lostDate = lostDate;
    }

    public Document toDocument() {
        Document document = super.toDocument();
        document.append("lostDate", lostDate);
        return document;
    }

}
