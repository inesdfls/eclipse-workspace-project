package orchestrator.api;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import orchestrator.logic.OrchestratorEngine;
import orchestrator.model.CareRequest;
import orchestrator.model.RequestStatus;
import orchestrator.storage.RequestStore;

@Path("/care-requests")
public class CareRequestResource {

    private final OrchestratorEngine engine = new OrchestratorEngine();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CareRequest submit(CareRequest request) {

        request.setStatus(RequestStatus.SUBMITTED);
        request.addHistory("SUBMITTED", "Care request submitted");

        RequestStore.create(request);
        RequestStore.update(request);

        engine.process(request);
        return request;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public CareRequest get(@PathParam("id") String id) {
        CareRequest req = RequestStore.get(id);
        if (req == null) throw new NotFoundException("Request not found");
        return req;
    }

    @GET
    @Path("/{id}/history")
    @Produces(MediaType.APPLICATION_JSON)
    public Object history(@PathParam("id") String id) {
        CareRequest req = RequestStore.get(id);
        if (req == null) throw new NotFoundException("Request not found");
        return req.getHistory();
    }
}