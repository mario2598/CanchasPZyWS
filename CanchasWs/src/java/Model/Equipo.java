/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "EQUIPO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Equipo.findAll", query = "SELECT e FROM Equipo e")
    , @NamedQuery(name = "Equipo.findByEquId", query = "SELECT e FROM Equipo e WHERE e.equId = :equId")
    , @NamedQuery(name = "Equipo.findByEquUsu", query = "SELECT e FROM Equipo e WHERE e.equUsu = :equUsu")
    , @NamedQuery(name = "Equipo.findByEquUsuPass", query = "SELECT e FROM Equipo e WHERE e.equUsu = :equUsu AND e.equPassword = :equPassword")    
    , @NamedQuery(name = "Equipo.findByEquPassword", query = "SELECT e FROM Equipo e WHERE e.equPassword = :equPassword")
    , @NamedQuery(name = "Equipo.findByEquNombre", query = "SELECT e FROM Equipo e WHERE e.equNombre = :equNombre")
    , @NamedQuery(name = "Equipo.findByEquNomJug1", query = "SELECT e FROM Equipo e WHERE e.equNomJug1 = :equNomJug1")
    , @NamedQuery(name = "Equipo.findByEquNomJug2", query = "SELECT e FROM Equipo e WHERE e.equNomJug2 = :equNomJug2")
    , @NamedQuery(name = "Equipo.findByEquUrl", query = "SELECT e FROM Equipo e WHERE e.equUrl = :equUrl")
    , @NamedQuery(name = "Equipo.findByEquTelJug2", query = "SELECT e FROM Equipo e WHERE e.equTelJug2 = :equTelJug2")
    , @NamedQuery(name = "Equipo.findByEquTelJug1", query = "SELECT e FROM Equipo e WHERE e.equTelJug1 = :equTelJug1")
    , @NamedQuery(name = "Equipo.findByEquPts", query = "SELECT e FROM Equipo e WHERE e.equPts = :equPts")})
