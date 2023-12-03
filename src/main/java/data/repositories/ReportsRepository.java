package data.repositories;



import application.models.*;
import data.connection;
//mongodb imports
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoClient;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Objects;

public class ReportsRepository {


    connection connection;
    MongoClient mongoClient;
    MongoCollection<Document> reports;

    //main function to test the connection

    public ReportsRepository() {
        this.connection = new connection();
        this.mongoClient = connection.getMongoClient();

        MongoDatabase database = mongoClient.getDatabase("lostNFound");
        this.reports = database.getCollection("reports");

        MongoCollection<Document> reports1 = database.getCollection("reports");

        //prtint users
        for (Object user : reports1.find()) {
            System.out.println(user);
        }

    }

        public Report submitReport(Report report) {

                Document document = report.toDocument();

                String reportID = Objects.requireNonNull(reports.insertOne(document).getInsertedId()).toString();

                report.setReportID(reportID);
                return report;

        }

        public  void printReports() {
            for (Object report : reports.find()) {
                System.out.println(report);
            }
        }



       public ArrayList<FoundReport> getApprovedFoundReports()
       {
              ArrayList<FoundReport> foundReports = new ArrayList<FoundReport>();


              //status approved
                for (Document document : reports.find(Filters.and(Filters.eq("type", "found"), Filters.eq("status", "approved")))) {
                    foundReports.add(new FoundReport(document));
                }

              return foundReports;
       }

         public ArrayList<LostReport> getLostReports()
         {
                  ArrayList<LostReport> lostReports = new ArrayList<LostReport>();

                  for (Document document : reports.find(Filters.eq("type", "lost"))) {
                 lostReports.add(new LostReport(document));
                  }

                  return lostReports;
         }

    public ArrayList<Report> getPendingReports() {

ArrayList<Report> pendingReports = new ArrayList<Report>();

        for (Document document : reports.find(Filters.eq("status", "pending"))) {
            if (document.getString("type").equals("found")) {
                pendingReports.add(new FoundReport(document));
            } else {
                pendingReports.add(new LostReport(document));
            }
        }

        return pendingReports;

    }

    public Report setReportStatus(String reportID, String status) {

//        get by objectID is reportID
        Document document = reports.find(Filters.eq("_id",new ObjectId(reportID))).first();


        if (document == null) {
            return null;
        }

        document.replace("status", status);
        reports.replaceOne(Filters.eq("_id", new ObjectId(reportID)), document);

        if (document.getString("type").equals("found")) {
            return new FoundReport(document);
        } else {
            return new LostReport(document);
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
