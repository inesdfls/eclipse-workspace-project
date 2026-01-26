package orchestrator.clients;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class GraphQLClient {

    private final String url = "http://localhost:8083/graphql";

    public String score(String act, String justification, double cost) throws Exception {

        String query =
                "query { scoreRisk(act: \\\"" + act + "\\\", justification: \\\"" + justification +
                        "\\\", cost: " + cost + ") { riskLevel confidence } }";

        String payload = "{\"query\":\"" + query.replace("\"", "\\\"") + "\"}";

        URL u = new URL(url);
        HttpURLConnection con = (HttpURLConnection) u.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);

        OutputStream os = con.getOutputStream();
        os.write(payload.getBytes("UTF-8"));
        os.flush();
        os.close();

        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) sb.append(line);
        br.close();

        con.disconnect();
        return sb.toString();
    }
}