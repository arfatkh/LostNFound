package business;

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

    public Report submitReport(LostReport lostReport) {
        reportsRepository.ReportsRepository();
        return reportsRepository.submitReport(lostReport);

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
