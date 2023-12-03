package business;

import application.models.FoundReport;
import application.models.LostReport;
import application.models.Report;
import application.models.User;

import java.util.ArrayList;

import data.repositories.ReportsRepository;

public class ReportService {

    ReportsRepository reportsRepository;


    public ReportService() {
        this.reportsRepository = new ReportsRepository();
    }

    public Report submitReport(Report report) {

        return reportsRepository.submitReport(report);

    }

    public ArrayList<FoundReport> getApprovedFoundReports() {
        return reportsRepository.getApprovedFoundReports();
    }

    public ArrayList<LostReport> getLostReports() {
        return reportsRepository.getLostReports();
    }

    public Report setReportStatus(String reportID, String status) {
        return reportsRepository.setReportStatus(reportID, status);
    }









    public ArrayList<Report> getReports() {
        return reportsRepository.getReports();
    }

    public void updateReport() {
    }

    public void deleteReport() {
    }


    public ArrayList<Report> getPendingReports() {
        return reportsRepository.getPendingReports();
    }

    public ArrayList<LostReport> getApprovedLostReports() {
        return reportsRepository.getApprovedLostReports();
    }
}
