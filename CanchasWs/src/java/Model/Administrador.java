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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mario
 */
@Entity
@Table(name = "ADMINISTRADOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Administrador.findAll", query = "SELECT a FROM Administrador a")
    , @NamedQuery(name = "Administrador.findByAdmId", query = "SELECT a FROM Administrador a WHERE a.admId = :admId")
    , @NamedQuery(name = "Administrador.findByAdmUsu", query = "SELECT a FROM Administrador a WHERE a.admUsu = :admUsu")
    , @NamedQuery(name = "Administrador.findByAdmPassword", query = "SELECT a FROM Administrador a WHERE a.admPassword = :admPassword AND a.admUsu = :admUsu")})
public class Administrador implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
   // @NotNull
    @Column(name = "ADM_ID")
    private Long admId;
    @Basic(optional = false)
 //   @NotNull
  //  @Size(min = 1, max = 30)
    @Column(name = "ADM_USU")
    private String admUsu;
    @Basic(optional = false)
 //   @NotNull
  //  @Size(min = 1, max = 30)
    @Column(name = "ADM_PASSWORD")
    private String admPassword;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "admId", fetch = FetchType.LAZY)
    private List<Cancha> canchaList;

    public Administrador() {
    }

    public Administrador(Long admId) {
        this.admId = admId;
    }

    public Administrador(Long admId, String admUsu, String admPassword) {
        this.admId = admId;
        this.admUsu = admUsu;
        this.admPassword = admPassword;
    }
    
    public Administrador(AdministradorDto adminDto){
        if(adminDto.adminId != null){
            this.admId = adminDto.adminId;
        }
        copiarInfo(adminDto);
    }
    
    public void copiarInfo(AdministradorDto adminDto){
        this.admUsu = adminDto.adminUsu;
        this.admPassword = adminDto.adminPassword;
    }
    
    public void convertirListaCanchas(List<CanchaDto> list){
        this.canchaList = new ArrayList<>();
        for(CanchaDto cancha : list){
            Cancha newC = new Cancha(cancha);
            newC.setAdmId(this);
            newC.convertirListaPartidos(cancha.matchList);
            newC.convertirListaRetos(cancha.retoList);
            canchaList.add(newC);
        }
    }

    public Long getAdmId() {
        return admId;
    }

    public void setAdmId(Long admId) {
        this.admId = admId;
    }

    public String getAdmUsu() {
        return admUsu;
    }

    public void setAdmUsu(String admUsu) {
        this.admUsu = admUsu;
    }

    public String getAdmPassword() {
        return admPassword;
    }

    public void setAdmPassword(String admPassword) {
        this.admPassword = admPassword;
    }

    @XmlTransient
    public List<Cancha> getCanchaList() {
        return canchaList;
    }
    public void setCanchaList(List<Cancha> canchaList) {
        this.canchaList = canchaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (admId != null ? admId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Administrador)) {
            return false;
        }
        Administrador other = (Administrador) object;
        if((this.admId == null && other.admId != null) || (this.admId != null && !this.admId.equals(other.admId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Administrador[ admId=" + admId + " ]";
    }
    
}