public class Equipo implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "EQU_ID")
    private Long equId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "EQU_USU")
    private String equUsu;
    @Size(max = 20)
    @Column(name = "EQU_PASSWORD")
    private String equPassword;
    @Size(max = 20)
    @Column(name = "EQU_NOMBRE")
    private String equNombre;
    @Size(max = 20)
    @Column(name = "EQU_NOM_JUG1")
    private String equNomJug1;
    @Size(max = 20)
    @Column(name = "EQU_NOM_JUG2")
    private String equNomJug2;
    @Size(max = 200)
    @Column(name = "EQU_URL")
    private String equUrl;
    @Column(name = "EQU_TEL_JUG2")
    private Integer equTelJug2;
    @Column(name = "EQU_TEL_JUG1")
    private Integer equTelJug1;
    @Column(name = "EQU_PTS")
    private Integer equPts;
    @JoinColumn(name = "RETO_ID", referencedColumnName = "RETO_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Reto retoId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equipo1Id", fetch = FetchType.LAZY)
    private List<Reto> retoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equipo2Id", fetch = FetchType.LAZY)
    private List<Reto> retoList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equId2", fetch = FetchType.LAZY)
    private List<Match> matchList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equId1", fetch = FetchType.LAZY)
    private List<Match> matchList1;

    public Equipo() {
    }

    public Equipo(Long equId) {
        this.equId = equId;
    }
    
    public Equipo(EquipoDto equipoDto){
        if(equipoDto.getEquId()!=null){
            this.equId = equipoDto.getEquId();
        }
        copiarInfo(equipoDto);
    }
    
    public void copiarInfo(EquipoDto equipoDto){
        this.equUsu = equipoDto.getEquUsu();
        this.equPassword = equipoDto.getEquPassword();
        this.equNombre = equipoDto.getEquNombre();
        this.equNomJug1 = equipoDto.getEquNomJug1();
        this.equNomJug2 = equipoDto.getEquNomJug2();
        this.equUrl = equipoDto.getUrl();
        this.equTelJug2 = equipoDto.getEquTelJug1();
        this.equTelJug1 = equipoDto.getEquTelJug2();
        this.equPts = equipoDto.getEquPts();
    }

    public Equipo(Long equId, String equUsu) {
        this.equId = equId;
        this.equUsu = equUsu;
    }
    
    /**
     * 
     * @param list Lista de partidos como local (Lado izquierdo)
     * @param list1 Lista de partidos como visitante (Lado derecho) 
     */
    public void convertirListaPartidos(List<MatchDto> list, List<MatchDto> list1){
        this.matchList = new ArrayList<>();
        this.matchList1 = new ArrayList<>();
        for(MatchDto match : list){
            Match newM = new Match(match);
            newM.copiarSoloIDEquipos(match);
            newM.copiarSoloIdCancha(match);
            matchList.add(newM);
        }
        for(MatchDto match : list1){
            Match newM = new Match(match);
            newM.copiarSoloIDEquipos(match);
            newM.copiarSoloIdCancha(match);
            matchList1.add(newM);
        }
    }
    
    public void convertirListaRetos(List<RetoDto> list){
        this.retoList = new ArrayList<>();
        this.retoList1 = null; //Esta lista de retos siempre estara vacia, una vez aceptado el reto es eliminado de la base de datos
        for(RetoDto reto : list){
            Reto newR = new Reto(reto);
            newR.copiarSoloIDEquipos(reto);
            newR.copiarSoloIdCancha(reto);
            retoList.add(newR);
        }
    }

    public Long getEquId() {
        return equId;
    }

    public void setEquId(Long equId) {
        this.equId = equId;
    }

    public String getEquUsu() {
        return equUsu;
    }

    public void setEquUsu(String equUsu) {
        this.equUsu = equUsu;
    }

    public String getEquPassword() {
        return equPassword;
    }

    public void setEquPassword(String equPassword) {
        this.equPassword = equPassword;
    }

    public String getEquNombre() {
        return equNombre;
    }

    public void setEquNombre(String equNombre) {
        this.equNombre = equNombre;
    }

    public String getEquNomJug1() {
        return equNomJug1;
    }

    public void setEquNomJug1(String equNomJug1) {
        this.equNomJug1 = equNomJug1;
    }

    public String getEquNomJug2() {
        return equNomJug2;
    }

    public void setEquNomJug2(String equNomJug2) {
        this.equNomJug2 = equNomJug2;
    }

    public String getEquUrl() {
        return equUrl;
    }

    public void setEquUrl(String equUrl) {
        this.equUrl = equUrl;
    }

    public Integer getEquTelJug2() {
        return equTelJug2;
    }

    public void setEquTelJug2(Integer equTelJug2) {
        this.equTelJug2 = equTelJug2;
    }

    public Integer getEquTelJug1() {
        return equTelJug1;
    }

    public void setEquTelJug1(Integer equTelJug1) {
        this.equTelJug1 = equTelJug1;
    }

    public Integer getEquPts() {
        return equPts;
    }

    public void setEquPts(Integer equPts) {
        this.equPts = equPts;
    }

    public Reto getRetoId() {
        return retoId;
    }

    public void setRetoId(Reto retoId) {
        this.retoId = retoId;
    }

    @XmlTransient
    public List<Reto> getRetoList() {
        return retoList;
    }

    public void setRetoList(List<Reto> retoList) {
        this.retoList = retoList;
    }

    @XmlTransient
    public List<Reto> getRetoList1() {
        return retoList1;
    }

    public void setRetoList1(List<Reto> retoList1) {
        this.retoList1 = retoList1;
    }

    @XmlTransient
    public List<Match> getMatchList() {
        return matchList;
    }

    public void setMatchList(List<Match> matchList) {
        this.matchList = matchList;
    }

    @XmlTransient
    public List<Match> getMatchList1() {
        return matchList1;
    }

    public void setMatchList1(List<Match> matchList1) {
        this.matchList1 = matchList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (equId != null ? equId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Equipo)) {
            return false;
        }
        Equipo other = (Equipo) object;
        if ((this.equId == null && other.equId != null) || (this.equId != null && !this.equId.equals(other.equId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Equipo[ equId=" + equId + " ]";
    }
    
}
