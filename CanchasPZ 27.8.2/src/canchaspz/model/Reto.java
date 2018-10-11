/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canchaspz.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mario
 */
@Entity
@Table(name = "RETO")
@XmlRootElement
@NamedQueries({
      @NamedQuery(name = "Reto.findRetos", query = "Select r from Reto r join r.equipo1Id e WHERE e.equId != :equId")
    , @NamedQuery(name = "Reto.findByRetoId", query = "SELECT r FROM Reto r WHERE r.retoId = :retoId")
    , @NamedQuery(name = "Reto.findByRetoList", query = "SELECT r FROM Reto r")
    , @NamedQuery(name = "Reto.findByRetoCancha", query = "SELECT r FROM Reto r join r.canchaId c WHERE c.canId = :canId")
    , @NamedQuery(name = "Reto.findByRetoHoraIni", query = "SELECT r FROM Reto r WHERE r.retoHoraIni = :retoHoraIni")
    , @NamedQuery(name = "Reto.findByRetoHoraFin", query = "SELECT r FROM Reto r WHERE r.retoHoraFin = :retoHoraFin")
    , @NamedQuery(name = "Reto.findByRetoFecha", query = "SELECT r FROM Reto r WHERE r.retoFecha = :retoFecha")
    , @NamedQuery(name = "Reto.findByRetoCompleto", query = "SELECT r FROM Reto r WHERE r.retoCompleto = :retoCompleto")})
public class Reto implements Serializable {

    @Column(name = "RETO_HORA_INI")
    private Long retoHoraIni;
    @Column(name = "RETO_HORA_FIN")
    private Long retoHoraFin;
    @Column(name = "RETO_NIVEL")
    private Long retoNivel;

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name="RETO_SEQ_NAME",sequenceName="UNA.RETO_SEQ01",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="RETO_SEQ_NAME")
    @Basic(optional = false) 
    @Column(name = "RETO_ID")
    private Long retoId;
    @Column(name = "RETO_FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date retoFecha;
    @Column(name = "RETO_COMPLETO")
    private String retoCompleto;
    @OneToMany(mappedBy = "retoId", fetch = FetchType.LAZY)
    private List<Equipo> equipoList;
    @JoinColumn(name = "CANCHA_ID", referencedColumnName = "CAN_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Cancha canchaId;
    @JoinColumn(name = "EQUIPO1_ID", referencedColumnName = "EQU_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Equipo equipo1Id;
    @JoinColumn(name = "EQUIPO2_ID", referencedColumnName = "EQU_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Equipo equipo2Id;

    public Reto() {
    }

    public Reto(RetoDto reto){
        String i = "0";
        if(reto.getRetoId() != Long.valueOf(i)){
          this.retoId = reto.getRetoId();  
        }
        else{ 
            this.retoId = null;
        }
        ActualizarReto(reto);
    }
    
    public void ActualizarReto(RetoDto reto){
        this.retoHoraIni = reto.getRetoHoraIni();
        this.retoHoraFin = reto.getRetoHoraFin();
        this.retoFecha = Date.from(reto.getRetoFecha().atStartOfDay(ZoneId.systemDefault()).toInstant());
        this.retoCompleto = reto.getRetoCompleto();
        this.canchaId = new Cancha(reto.getCanchaId());
        this.equipo1Id = reto.getEquipo1Id();
        this.equipoList = reto.getEquipoList();
        this.retoNivel = reto.getRetoNivel();
    }
    
    public Reto(Long retoId) {
        this.retoId = retoId;
    }

    public Long getRetoId() {
        return retoId;
    }

    public void setRetoId(Long retoId) {
        this.retoId = retoId;
    }

    public Long getRetoHoraIni() {
        return retoHoraIni;
    }

    public void setRetoHoraIni(Long retoHoraIni) {
        this.retoHoraIni = retoHoraIni;
    }

    public Long getRetoHoraFin() {
        return retoHoraFin;
    }

    public void setRetoHoraFin(Long retoHoraFin) {
        this.retoHoraFin = retoHoraFin;
    }

    public Date getRetoFecha() {
        return retoFecha;
    }

    public void setRetoFecha(Date retoFecha) {
        this.retoFecha = retoFecha;
    }

    public String getRetoCompleto() {
        return retoCompleto;
    }

    public void setRetoCompleto(String retoCompleto) {
        this.retoCompleto = retoCompleto;
    }

    @XmlTransient
    public List<Equipo> getEquipoList() {
        return equipoList;
    }

    public void setEquipoList(List<Equipo> equipoList) {
        this.equipoList = equipoList;
    }

    public Cancha getCanchaId() {
        return canchaId;
    }

    public void setCanchaId(Cancha canchaId) {
        this.canchaId = canchaId;
    }

    public Equipo getEquipo1Id() {
        return equipo1Id;
    }

    public void setEquipo1Id(Equipo equipo1Id) {
        this.equipo1Id = equipo1Id;
    }

    public Equipo getEquipo2Id() {
        return equipo2Id;
    }

    public void setEquipo2Id(Equipo equipo2Id) {
        this.equipo2Id = equipo2Id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (retoId != null ? retoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reto)) {
            return false;
        }
        Reto other = (Reto) object;
        if ((this.retoId == null && other.retoId != null) || (this.retoId != null && !this.retoId.equals(other.retoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "canchaspz.model.Reto[ retoId=" + retoId + " ]";
    }

    public Long getRetoNivel() {
        return retoNivel;
    }

    public void setRetoNivel(Long retoNivel) {
        this.retoNivel = retoNivel;
    }

}
