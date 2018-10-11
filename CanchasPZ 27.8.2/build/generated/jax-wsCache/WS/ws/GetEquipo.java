
package ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para getEquipo complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="getEquipo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="usu" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contra" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getEquipo", propOrder = {
    "usu",
    "contra"
})
public class GetEquipo {

    protected String usu;
    protected String contra;

    /**
     * Obtiene el valor de la propiedad usu.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsu() {
        return usu;
    }

    /**
     * Define el valor de la propiedad usu.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsu(String value) {
        this.usu = value;
    }

    /**
     * Obtiene el valor de la propiedad contra.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContra() {
        return contra;
    }

    /**
     * Define el valor de la propiedad contra.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContra(String value) {
        this.contra = value;
    }

}
