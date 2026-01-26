package notification.api;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/notify")
public class NotifyResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String notify(String payload) {
        System.out.println("[NOTIFICATION RECEIVED] " + payload);
        return "{\"status\":\"OK\"}";
    }

    // Pour tester dans navigateur
    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        return "Notification service is running";
    }
}