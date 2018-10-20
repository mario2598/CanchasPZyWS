/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mario
 */
@Entity
@Table(name = "MATCH")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Match.findAll", query = "SELECT m FROM Match m")
    , @NamedQuery(name = "Match.findByMatId", query = "SELECT m FROM Match m WHERE m.matId = :matId")
    , @NamedQuery(name = "Match.findByMatDate", query = "SELECT m FROM Match m WHERE m.matDate = :matDate")
    , @NamedQuery(name = "Match.findByMatHora", query = "SELECT m FROM Match m WHERE m.matHora = :matHora")
    , @NamedQuery(name = "Match.findByMatDisputado", query = "SELECT m FROM Match m WHERE m.matDisputado = :matDisputado")
    , @NamedQuery(name = "Match.findByMatMarcador1", query = "SELECT m FROM Match m WHERE m.matMarcador1 = :matMarcador1")
    , @NamedQuery(name = "Match.findByMatMarcador2", query = "SELECT m FROM Match m WHERE m.matMarcador2 = :matMarcador2")
    , @NamedQuery(name = "Match.findByMatResultado", query = "SELECT m FROM Match m WHERE m.matResultado = :matResultado")
    , @NamedQuery(name = "Match.findByMatCobro", query = "SELECT m FROM Match m WHERE m.matCobro = :matCobro")
    , @NamedQuery(name = "Match.findByEquWin", query = "SELECT m FROM Match m WHERE m.equWin = :equWin")
    , @NamedQuery(name = "Match.findByCanId", query = "SELECT m FROM Match m WHERE m.canId = :canId")})
public class Match implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "MAT_ID")
    private Long matId;
    @Column(name = "MAT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date matDate;
    @Column(name = "MAT_HORA")
    private Integer matHora;
    @Size(max = 1)
    @Column(name = "MAT_DISPUTADO")
    private String matDisputado;
    @Column(name = "MAT_MARCADOR1")
    private Integer matMarcador1;
    @Column(name = "MAT_MARCADOR2")
    private Integer matMarcador2;
    @Column(name = "MAT_RESULTADO")
    private Integer matResultado;
    @Column(name = "MAT_COBRO")
    private Integer matCobro;
    @Column(name = "EQU_WIN")
    private Integer equWin;
    @JoinColumn(name = "CAN_ID", referencedColumnName = "CAN_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Cancha canId;
    @JoinColumn(name = "EQU_ID2", referencedColumnName = "EQU_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Equipo equId2;
    @JoinColumn(name = "EQU_ID1", referencedColumnName = "EQU_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Equipo equId1;

    public Match() {
        
    }

    public Match(Long matId) {
        this.matId = matId;
    }
    
    public Match(MatchDto matchDto){
        if(matchDto.matId!=null){
            this.matId = matchDto.matId;
        }
        copiarInfo(matchDto);
    }
    
    public void copiarInfo(MatchDto matchDto){
        this.matDate = matchDto.matDate;
        this.matHora = matchDto.matHora;
        this.matDisputado = matchDto.matDisputado;
        this.matMarcador1 = matchDto.matMarcador1;
        this.matMarcador2 = matchDto.matMarcador2;
        this.matResultado = matchDto.matResultado;
        this.matCobro = matchDto.matCobro;
        this.equWin = matchDto.equWin;
    }
    
    public void copiarSoloIDEquipos(MatchDto match){
        this.equId1 = new Equipo();
        this.equId1.setEquId(match.equId1.equId);
        this.equId2 = new Equipo();
        this.equId2.setEquId(match.equId2.equId);
    }
    
    public void copiarSoloIdCancha(MatchDto match){
        this.canId = new Cancha();
        this.canId.setCanId(match.canId.canId);
    }

    public Long getMatId() {
        return matId;
    }

    public void setMatId(Long matId) {
        this.matId = matId;
    }

    public Date getMatDate() {
        return matDate;
    }

    public void setMatDate(Date matDate) {
        this.matDate = matDate;
    }

    public Integer getMatHora() {
        return matHora;
    }

    public void setMatHora(Integer matHora) {
        this.matHora = matHora;
    }

    public String getMatDisputado() {
        return matDisputado;
    }

    public void setMatDisputado(String matDisputado) {
        this.matDisputado = matDisputado;
    }

    public Integer getMatMarcador1() {
        return matMarcador1;
    }

    public void setMatMarcador1(Integer matMarcador1) {
        this.matMarcador1 = matMarcador1;
    }

    public Integer getMatMarcador2() {
        return matMarcador2;
    }

    public void setMatMarcador2(Integer matMarcador2) {
        this.matMarcador2 = matMarcador2;
    }

    public Integer getMatResultado() {
        return matResultado;
    }

    public void setMatResultado(Integer matResultado) {
        this.matResultado = matResultado;
    }

    public Integer getMatCobro() {
        return matCobro;
    }

    public void setMatCobro(Integer matCobro) {
        this.matCobro = matCobro;
    }

    public Integer getEquWin() {
        return equWin;
    }

    public void setEquWin(Integer equWin) {
        this.equWin = equWin;
    }

    public Cancha getCanId() {
        return canId;
    }

    public void setCanId(Cancha canId) {
        this.canId = canId;
    }

    public Equipo getEquId2() {
        return equId2;
    }

    public void setEquId2(Equipo equId2) {
        this.equId2 = equId2;
    }

    public Equipo getEquId1() {
        return equId1;
    }

    public void setEquId1(Equipo equId1) {
        this.equId1 = equId1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (matId != null ? matId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Match)) {
            return false;
        }
        Match other = (Match) object;
        if ((this.matId == null && other.matId != null) || (this.matId != null && !this.matId.equals(other.matId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Match[ matId=" + matId + " ]";
    }
    
}
