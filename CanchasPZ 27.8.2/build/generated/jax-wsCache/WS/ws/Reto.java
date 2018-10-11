
package ws;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para reto complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="reto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="canchaId" type="{http://ws/}cancha" minOccurs="0"/>
 *         &lt;element name="equipo1Id" type="{http://ws/}equipo" minOccurs="0"/>
 *         &lt;element name="equipo2Id" type="{http://ws/}equipo" minOccurs="0"/>
 *         &lt;element name="retoCompleto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="retoFecha" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="retoHoraFin" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="retoHoraIni" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="retoId" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="retoNivel" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "reto", propOrder = {
    "canchaId",
    "equipo1Id",
    "equipo2Id",
    "retoCompleto",
    "retoFecha",
    "retoHoraFin",
    "retoHoraIni",
    "retoId",
    "retoNivel"
})
public class Reto {

    protected Cancha canchaId;
    protected Equipo equipo1Id;
    protected Equipo equipo2Id;
    protected String retoCompleto;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar retoFecha;
    protected BigInteger retoHoraFin;
    protected BigInteger retoHoraIni;
    protected BigDecimal retoId;
    protected BigInteger retoNivel;

    /**
     * Obtiene el valor de la propiedad canchaId.
     * 
     * @return
     *     possible object is
     *     {@link Cancha }
     *     
     */
    public Cancha getCanchaId() {
        return canchaId;
    }

    /**
     * Define el valor de la propiedad canchaId.
     * 
     * @param value
     *     allowed object is
     *     {@link Cancha }
     *     
     */
    public void setCanchaId(Cancha value) {
        this.canchaId = value;
    }

    /**
     * Obtiene el valor de la propiedad equipo1Id.
     * 
     * @return
     *     possible object is
     *     {@link Equipo }
     *     
     */
    public Equipo getEquipo1Id() {
        return equipo1Id;
    }

    /**
     * Define el valor de la propiedad equipo1Id.
     * 
     * @param value
     *     allowed object is
     *     {@link Equipo }
     *     
     */
    public void setEquipo1Id(Equipo value) {
        this.equipo1Id = value;
    }

    /**
     * Obtiene el valor de la propiedad equipo2Id.
     * 
     * @return
     *     possible object is
     *     {@link Equipo }
     *     
     */
    public Equipo getEquipo2Id() {
        return equipo2Id;
    }

    /**
     * Define el valor de la propiedad equipo2Id.
     * 
     * @param value
     *     allowed object is
     *     {@link Equipo }
     *     
     */
    public void setEquipo2Id(Equipo value) {
        this.equipo2Id = value;
    }

    /**
     * Obtiene el valor de la propiedad retoCompleto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRetoCompleto() {
        return retoCompleto;
    }

    /**
     * Define el valor de la propiedad retoCompleto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRetoCompleto(String value) {
        this.retoCompleto = value;
    }

    /**
     * Obtiene el valor de la propiedad retoFecha.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRetoFecha() {
        return retoFecha;
    }

    /**
     * Define el valor de la propiedad retoFecha.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRetoFecha(XMLGregorianCalendar value) {
        this.retoFecha = value;
    }

    /**
     * Obtiene el valor de la propiedad retoHoraFin.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRetoHoraFin() {
        return retoHoraFin;
    }

    /**
     * Define el valor de la propiedad retoHoraFin.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRetoHoraFin(BigInteger value) {
        this.retoHoraFin = value;
    }

    /**
     * Obtiene el valor de la propiedad retoHoraIni.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRetoHoraIni() {
        return retoHoraIni;
    }

    /**
     * Define el valor de la propiedad retoHoraIni.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRetoHoraIni(BigInteger value) {
        this.retoHoraIni = value;
    }

    /**
     * Obtiene el valor de la propiedad retoId.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRetoId() {
        return retoId;
    }

    /**
     * Define el valor de la propiedad retoId.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRetoId(BigDecimal value) {
        this.retoId = value;
    }

    /**
     * Obtiene el valor de la propiedad retoNivel.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRetoNivel() {
        return retoNivel;
    }

    /**
     * Define el valor de la propiedad retoNivel.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRetoNivel(BigInteger value) {
        this.retoNivel = value;
    }

}
