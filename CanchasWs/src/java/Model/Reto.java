/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @NamedQuery(name = "Reto.findAll", query = "SELECT r FROM Reto r")
    , @NamedQuery(name = "Reto.findByRetoId", query = "SELECT r FROM Reto r WHERE r.retoId = :retoId")
    , @NamedQuery(name = "Reto.findByRetoHoraIni", query = "SELECT r FROM Reto r WHERE r.retoHoraIni = :retoHoraIni")
    , @NamedQuery(name = "Reto.findByRetoHoraFin", query = "SELECT r FROM Reto r WHERE r.retoHoraFin = :retoHoraFin")
    , @NamedQuery(name = "Reto.findByRetoFecha", query = "SELECT r FROM Reto r WHERE r.retoFecha = :retoFecha")
    , @NamedQuery(name = "Reto.findByRetoCompleto", query = "SELECT r FROM Reto r WHERE r.retoCompleto = :retoCompleto")
    , @NamedQuery(name = "Reto.findByRetoNivel", query = "SELECT r FROM Reto r WHERE r.retoNivel = :retoNivel")})
public class Reto implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "RETO_ID")
    private BigDecimal retoId;
    @Column(name = "RETO_HORA_INI")
    private BigInteger retoHoraIni;
    @Column(name = "RETO_HORA_FIN")
    private BigInteger retoHoraFin;
    @Column(name = "RETO_FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date retoFecha;
    @Size(max = 1)
    @Column(name = "RETO_COMPLETO")
    private String retoCompleto;
    @Column(name = "RETO_NIVEL")
    private BigInteger retoNivel;
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

    public Reto(BigDecimal retoId) {
        this.retoId = retoId;
    }

    public BigDecimal getRetoId() {
        return retoId;
    }

    public void setRetoId(BigDecimal retoId) {
        this.retoId = retoId;
    }

    public BigInteger getRetoHoraIni() {
        return retoHoraIni;
    }

    public void setRetoHoraIni(BigInteger retoHoraIni) {
        this.retoHoraIni = retoHoraIni;
    }

    public BigInteger getRetoHoraFin() {
        return retoHoraFin;
    }

    public void setRetoHoraFin(BigInteger retoHoraFin) {
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

    public BigInteger getRetoNivel() {
        return retoNivel;
    }

    public void setRetoNivel(BigInteger retoNivel) {
        this.retoNivel = retoNivel;
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
        return "Model.Reto[ retoId=" + retoId + " ]";
    }
    
}
