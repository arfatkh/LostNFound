package application.models;

import org.bson.Document;

public class Feedback {
    private String type; // Feedback or Suggestion
    private String message;
    private String userEmail;

    public Feedback(String type, String message, String userEmail) {
        this.type = type;
        this.message = message;
        this.userEmail = userEmail;
    }


    public Feedback(Document doc) {
        this.type = doc.getString("type");
        this.message = doc.getString("message");
        this.userEmail = doc.getString("userEmail");

    }

    public Document toDocument() {
        Document doc = new Document();
        doc.append("type", this.type);
        doc.append("message", this.message);
        doc.append("userEmail", this.userEmail);
        return doc;
    }
                    // Getters and setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserEmail() { return userEmail; }





}
