package application.models;

import application.models.Item;
import application.models.LostItem;
import application.models.FoundItem;

import java.util.ArrayList;
import java.util.Date;
import org.bson.Document;

public class Report {

    private String reportID;
    private User reporter;
    private String type;

    private Date reportDate;


    //constructor
    public Report() {
    }

    public Report(User reporter, String type) {
        this.reporter = reporter;
        this.type = type;
        this.reportDate = new Date();
        //set the date to the current date
        reportDate = Date.from(reportDate.toInstant().plusSeconds(3600));



    }

    //constructor for the report from the databased document
    public Report(Document document)
    {
        this.reporter = new User((Document) document.get("reporter"));
        this.type = document.getString("type");
        this.reportDate = document.getDate("reportDate");
    }


    public void setReportID(String reportID) {
        this.reportID = reportID;
    }

    public String getReportID() {
        return reportID;
    }

    public User getReporter() {
        return reporter;
    }

    public Document toDocument() {
        Document document = new Document();
        document.append("reporter", reporter.toDocument());
        document.append("type", type);
        document.append("reportDate", reportDate);
        return document;
    }

}
