package application.models;

import java.util.Date;
import org.bson.Document;

public class Report {

    private String reportID;
    private User reporter;
    private String type;

    private Date reportDate;

    private String status; //pending, approved, rejected , completed


    //constructor
    public Report() {
    }

    public Report(User reporter, String type) {
        this.reporter = reporter;
        this.type = type;
        this.reportDate = new Date();
        this.status = "pending";
        //set the date to the current date
        reportDate = Date.from(reportDate.toInstant().plusSeconds(3600));



    }

    //constructor for the report from the databased document
    public Report(Document document)
    {
        this.reporter = new User((Document) document.get("reporter"));
        this.reportID = document.getObjectId("_id").toString();;
        this.type = document.getString("type");
        this.reportDate = document.getDate("reportDate");
        this.status = document.getString("status");
    }


    public void setReportID(String reportID) {
        this.reportID = reportID;
    }

    public String getReportID() {
        return reportID;
    }

    public String getStatus() {
        return status;
    }
    public User getReporter() {
        return reporter;
    }

    public String getType() {
        return type;
    }
    public Document toDocument() {
        Document document = new Document();
        document.append("reporter", reporter.toDocument());
        document.append("type", type);
        document.append("reportDate", reportDate);
        document.append("status", status);
        return document;
    }

}
