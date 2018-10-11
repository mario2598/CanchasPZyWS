
package ws;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para administrador complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="administrador">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="admId" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="admPassword" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="admUsu" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "administrador", propOrder = {
    "admId",
    "admPassword",
    "admUsu"
})
public class Administrador {

    protected BigDecimal admId;
    protected String admPassword;
    protected String admUsu;

    /**
     * Obtiene el valor de la propiedad admId.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAdmId() {
        return admId;
    }

    /**
     * Define el valor de la propiedad admId.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAdmId(BigDecimal value) {
        this.admId = value;
    }

    /**
     * Obtiene el valor de la propiedad admPassword.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdmPassword() {
        return admPassword;
    }

    /**
     * Define el valor de la propiedad admPassword.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdmPassword(String value) {
        this.admPassword = value;
    }

    /**
     * Obtiene el valor de la propiedad admUsu.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdmUsu() {
        return admUsu;
    }

    /**
     * Define el valor de la propiedad admUsu.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdmUsu(String value) {
        this.admUsu = value;
    }

}
