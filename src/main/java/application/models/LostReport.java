package application.models;

import org.bson.Document;

public class LostReport extends Report{

    LostItem lostItem;

    public LostReport(User reporter, LostItem lostItem) {
        super(reporter, "lost");
        this.lostItem = lostItem;
    }

    public LostReport() {
        super();
    }

    public Document toDocument() {
        Document document = super.toDocument();
        document.append("lostItem", lostItem.toDocument());
        return document;
    }


    public Item getLostItem() {
        return lostItem;
    }
}