package orchestrator.storage;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import orchestrator.model.CareRequest;

public class RequestStore {
    private static final Map<String, CareRequest> db = new ConcurrentHashMap<>();

    public static CareRequest create(CareRequest req) {
        String id = "REQ-" + UUID.randomUUID().toString().substring(0, 8);
        req.setRequestId(id);
        db.put(id, req);
        return req;
    }

    public static CareRequest get(String id) {
        return db.get(id);
    }

    public static void update(CareRequest req) {
        db.put(req.getRequestId(), req);
    }
}