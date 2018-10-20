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
    private Long retoId;
    private Integer retoHoraIni;
    private Integer retoHoraFin;
    private Date retoFecha;
    private String retoCompleto;
    private Integer retoNivel;
    private CanchaDto canchaId;
    private EquipoDto equipo1Id;
    private EquipoDto equipo2Id;
    
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
        this.equipo1Id.setEquId(match.getEquipo1Id().getEquId());
        this.equipo2Id = new EquipoDto();
        this.equipo2Id.setEquId(match.getEquipo2Id().getEquId());
    }
    
    public void copiarSoloIdCancha(Reto reto){
        this.canchaId = new CanchaDto();
        this.canchaId.setCanId(reto.getCanchaId().getCanId());
    }
    
    //Getteres and Setters
    public Long getRetoId() {
        return retoId;
    }

    public void setRetoId(Long retoId) {
        this.retoId = retoId;
    }

    public Integer getRetoHoraIni() {
        return retoHoraIni;
    }

    public void setRetoHoraIni(Integer retoHoraIni) {
        this.retoHoraIni = retoHoraIni;
    }

    public Integer getRetoHoraFin() {
        return retoHoraFin;
    }

    public void setRetoHoraFin(Integer retoHoraFin) {
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

    public Integer getRetoNivel() {
        return retoNivel;
    }

    public void setRetoNivel(Integer retoNivel) {
        this.retoNivel = retoNivel;
    }

    public CanchaDto getCanchaId() {
        return canchaId;
    }

    public void setCanchaId(CanchaDto canchaId) {
        this.canchaId = canchaId;
    }

    public EquipoDto getEquipo1Id() {
        return equipo1Id;
    }

    public void setEquipo1Id(EquipoDto equipo1Id) {
        this.equipo1Id = equipo1Id;
    }

    public EquipoDto getEquipo2Id() {
        return equipo2Id;
    }

    public void setEquipo2Id(EquipoDto equipo2Id) {
        this.equipo2Id = equipo2Id;
    }
    
    
}
