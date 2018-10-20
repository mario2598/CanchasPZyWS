/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Chris
 */
@XmlRootElement(name = "RetoDto")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class RetoDto {
    //Attributes
    public Long retoId;
    public Integer retoHoraIni;
    public Integer retoHoraFin;
    public Date retoFecha;
    public String retoCompleto;
    public Integer retoNivel;
    public CanchaDto canchaId;
    public EquipoDto equipo1Id;
    public EquipoDto equipo2Id;
    
    //Constructors
    public RetoDto() {
        
    }
    
    public RetoDto(Reto reto) {
        if(reto.getRetoId() != null){
            this.retoId = reto.getRetoId();
        }
        duplicarInfo(reto);
    }
    
    //Methods
    public void duplicarInfo(Reto reto){
        this.retoHoraIni = reto.getRetoHoraIni();
        this.retoHoraFin = reto.getRetoHoraFin();
        this.retoFecha = reto.getRetoFecha();
        this.retoCompleto = reto.getRetoCompleto();
        this.retoNivel = reto.getRetoNivel();
    }
    
    public void copiarSoloIDEquipos(Reto match){
        this.equipo1Id = new EquipoDto();
        this.equipo1Id.equId = (match.getEquipo1Id().getEquId());
        this.equipo2Id = new EquipoDto();
        this.equipo2Id.equId = (match.getEquipo2Id().getEquId());
    }
    
    public void copiarSoloIdCancha(Reto reto){
        this.canchaId = new CanchaDto();
        this.canchaId.canId = (reto.getCanchaId().getCanId());
    }
    
}
