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

    public ArrayList<FoundReport> getFoundReports() {
        return reportsRepository.getFoundReports();
    }

    public ArrayList<LostReport> getLostReports() {
        return reportsRepository.getLostReports();
    }







    public void getReport() {
    }

    public void getReports() {
    }

    public void updateReport() {
    }

    public void deleteReport() {
    }



}
