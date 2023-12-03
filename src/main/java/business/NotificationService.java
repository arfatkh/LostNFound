package business;

import application.models.LostItem;
import application.models.LostReport;
import application.models.User;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NotificationService {

    public  void sendFoundItemNotification(LostReport lostReport) {
        try {
            URL url = new URL("https://api.notificationapi.com/6937e60ugnrvi7iflren1ld4qv/sender");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method and headers
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("authorization", "Basic NjkzN2U2MHVnbnJ2aTdpZmxyZW4xbGQ0cXY6NWJvcXRqMmlycDNwOGpkaHBkN2VvajNtOTgydnU2Z2NhY2RjaGxyMnY4MGg1NnZvbnF1");
            connection.setDoOutput(true);

            // Construct the JSON request body for found item notification
            String requestBody = "{" +
                    "\"notificationId\": \"item_found\"," +
                    "\"user\": {" +
                    "\"id\": \"test_user_id\"," +
                    "\"email\": \""+lostReport.getReporter().getEmail()+"\"" +
                    "}," +
                    "\"mergeTags\": {" +
                    "\"firstName\": \""+lostReport.getReporter().getUsername()+"\"," +
                    "\"itemName\": \"["+lostReport.getLostItem().getName()+"]\"," +
                    "\"alerts\": [{" +
                    "\"title\": \"This is a new alert from your software!\"" +
                    "}]" +
                    "}" +
                    "}";


            // Write the JSON data to the request
            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(requestBody);
            outputStream.flush();
            outputStream.close();

            // Get the response code
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    void sendReportSubmissionConfirm()
    public  void sendNotification(String email, String Subject, String message) {
        try {
            URL url = new URL("https://api.notificationapi.com/6937e60ugnrvi7iflren1ld4qv/sender");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method and headers
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("authorization", "Basic NjkzN2U2MHVnbnJ2aTdpZmxyZW4xbGQ0cXY6NWJvcXRqMmlycDNwOGpkaHBkN2VvajNtOTgydnU2Z2NhY2RjaGxyMnY4MGg1NnZvbnF1");
            connection.setDoOutput(true);

            // Construct the JSON request body for found item notification
            String requestBody = "{" +
                    "\"notificationId\": \"notify\"," +
                    "\"user\": {" +
                    "\"id\": \"test_user_id\"," +
                    "\"email\": \"" + email + "\"" +
                    "}," +
                    "\"mergeTags\": {" +
                    "\"subject\": \"" + Subject + "\"," +
                    "\"content\": \"" + message + "\"" +


                    "}" +
                    "}";

            // Write the JSON data to the request
            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(requestBody);
            outputStream.flush();
            outputStream.close();

            // Get the response code
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            connection.disconnect();

            // Write the JSON data to the request

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

public static void main(String[] args) {
    NotificationService notificationService = new NotificationService();
    notificationService.sendNotification("arfat@duck.com", "test", "testr34324");

}
    }