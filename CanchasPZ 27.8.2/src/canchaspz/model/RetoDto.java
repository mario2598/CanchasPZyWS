/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canchaspz.model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author robri
 */
public class RetoDto {
    public SimpleStringProperty retoId;
    public SimpleStringProperty retoHoraIni;
    public SimpleStringProperty retoHoraFin;
    public SimpleStringProperty nivel;
    public ObjectProperty<LocalDate> retoFecha;
    public SimpleBooleanProperty retoCompleto;
    public CanchaDto canchaId;
    public Equipo equipo1Id;
    public EquipoDto equipo2Id;
    public List<Equipo> equipoList;

    
    
    public RetoDto(){
        this.retoId = new SimpleStringProperty();
        this.nivel = new SimpleStringProperty();
        this.retoHoraIni = new SimpleStringProperty();
        this.retoHoraFin = new SimpleStringProperty();
        this.retoFecha = new SimpleObjectProperty();
        this.retoCompleto = new SimpleBooleanProperty(false);
        this.canchaId = null;
        this.equipo1Id = null;
        this.equipo2Id = null;
        this.equipoList = new ArrayList<>();
    }
    
    public RetoDto(Reto challenge){
        this();
        if(challenge.getRetoId() != null){
          this.retoId.set(challenge.getRetoId().toString());  
        }
        this.retoHoraIni.set(challenge.getRetoHoraIni().toString());
        this.retoHoraFin.set(challenge.getRetoHoraFin().toString()); 
        this.retoFecha.set(challenge.getRetoFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        this.retoCompleto.set(challenge.getRetoCompleto().equalsIgnoreCase("t"));
        this.canchaId = new CanchaDto(challenge.getCanchaId());
        this.equipo1Id = challenge.getEquipo1Id();
        this.equipoList =challenge.getEquipoList();
        this.nivel.set(challenge.getRetoNivel().toString());
    }
    
    public Long getRetoId() {
        return Long.valueOf(this.retoId.get());
    }

    public void setRetoId(String retoId) {
        this.retoId.set(retoId);
    }
    
    public Long getRetoNivel() {
        return Long.valueOf(this.nivel.get());
    }

    public void setRetoNivel(String nivel) {
        this.nivel.set(nivel);
    }

    public Long getRetoHoraIni() {
        return Long.valueOf(this.retoHoraIni.getValue());
    }

    public void setRetoHoraIni(Long retoHoraIni) {
        this.retoHoraIni.set(retoHoraIni.toString());
    }

    public Long getRetoHoraFin() {
        return Long.valueOf(this.retoHoraFin.getValue());
    }

    public void setRetoHoraFin(Long retoHoraFin) {
        this.retoHoraFin.set(retoHoraFin.toString());
    }

    public LocalDate getRetoFecha() {
        return this.retoFecha.getValue();
    }

    public void setRetoFecha(LocalDate retoFecha) {
        this.retoFecha.set(retoFecha);
    }

    public String getRetoCompleto() {
        return this.retoCompleto.getValue() ? "t":"f";
    }

    public void setRetoCompleto(String retoCompleto) {
        this.retoCompleto.set(retoCompleto.equalsIgnoreCase("T"));
    }

    public CanchaDto getCanchaId() {
        return canchaId;
    }

    public void setCanchaId(CanchaDto canchaId) {
        this.canchaId = canchaId;
    }

    public Equipo getEquipo1Id() {
        return equipo1Id;
    }

    public void setEquipo1Id(Equipo equipo1Id) {
        this.equipo1Id = equipo1Id;
    }

    public EquipoDto getEquipo2Id() {
        return equipo2Id;
    }

    public void setEquipo2Id(EquipoDto equipo2Id) {
        this.equipo2Id = equipo2Id;
    }

    public List<Equipo> getEquipoList() {
        return equipoList;
    }

    public void setEquipoList(List<Equipo> equipoList) {
        this.equipoList = equipoList;
    }
    
    
}
