
package orchestrator.soap.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the orchestrator.soap.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _VerifyIdentity_QNAME = new QName("http://soap.identity/", "verifyIdentity");
    private final static QName _VerifyIdentityResponse_QNAME = new QName("http://soap.identity/", "verifyIdentityResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: orchestrator.soap.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link VerifyIdentity }
     * 
     */
    public VerifyIdentity createVerifyIdentity() {
        return new VerifyIdentity();
    }

    /**
     * Create an instance of {@link VerifyIdentityResponse }
     * 
     */
    public VerifyIdentityResponse createVerifyIdentityResponse() {
        return new VerifyIdentityResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VerifyIdentity }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.identity/", name = "verifyIdentity")
    public JAXBElement<VerifyIdentity> createVerifyIdentity(VerifyIdentity value) {
        return new JAXBElement<VerifyIdentity>(_VerifyIdentity_QNAME, VerifyIdentity.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VerifyIdentityResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.identity/", name = "verifyIdentityResponse")
    public JAXBElement<VerifyIdentityResponse> createVerifyIdentityResponse(VerifyIdentityResponse value) {
        return new JAXBElement<VerifyIdentityResponse>(_VerifyIdentityResponse_QNAME, VerifyIdentityResponse.class, null, value);
    }

}
