/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Chris
 */
@XmlRootElement(name = "EquipoDto")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class EquipoDto {
    //Attributes
    public Long equId;
    public String equUsu;
    public String equPassword;
    public String equNombre;
    public String equNomJug1;
    public String equNomJug2;
    public Integer equTelJug2;
    public Integer equTelJug1;
    public Integer equPts;
    public String url;
    public List<RetoDto> retoList;
    public List<RetoDto> retoList1;
    public List<MatchDto> matchList;
    public List<MatchDto> matchList1;

    //Constructors
    public EquipoDto() {
        
    }
    
    public EquipoDto(Equipo team) {
        if(team.getEquId()!=null){
            this.equId = team.getEquId();
        }
        duplicarInfo(team);
    }
    
    //Methods
    public void duplicarInfo(Equipo team){
        this.equUsu = team.getEquUsu();
        this.equPassword = team.getEquPassword();
        this.equNombre = team.getEquNombre();
        this.equNomJug1 = team.getEquNomJug1();
        this.equNomJug2 = team.getEquNomJug2();
        this.equTelJug2 = team.getEquTelJug1();
        this.equTelJug1 = team.getEquTelJug2();
        this.equPts = team.getEquPts();
        this.url = team.getEquUrl();
    }
    
    /**
     * 
     * @param list Lista de partidos como local (Lado izquierdo)
     * @param list1 Lista de partidos como visitante (Lado derecho) 
     */
    public void convertirListaPartidos(List<Match> list, List<Match> list1){
        this.matchList = new ArrayList<>();
        this.matchList1 = new ArrayList<>();
        for(Match match : list){
            MatchDto newM = new MatchDto(match);
            newM.copiarSoloIDEquipos(match);
            newM.copiarSoloIdCancha(match);
            matchList.add(newM);
        }
        for(Match match : list1){
            MatchDto newM = new MatchDto(match);
            newM.copiarSoloIDEquipos(match);
            newM.copiarSoloIdCancha(match);
            matchList1.add(newM);
        }
    }
    
    public void convertirListaRetos(List<Reto> list){
        this.retoList = new ArrayList<>();
        this.retoList1 = null; //Esta lista de retos siempre estara vacia, una vez aceptado el reto es eliminado de la base de datos
        for(Reto reto : list){
            RetoDto newR = new RetoDto(reto);
            newR.copiarSoloIDEquipos(reto);
            newR.copiarSoloIdCancha(reto);
            retoList.add(newR);
        }
    }
    
    //Getters and Setters
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<RetoDto> getRetoList() {
        return retoList;
    }

    public void setRetoList(List<RetoDto> retoList) {
        this.retoList = retoList;
    }

    public List<RetoDto> getRetoList1() {
        return retoList1;
    }

    public void setRetoList1(List<RetoDto> retoList1) {
        this.retoList1 = retoList1;
    }

    public List<MatchDto> getMatchList() {
        return matchList;
    }

    public void setMatchList(List<MatchDto> matchList) {
        this.matchList = matchList;
    }

    public List<MatchDto> getMatchList1() {
        return matchList1;
    }

    public void setMatchList1(List<MatchDto> matchList1) {
        this.matchList1 = matchList1;
    }
    
}
