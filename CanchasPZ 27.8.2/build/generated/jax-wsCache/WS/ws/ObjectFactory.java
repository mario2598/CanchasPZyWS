
package ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ws package. 
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

    private final static QName _Administrador_QNAME = new QName("http://ws/", "administrador");
    private final static QName _Cancha_QNAME = new QName("http://ws/", "cancha");
    private final static QName _Equipo_QNAME = new QName("http://ws/", "equipo");
    private final static QName _GetAdminResponse_QNAME = new QName("http://ws/", "getAdminResponse");
    private final static QName _GetAdmin_QNAME = new QName("http://ws/", "getAdmin");
    private final static QName _GetEquipo_QNAME = new QName("http://ws/", "getEquipo");
    private final static QName _Reto_QNAME = new QName("http://ws/", "reto");
    private final static QName _GetEquipoResponse_QNAME = new QName("http://ws/", "getEquipoResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Administrador }
     * 
     */
    public Administrador createAdministrador() {
        return new Administrador();
    }

    /**
     * Create an instance of {@link Cancha }
     * 
     */
    public Cancha createCancha() {
        return new Cancha();
    }

    /**
     * Create an instance of {@link Equipo }
     * 
     */
    public Equipo createEquipo() {
        return new Equipo();
    }

    /**
     * Create an instance of {@link GetAdminResponse }
     * 
     */
    public GetAdminResponse createGetAdminResponse() {
        return new GetAdminResponse();
    }

    /**
     * Create an instance of {@link GetAdmin }
     * 
     */
    public GetAdmin createGetAdmin() {
        return new GetAdmin();
    }

    /**
     * Create an instance of {@link GetEquipo }
     * 
     */
    public GetEquipo createGetEquipo() {
        return new GetEquipo();
    }

    /**
     * Create an instance of {@link Reto }
     * 
     */
    public Reto createReto() {
        return new Reto();
    }

    /**
     * Create an instance of {@link GetEquipoResponse }
     * 
     */
    public GetEquipoResponse createGetEquipoResponse() {
        return new GetEquipoResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Administrador }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws/", name = "administrador")
    public JAXBElement<Administrador> createAdministrador(Administrador value) {
        return new JAXBElement<Administrador>(_Administrador_QNAME, Administrador.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Cancha }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws/", name = "cancha")
    public JAXBElement<Cancha> createCancha(Cancha value) {
        return new JAXBElement<Cancha>(_Cancha_QNAME, Cancha.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Equipo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws/", name = "equipo")
    public JAXBElement<Equipo> createEquipo(Equipo value) {
        return new JAXBElement<Equipo>(_Equipo_QNAME, Equipo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAdminResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws/", name = "getAdminResponse")
    public JAXBElement<GetAdminResponse> createGetAdminResponse(GetAdminResponse value) {
        return new JAXBElement<GetAdminResponse>(_GetAdminResponse_QNAME, GetAdminResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAdmin }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws/", name = "getAdmin")
    public JAXBElement<GetAdmin> createGetAdmin(GetAdmin value) {
        return new JAXBElement<GetAdmin>(_GetAdmin_QNAME, GetAdmin.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetEquipo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws/", name = "getEquipo")
    public JAXBElement<GetEquipo> createGetEquipo(GetEquipo value) {
        return new JAXBElement<GetEquipo>(_GetEquipo_QNAME, GetEquipo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Reto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws/", name = "reto")
    public JAXBElement<Reto> createReto(Reto value) {
        return new JAXBElement<Reto>(_Reto_QNAME, Reto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetEquipoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws/", name = "getEquipoResponse")
    public JAXBElement<GetEquipoResponse> createGetEquipoResponse(GetEquipoResponse value) {
        return new JAXBElement<GetEquipoResponse>(_GetEquipoResponse_QNAME, GetEquipoResponse.class, null, value);
    }

}
