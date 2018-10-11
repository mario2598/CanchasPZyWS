
package ws;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para equipo complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="equipo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="equId" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="equNomJug1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="equNomJug2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="equNombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="equPassword" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="equPts" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="equTelJug1" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="equTelJug2" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="equUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="equUsu" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="retoId" type="{http://ws/}reto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "equipo", propOrder = {
    "equId",
    "equNomJug1",
    "equNomJug2",
    "equNombre",
    "equPassword",
    "equPts",
    "equTelJug1",
    "equTelJug2",
    "equUrl",
    "equUsu",
    "retoId"
})
public class Equipo {

    protected BigDecimal equId;
    protected String equNomJug1;
    protected String equNomJug2;
    protected String equNombre;
    protected String equPassword;
    protected BigInteger equPts;
    protected BigInteger equTelJug1;
    protected BigInteger equTelJug2;
    protected String equUrl;
    protected String equUsu;
    protected Reto retoId;

    /**
     * Obtiene el valor de la propiedad equId.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getEquId() {
        return equId;
    }

    /**
     * Define el valor de la propiedad equId.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setEquId(BigDecimal value) {
        this.equId = value;
    }

    /**
     * Obtiene el valor de la propiedad equNomJug1.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEquNomJug1() {
        return equNomJug1;
    }

    /**
     * Define el valor de la propiedad equNomJug1.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEquNomJug1(String value) {
        this.equNomJug1 = value;
    }

    /**
     * Obtiene el valor de la propiedad equNomJug2.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEquNomJug2() {
        return equNomJug2;
    }

    /**
     * Define el valor de la propiedad equNomJug2.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEquNomJug2(String value) {
        this.equNomJug2 = value;
    }

    /**
     * Obtiene el valor de la propiedad equNombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEquNombre() {
        return equNombre;
    }

    /**
     * Define el valor de la propiedad equNombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEquNombre(String value) {
        this.equNombre = value;
    }

    /**
     * Obtiene el valor de la propiedad equPassword.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEquPassword() {
        return equPassword;
    }

    /**
     * Define el valor de la propiedad equPassword.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEquPassword(String value) {
        this.equPassword = value;
    }

    /**
     * Obtiene el valor de la propiedad equPts.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getEquPts() {
        return equPts;
    }

    /**
     * Define el valor de la propiedad equPts.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setEquPts(BigInteger value) {
        this.equPts = value;
    }

    /**
     * Obtiene el valor de la propiedad equTelJug1.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getEquTelJug1() {
        return equTelJug1;
    }

    /**
     * Define el valor de la propiedad equTelJug1.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setEquTelJug1(BigInteger value) {
        this.equTelJug1 = value;
    }

    /**
     * Obtiene el valor de la propiedad equTelJug2.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getEquTelJug2() {
        return equTelJug2;
    }

    /**
     * Define el valor de la propiedad equTelJug2.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setEquTelJug2(BigInteger value) {
        this.equTelJug2 = value;
    }

    /**
     * Obtiene el valor de la propiedad equUrl.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEquUrl() {
        return equUrl;
    }

    /**
     * Define el valor de la propiedad equUrl.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEquUrl(String value) {
        this.equUrl = value;
    }

    /**
     * Obtiene el valor de la propiedad equUsu.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEquUsu() {
        return equUsu;
    }

    /**
     * Define el valor de la propiedad equUsu.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEquUsu(String value) {
        this.equUsu = value;
    }

    /**
     * Obtiene el valor de la propiedad retoId.
     * 
     * @return
     *     possible object is
     *     {@link Reto }
     *     
     */
    public Reto getRetoId() {
        return retoId;
    }

    /**
     * Define el valor de la propiedad retoId.
     * 
     * @param value
     *     allowed object is
     *     {@link Reto }
     *     
     */
    public void setRetoId(Reto value) {
        this.retoId = value;
    }

}
