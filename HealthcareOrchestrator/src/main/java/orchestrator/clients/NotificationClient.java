package orchestrator.clients;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NotificationClient {

    private final String url = "http://localhost:8080/NotificationService/api/notify";

    public void send(String requestId, String message) {
        HttpURLConnection con = null;
        try {
            URL u = new URL(url);
            con = (HttpURLConnection) u.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);

            String json = "{\"requestId\":\"" + requestId + "\",\"message\":\"" + message + "\"}";
            OutputStream os = con.getOutputStream();
            os.write(json.getBytes("UTF-8"));
            os.flush();
            os.close();

            con.getResponseCode();
        } catch (Exception e) {
            System.out.println("[NOTIFY ERROR] " + e.getMessage());
        } finally {
            if (con != null) con.disconnect();
        }
    }
}