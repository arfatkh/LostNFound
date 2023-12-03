package data.repositories;


import application.models.*;
import data.connection;
//mongodb imports
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoClient;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Objects;

public class FeedbackRepository {

    connection connection;
    MongoClient mongoClient;
    MongoCollection<Document> feedbacks;


    public FeedbackRepository() {
        this.connection = new connection();
        this.mongoClient = connection.getMongoClient();

        MongoDatabase database = mongoClient.getDatabase("lostNFound");
        this.feedbacks = database.getCollection("feedbacks");

        MongoCollection<Document> feedbacks1 = database.getCollection("feedbacks");

        //prtint users
//        for (Object feedback : feedbacks1.find()) {
//            System.out.println(feedback);
//        }

    }


    public Feedback submitFeedback(Feedback feedback) {

        Document document = feedback.toDocument();

        String feedbackID = Objects.requireNonNull(feedbacks.insertOne(document).getInsertedId()).toString();

        if (feedbackID != null) {
            System.out.println("Feedback submitted");
        }

        return feedback;

    }






}
