/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mario
 */
@Entity
@Table(name = "CANCHA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cancha.findAll", query = "SELECT c FROM Cancha c")
    , @NamedQuery(name = "Cancha.findByCanId", query = "SELECT c FROM Cancha c WHERE c.canId = :canId")
    , @NamedQuery(name = "Cancha.findByCanNombre", query = "SELECT c FROM Cancha c WHERE c.canNombre = :canNombre")
    , @NamedQuery(name = "Cancha.findByCanDireccion", query = "SELECT c FROM Cancha c WHERE c.canDireccion = :canDireccion")
    , @NamedQuery(name = "Cancha.findByCanCantJugadores", query = "SELECT c FROM Cancha c WHERE c.canCantJugadores = :canCantJugadores")
    , @NamedQuery(name = "Cancha.findByCanLatitud", query = "SELECT c FROM Cancha c WHERE c.canLatitud = :canLatitud")
    , @NamedQuery(name = "Cancha.findByCanLongitud", query = "SELECT c FROM Cancha c WHERE c.canLongitud = :canLongitud")
    , @NamedQuery(name = "Cancha.findByCanPrecioDia", query = "SELECT c FROM Cancha c WHERE c.canPrecioDia = :canPrecioDia")
    , @NamedQuery(name = "Cancha.findByCanPrecioNoches", query = "SELECT c FROM Cancha c WHERE c.canPrecioNoches = :canPrecioNoches")
    , @NamedQuery(name = "Cancha.findByCanTel", query = "SELECT c FROM Cancha c WHERE c.canTel = :canTel")
    , @NamedQuery(name = "Cancha.findByCanCierra", query = "SELECT c FROM Cancha c WHERE c.canCierra = :canCierra")
    , @NamedQuery(name = "Cancha.findByCanAbre", query = "SELECT c FROM Cancha c WHERE c.canAbre = :canAbre")
    , @NamedQuery(name = "Cancha.findByCanUrl", query = "SELECT c FROM Cancha c WHERE c.canUrl = :canUrl")})
public class Cancha implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CAN_ID")
    private BigDecimal canId;
    @Size(max = 30)
    @Column(name = "CAN_NOMBRE")
    private String canNombre;
    @Size(max = 150)
    @Column(name = "CAN_DIRECCION")
    private String canDireccion;
    @Column(name = "CAN_CANT_JUGADORES")
    private BigInteger canCantJugadores;
    @Column(name = "CAN_LATITUD")
    private BigInteger canLatitud;
    @Column(name = "CAN_LONGITUD")
    private BigInteger canLongitud;
    @Column(name = "CAN_PRECIO_DIA")
    private BigInteger canPrecioDia;
    @Column(name = "CAN_PRECIO_NOCHES")
    private BigInteger canPrecioNoches;
    @Column(name = "CAN_TEL")
    private BigInteger canTel;
    @Column(name = "CAN_CIERRA")
    private BigInteger canCierra;
    @Column(name = "CAN_ABRE")
    private BigInteger canAbre;
    @Size(max = 200)
    @Column(name = "CAN_URL")
    private String canUrl;
    @JoinColumn(name = "ADM_ID", referencedColumnName = "ADM_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Administrador admId;
    @OneToMany(mappedBy = "canchaId", fetch = FetchType.LAZY)
    private List<Reto> retoList;
    @OneToMany(mappedBy = "canId", fetch = FetchType.LAZY)
    private List<Match> matchList;

    public Cancha() {
    }

    public Cancha(BigDecimal canId) {
        this.canId = canId;
    }

    public BigDecimal getCanId() {
        return canId;
    }

    public void setCanId(BigDecimal canId) {
        this.canId = canId;
    }

    public String getCanNombre() {
        return canNombre;
    }

    public void setCanNombre(String canNombre) {
        this.canNombre = canNombre;
    }

    public String getCanDireccion() {
        return canDireccion;
    }

    public void setCanDireccion(String canDireccion) {
        this.canDireccion = canDireccion;
    }

    public BigInteger getCanCantJugadores() {
        return canCantJugadores;
    }

    public void setCanCantJugadores(BigInteger canCantJugadores) {
        this.canCantJugadores = canCantJugadores;
    }

    public BigInteger getCanLatitud() {
        return canLatitud;
    }

    public void setCanLatitud(BigInteger canLatitud) {
        this.canLatitud = canLatitud;
    }

    public BigInteger getCanLongitud() {
        return canLongitud;
    }

    public void setCanLongitud(BigInteger canLongitud) {
        this.canLongitud = canLongitud;
    }

    public BigInteger getCanPrecioDia() {
        return canPrecioDia;
    }

    public void setCanPrecioDia(BigInteger canPrecioDia) {
        this.canPrecioDia = canPrecioDia;
    }

    public BigInteger getCanPrecioNoches() {
        return canPrecioNoches;
    }

    public void setCanPrecioNoches(BigInteger canPrecioNoches) {
        this.canPrecioNoches = canPrecioNoches;
    }

    public BigInteger getCanTel() {
        return canTel;
    }

    public void setCanTel(BigInteger canTel) {
        this.canTel = canTel;
    }

    public BigInteger getCanCierra() {
        return canCierra;
    }

    public void setCanCierra(BigInteger canCierra) {
        this.canCierra = canCierra;
    }

    public BigInteger getCanAbre() {
        return canAbre;
    }

    public void setCanAbre(BigInteger canAbre) {
        this.canAbre = canAbre;
    }

    public String getCanUrl() {
        return canUrl;
    }

    public void setCanUrl(String canUrl) {
        this.canUrl = canUrl;
    }

    public Administrador getAdmId() {
        return admId;
    }

    public void setAdmId(Administrador admId) {
        this.admId = admId;
    }

    @XmlTransient
    public List<Reto> getRetoList() {
        return retoList;
    }

    public void setRetoList(List<Reto> retoList) {
        this.retoList = retoList;
    }

    @XmlTransient
    public List<Match> getMatchList() {
        return matchList;
    }

    public void setMatchList(List<Match> matchList) {
        this.matchList = matchList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (canId != null ? canId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cancha)) {
            return false;
        }
        Cancha other = (Cancha) object;
        if ((this.canId == null && other.canId != null) || (this.canId != null && !this.canId.equals(other.canId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Cancha[ canId=" + canId + " ]";
    }
    
}
