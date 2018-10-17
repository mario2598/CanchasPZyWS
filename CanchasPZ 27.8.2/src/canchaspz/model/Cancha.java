/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canchaspz.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

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
    , @NamedQuery(name = "Cancha.findByCanCantJugadores", query = "SELECT c FROM Cancha c WHERE c.canCantJugadores = :canCantJugadores")
    , @NamedQuery(name = "Cancha.findByCanLatitud", query = "SELECT c FROM Cancha c WHERE c.canLatitud = :canLatitud")
    , @NamedQuery(name = "Cancha.findByCanDireccion", query = "SELECT c FROM Cancha c WHERE c.canDireccion = :canDireccion")
    , @NamedQuery(name = "Cancha.findByCanCierra", query = "SELECT c FROM Cancha c WHERE c.canCierra = :canCierra")
    , @NamedQuery(name = "Cancha.findByCanAbre", query = "SELECT c FROM Cancha c WHERE c.canAbre = :canAbre")
    , @NamedQuery(name = "Cancha.findByCanTel", query = "SELECT c FROM Cancha c WHERE c.canTel = :canTel")
    , @NamedQuery(name = "Cancha.findByCanPrecioNoches", query = "SELECT c FROM Cancha c WHERE c.canPrecioNoches = :canPrecioNoches")
    , @NamedQuery(name = "Cancha.findByCanPrecioDia", query = "SELECT c FROM Cancha c WHERE c.canPrecioDia = :canPrecioDia")
    , @NamedQuery(name = "Cancha.findByCanLongitud", query = "SELECT c FROM Cancha c WHERE c.canLongitud = :canLongitud")})
public class Cancha implements Serializable {

    @Column(name = "CAN_CANT_JUGADORES")
    private Long canCantJugadores;
    @Column(name = "CAN_LATITUD")
    private Double canLatitud;
    @Column(name = "CAN_LONGITUD")
    private Double canLongitud;
    @Column(name = "CAN_PRECIO_DIA")
    private Long canPrecioDia;
    @Column(name = "CAN_PRECIO_NOCHES")
    private Long canPrecioNoches;
    @Column(name = "CAN_TEL")
    private Long canTel;
    @Column(name = "CAN_CIERRA")
    private Long canCierra;
    @Column(name = "CAN_ABRE")
    private Long canAbre;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "canchaId", fetch = FetchType.LAZY)
    private List<Reto> retoList;
    @Column(name = "CAN_URL")
    private String canUrl;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "canId", fetch = FetchType.LAZY)
    private List<Match> matchList;

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name="FIELD_SEQ_NAME",sequenceName="UNA.CANCHA_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="FIELD_SEQ_NAME")
    @Basic(optional = false)
    @Column(name = "CAN_ID")
    private Long canId;
    @Basic(optional = false)
    @Column(name = "CAN_NOMBRE")
    private String canNombre;
    @Column(name = "CAN_DIRECCION")
    private String canDireccion;
  //  @JoinColumn(name = "ADM_ID", referencedColumnName = "ADM_ID")
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Administrador admId;

    public Cancha() {
    }

    public Cancha(Long canId) {
        this.canId = canId;
    }

    public Cancha(Long canId, String canNombre, Long canAbre) {
        this.canId = canId;
        this.canNombre = canNombre;
        this.canAbre = canAbre;
    }

    public Cancha(CanchaDto field) { 
        if(field.getCanID()!=null)
            this.canId = field.getCanID();
        else
            this.canId = null;
        refreshData(field);
    }
    
    public void refreshData(CanchaDto field){
        this.canNombre = field.getCanNombre();
        this.canDireccion = field.getCanDireccion();
        this.canUrl = field.getCanUrl();
//        this.admId = field.getAdmId();
        this.canAbre = field.getCanAbre();
        this.canCantJugadores = field.getCanCantJugadores();
        this.canCierra = field.getCanCierra();
        this.canLatitud = field.getCanLatitud();
        this.canLongitud = field.getCanLongitud();
        this.canPrecioDia = field.getCanPrecioDia();
        this.canPrecioNoches = field.getCanPrecioNoches();
        this.canTel = field.getCanTel();
        this.retoList = field.getRetoList();
        this.matchList = field.getMatchList();
    }
    
    public Long getCanId() {
        return canId;
    }

    public void setCanId(Long canId) {
        this.canId = canId;
    }

    public String getCanNombre() {
        return canNombre;
    }

    public void setCanNombre(String canNombre) {
        this.canNombre = canNombre;
    }

    public Long getCanCantJugadores() {
        return canCantJugadores;
    }

    public void setCanCantJugadores(Long canCantJugadores) {
        this.canCantJugadores = canCantJugadores;
    }

    public Double getCanLatitud() {
        return canLatitud;
    }

    public void setCanLatitud(Double canLatitud) {
        this.canLatitud = canLatitud;
    }

    public String getCanDireccion() {
        return canDireccion;
    }

    public void setCanDireccion(String canDireccion) {
        this.canDireccion = canDireccion;
    }

    public Long getCanCierra() {
        return canCierra;
    }

    public void setCanCierra(Long canCierra) {
        this.canCierra = canCierra;
    }

    public Long getCanAbre() {
        return canAbre;
    }

    public void setCanAbre(Long canAbre) {
        this.canAbre = canAbre;
    }

    public Long getCanTel() {
        return canTel;
    }

    public void setCanTel(Long canTel) {
        this.canTel = canTel;
    }

    public Long getCanPrecioNoches() {
        return canPrecioNoches;
    }

    public void setCanPrecioNoches(Long canPrecioNoches) {
        this.canPrecioNoches = canPrecioNoches;
    }

    public Long getCanPrecioDia() {
        return canPrecioDia;
    }

    public void setCanPrecioDia(Long canPrecioDia) {
        this.canPrecioDia = canPrecioDia;
    }

    public Double getCanLongitud() {
        return canLongitud;
    }

    public void setCanLongitud(Double canLongitud) {
        this.canLongitud = canLongitud;
    }

//    public Administrador getAdmId() {
 //       return admId;
 //   }

 //   public void setAdmId(Administrador admId) {
  //      this.admId = admId;
  //  }
    
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
        return canNombre;
    }

    public List<Match> getMatchList() {
        return matchList;
    }

    public void setMatchList(List<Match> matchList) {
        this.matchList = matchList;
    }

    public String getCanUrl() {
        return canUrl;
    }

    public void setCanUrl(String canUrl) {
        this.canUrl = canUrl;
    }
    
    public List<Reto> getRetoList() {
        return retoList;
    }

    public void setRetoList(List<Reto> retoList) {
        this.retoList = retoList;
    }

}
