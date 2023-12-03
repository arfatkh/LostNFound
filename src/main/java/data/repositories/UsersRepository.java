package data.repositories;



import data.connection;
import application.models.User;
//mongodb imports
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import org.bson.Document;

import java.util.ArrayList;

public class UsersRepository {

    connection connection;
    MongoClient mongoClient;
    MongoCollection<Document> users;

    //main function to test the connection

    public  UsersRepository() {
        this.connection = new connection();
        this.mongoClient = connection.getMongoClient();

        MongoDatabase database = mongoClient.getDatabase("lostNFound");
        this.users = database.getCollection("users");

        MongoCollection users1 = database.getCollection("users");

        //prtint users
        for (Object user : users1.find()) {
            System.out.println(user);
        }

    }


    //to run the cureent file to test the connection


    public User authenticate(String email, String password) {



        Object _user = users.find(Filters.eq("email", email)).first();

        System.out.println("User found: " + _user);

        if (_user == null) {
            return null;
        }

        User curr_user = new User((Document) _user);

        if (curr_user.getPassword().equals(password)) {
            return curr_user;
        } else {
            return null;
        }


    }


    public User register(User user_) {

        Document _user  = users.find(Filters.eq("email", user_.getEmail())).first();
        User curr_user;

        if (_user != null) {
            curr_user = new User((Document) _user);
        }
        else {
            curr_user = null;
        }

        if (curr_user == null) {
            users.insertOne(user_.toDocument());
            return user_;
        } else {
            return null;
        }

    }


    public User getUser(String username) {

        Object _user = users.find(Filters.eq("username", username)).first();
        User curr_user = new User((Document) _user);

        if (curr_user != null) {
            return curr_user;
        } else {
            return null;
        }

    }

    public ArrayList<User> getUsers() {

        ArrayList<User> usersList = new ArrayList<User>();

        for (Document document : users.find()) {
            usersList.add(new User(document));
        }

        return usersList;

    }

    public User updateUser(String email, User user_) {

        Document _user = users.find(Filters.eq("email", email)).first();
        User curr_user = new User((Document) _user);

        if (curr_user != null) {
            users.replaceOne(Filters.eq("email", email), user_.toDocument());
            return user_;
        } else {
            return null;
        }

    }


    public void deleteUser(String email) {


        users.deleteOne(Filters.eq("email", email));
    }
}
