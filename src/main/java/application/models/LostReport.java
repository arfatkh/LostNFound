package application.models;

import org.bson.Document;

public class LostReport extends Report{

    LostItem lostItem;

    public LostReport(User reporter, LostItem lostItem) {
        super(reporter, "lost");
        this.lostItem = lostItem;
    }

    public LostReport(Document document)
    {
        super(document);
        this.lostItem = new LostItem((Document) document.get("lostItem"));
    }

    public LostReport() {
        super();
    }



    public Document toDocument() {
        Document document = super.toDocument();
        document.append("lostItem", lostItem.toDocument());
        return document;
    }


    public LostItem getLostItem() {
        return lostItem;
    }
}
