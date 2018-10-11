
package ws;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para cancha complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="cancha">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="admId" type="{http://ws/}administrador" minOccurs="0"/>
 *         &lt;element name="canAbre" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="canCantJugadores" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="canCierra" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="canDireccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="canId" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="canLatitud" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="canLongitud" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="canNombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="canPrecioDia" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="canPrecioNoches" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="canTel" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="canUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cancha", propOrder = {
    "admId",
    "canAbre",
    "canCantJugadores",
    "canCierra",
    "canDireccion",
    "canId",
    "canLatitud",
    "canLongitud",
    "canNombre",
    "canPrecioDia",
    "canPrecioNoches",
    "canTel",
    "canUrl"
})
public class Cancha {

    protected Administrador admId;
    protected BigInteger canAbre;
    protected BigInteger canCantJugadores;
    protected BigInteger canCierra;
    protected String canDireccion;
    protected BigDecimal canId;
    protected BigInteger canLatitud;
    protected BigInteger canLongitud;
    protected String canNombre;
    protected BigInteger canPrecioDia;
    protected BigInteger canPrecioNoches;
    protected BigInteger canTel;
    protected String canUrl;

    /**
     * Obtiene el valor de la propiedad admId.
     * 
     * @return
     *     possible object is
     *     {@link Administrador }
     *     
     */
    public Administrador getAdmId() {
        return admId;
    }

    /**
     * Define el valor de la propiedad admId.
     * 
     * @param value
     *     allowed object is
     *     {@link Administrador }
     *     
     */
    public void setAdmId(Administrador value) {
        this.admId = value;
    }

    /**
     * Obtiene el valor de la propiedad canAbre.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCanAbre() {
        return canAbre;
    }

    /**
     * Define el valor de la propiedad canAbre.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCanAbre(BigInteger value) {
        this.canAbre = value;
    }

    /**
     * Obtiene el valor de la propiedad canCantJugadores.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCanCantJugadores() {
        return canCantJugadores;
    }

    /**
     * Define el valor de la propiedad canCantJugadores.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCanCantJugadores(BigInteger value) {
        this.canCantJugadores = value;
    }

    /**
     * Obtiene el valor de la propiedad canCierra.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCanCierra() {
        return canCierra;
    }

    /**
     * Define el valor de la propiedad canCierra.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCanCierra(BigInteger value) {
        this.canCierra = value;
    }

    /**
     * Obtiene el valor de la propiedad canDireccion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCanDireccion() {
        return canDireccion;
    }

    /**
     * Define el valor de la propiedad canDireccion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCanDireccion(String value) {
        this.canDireccion = value;
    }

    /**
     * Obtiene el valor de la propiedad canId.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCanId() {
        return canId;
    }

    /**
     * Define el valor de la propiedad canId.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCanId(BigDecimal value) {
        this.canId = value;
    }

    /**
     * Obtiene el valor de la propiedad canLatitud.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCanLatitud() {
        return canLatitud;
    }

    /**
     * Define el valor de la propiedad canLatitud.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCanLatitud(BigInteger value) {
        this.canLatitud = value;
    }

    /**
     * Obtiene el valor de la propiedad canLongitud.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCanLongitud() {
        return canLongitud;
    }

    /**
     * Define el valor de la propiedad canLongitud.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCanLongitud(BigInteger value) {
        this.canLongitud = value;
    }

    /**
     * Obtiene el valor de la propiedad canNombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCanNombre() {
        return canNombre;
    }

    /**
     * Define el valor de la propiedad canNombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCanNombre(String value) {
        this.canNombre = value;
    }

    /**
     * Obtiene el valor de la propiedad canPrecioDia.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCanPrecioDia() {
        return canPrecioDia;
    }

    /**
     * Define el valor de la propiedad canPrecioDia.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCanPrecioDia(BigInteger value) {
        this.canPrecioDia = value;
    }

    /**
     * Obtiene el valor de la propiedad canPrecioNoches.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCanPrecioNoches() {
        return canPrecioNoches;
    }

    /**
     * Define el valor de la propiedad canPrecioNoches.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCanPrecioNoches(BigInteger value) {
        this.canPrecioNoches = value;
    }

    /**
     * Obtiene el valor de la propiedad canTel.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCanTel() {
        return canTel;
    }

    /**
     * Define el valor de la propiedad canTel.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCanTel(BigInteger value) {
        this.canTel = value;
    }

    /**
     * Obtiene el valor de la propiedad canUrl.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCanUrl() {
        return canUrl;
    }

    /**
     * Define el valor de la propiedad canUrl.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCanUrl(String value) {
        this.canUrl = value;
    }

}
