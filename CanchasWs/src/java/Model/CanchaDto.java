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
 * @author mario
 */
@XmlRootElement(name = "CanchaDto")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class CanchaDto {
    //Attributes
    private Long canId;
    private String canNombre;
    private String canDireccion;
    private Integer canCantJugadores;
    private Integer canLatitud;
    private Integer canLongitud;
    private Integer canPrecioDia;
    private Integer canPrecioNoches;
    private Integer canTel;
    private Integer canCierra;
    private Integer canAbre;
    private String canUrl;
    private AdministradorDto admId;
    public List<RetoDto> retoList;
    public List<MatchDto> matchList;
    
    //Constructors
    public CanchaDto() {
        
    }
    
    public CanchaDto(Cancha cancha) {
        if(cancha.getCanId() != null){
            this.canId = cancha.getCanId();
        }
        duplicarInfo(cancha);
    }

    //Methods
    public void duplicarInfo(Cancha cancha){
        this.canNombre = cancha.getCanNombre();
        this.canDireccion = cancha.getCanDireccion();
        this.canCantJugadores = cancha.getCanCantJugadores();
        this.canLatitud = cancha.getCanLatitud();
        this.canLongitud = cancha.getCanLongitud();
        this.canPrecioDia = cancha.getCanPrecioDia();
        this.canPrecioNoches = cancha.getCanPrecioNoches();
        this.canTel = cancha.getCanTel();
        this.canCierra = cancha.getCanCierra();
        this.canAbre = cancha.getCanAbre();
        this.canUrl = cancha.getCanUrl();
    }
    
    public void convertirListaPartidos(List<Match> list){
        this.matchList = new ArrayList<>();
        for(Match match : list){
            MatchDto newM = new MatchDto(match);
            newM.setCanId(this);
            newM.copiarSoloIDEquipos(match);
            matchList.add(newM);
        }
    }
    
    public void convertirListaRetos(List<Reto> list){
        this.retoList = new ArrayList<>();
        for(Reto reto : list){
            RetoDto newR = new RetoDto(reto);
            newR.setCanchaId(this);
            newR.copiarSoloIDEquipos(reto);
            retoList.add(newR);
        }
    }

    //Setters and Getters
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

    public String getCanDireccion() {
        return canDireccion;
    }

    public void setCanDireccion(String canDireccion) {
        this.canDireccion = canDireccion;
    }

    public Integer getCanCantJugadores() {
        return canCantJugadores;
    }

    public void setCanCantJugadores(Integer canCantJugadores) {
        this.canCantJugadores = canCantJugadores;
    }

    public Integer getCanLatitud() {
        return canLatitud;
    }

    public void setCanLatitud(Integer canLatitud) {
        this.canLatitud = canLatitud;
    }

    public Integer getCanLongitud() {
        return canLongitud;
    }

    public void setCanLongitud(Integer canLongitud) {
        this.canLongitud = canLongitud;
    }

    public Integer getCanPrecioDia() {
        return canPrecioDia;
    }

    public void setCanPrecioDia(Integer canPrecioDia) {
        this.canPrecioDia = canPrecioDia;
    }

    public Integer getCanPrecioNoches() {
        return canPrecioNoches;
    }

    public void setCanPrecioNoches(Integer canPrecioNoches) {
        this.canPrecioNoches = canPrecioNoches;
    }

    public Integer getCanTel() {
        return canTel;
    }

    public void setCanTel(Integer canTel) {
        this.canTel = canTel;
    }

    public Integer getCanCierra() {
        return canCierra;
    }

    public void setCanCierra(Integer canCierra) {
        this.canCierra = canCierra;
    }

    public Integer getCanAbre() {
        return canAbre;
    }

    public void setCanAbre(Integer canAbre) {
        this.canAbre = canAbre;
    }

    public String getCanUrl() {
        return canUrl;
    }

    public void setCanUrl(String canUrl) {
        this.canUrl = canUrl;
    }

    public AdministradorDto getAdmId() {
        return admId;
    }

    public void setAdmId(AdministradorDto admId) {
        this.admId = admId;
    }

//    public List<RetoDto> getRetoList() {
//        return retoList;
//    }
//
//    public void setRetoList(List<RetoDto> retoList) {
//        this.retoList = retoList;
//    }
//
//    public List<MatchDto> getMatchList() {
//        return matchList;
//    }
//
//    public void setMatchList(List<MatchDto> matchList) {
//        this.matchList = matchList;
//    }
    
}
