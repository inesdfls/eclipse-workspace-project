package identity.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService
public class IdentityWebService {

    @WebMethod
    public boolean verifyIdentity(
        @WebParam(name = "nationalId") String nationalId
    ) {
        System.out.println("Received nationalId = " + nationalId);
        return nationalId != null && nationalId.startsWith("OK");
    }

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8082/identity", new IdentityWebService());
        System.out.println("SOAP Identity running: http://localhost:8082/identity?wsdl");
    }
}
