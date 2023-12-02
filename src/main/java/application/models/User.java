package application.models;
import org.bson.Document;

public class User {
    private String username;
    private String password;
    private String phone;
    private String email;





    public User(String name, String password, String phone, String email) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;

    }

    //constructor for the user from the databased document
    public User(Document document) {
        if (document == null) {
            return;
        }
        this.username = document.getString("username");
        this.password = document.getString("password");
        this.phone = document.getString("phone");
        this.email = document.getString("email");
    }

    // Getters and setters

    //to convert the user to a document
    public Document toDocument() {
        Document document = new Document();
        document.append("username", username);
        document.append("password", password);
        document.append("phone", phone);
        document.append("email", email);
        return document;
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }

    //for typecasting to a document automatically




    //to print the user
    @Override
    public String toString() {
        return "User [username=" + username + ", password=" + password + ", phone=" + phone + ", email=" + email + "]";
    }
    // ...
}