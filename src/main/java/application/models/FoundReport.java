package application.models;

import org.bson.Document;

public class FoundReport extends Report {

private FoundItem foundItem;

    public FoundReport(User reporter, FoundItem foundItem) {
        super(reporter, "found");
        this.foundItem = foundItem;
    }

    public FoundReport() {
        super();
    }

    public FoundReport(Document document)
    {
        super(document);
        this.foundItem = new FoundItem((Document) document.get("foundItem"));
    }

    public FoundItem getFoundItem() {
        return foundItem;
    }

    public void setFoundItem(FoundItem foundItem) {
        this.foundItem = foundItem;
    }

    public Document toDocument() {
        Document document = super.toDocument();
        document.append("foundItem", foundItem.toDocument());
        return document;
    }






}
