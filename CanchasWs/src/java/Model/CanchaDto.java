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
    public Long canId;
    public String canNombre;
    public String canDireccion;
    public Integer canCantJugadores;
    public Integer canLatitud;
    public Integer canLongitud;
    public Integer canPrecioDia;
    public Integer canPrecioNoches;
    public Integer canTel;
    public Integer canCierra;
    public Integer canAbre;
    public String canUrl;
    public AdministradorDto admId;
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
            newM.canId = (this);
            newM.copiarSoloIDEquipos(match);
            matchList.add(newM);
        }
    }
    
    public void convertirListaRetos(List<Reto> list){
        this.retoList = new ArrayList<>();
        for(Reto reto : list){
            RetoDto newR = new RetoDto(reto);
            newR.canchaId = (this);
            newR.copiarSoloIDEquipos(reto);
            retoList.add(newR);
        }
    }
    
    public void copiarSoloIdAdmin(Cancha cancha){
        this.admId = new AdministradorDto();
        this.admId.adminId = cancha.getCanId();
    }
    
}
