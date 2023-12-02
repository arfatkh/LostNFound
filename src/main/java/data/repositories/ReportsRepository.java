package data.repositories;



import application.models.LostItem;
import application.models.LostReport;
import application.models.User;
import data.connection;
import application.models.Report;
//mongodb imports
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import org.bson.Document;

import java.util.ArrayList;

public class ReportsRepository {


    connection connection;
    MongoClient mongoClient;
    MongoCollection<Document> reports;

    //main function to test the connection

    public void ReportsRepository() {
        this.connection = new connection();
        this.mongoClient = connection.getMongoClient();

        MongoDatabase database = mongoClient.getDatabase("lostNFound");
        this.reports = database.getCollection("reports");

        MongoCollection reports1 = database.getCollection("reports");

        //prtint users
        for (Object user : reports1.find()) {
            System.out.println(user);
        }

    }

        public Report submitReport(LostReport report) {

                Document document = report.toDocument();

                String reportID = reports.insertOne(document).getInsertedId().toString();

                report.setReportID(reportID);
                return report;

        }

        public  void printReports() {
            for (Object report : reports.find()) {
                System.out.println(report);
            }
        }

//        public  static void main(String[] args) {
//            ReportsRepository reportsRepository = new ReportsRepository();
//            reportsRepository.ReportsRepository();
//
//
//            User user = new User("username", "password", "phone", "email");
//            ArrayList<String> images = new ArrayList<String>();
//            LostItem lostItem = new LostItem("name", "location", "description", images, "type");
//
//            LostReport lostReport = new LostReport(user, lostItem);
//
//            reportsRepository.submitReport(lostReport);
//
//        }










}
