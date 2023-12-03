package application.models;

import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;

public class LostItem extends Item{

    private Date lostDate;
    private User owner;

    public LostItem(String name, String location, String description, ArrayList<String> images, Date lostDate,User _owner)
    {
        super(name, location, description, images, "lost",_owner);
        this.lostDate = lostDate;
        this.owner = new User(_owner.toDocument());
    }


    public LostItem() {
        super();
    }

    public LostItem(Document lostItem) {
        super(lostItem);
        this.lostDate = lostItem.getDate("lostDate");
    }

    public Document toDocument() {
        Document document = super.toDocument();
        document.append("lostDate", lostDate);
        return document;
    }

}
