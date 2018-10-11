/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canchaspz.model;

import canchaspz.util.DateUtil;
import java.time.LocalDate;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Chris
 */
public class MatchDto {
    //Attributes
    public SimpleStringProperty matId;
    public SimpleObjectProperty<LocalDate> matDate;
    public SimpleStringProperty matDisputados;
    public SimpleStringProperty matHora;
    public SimpleStringProperty matMarcador1;
    public SimpleStringProperty matMarcador2;
    public SimpleStringProperty matResultado;
    public SimpleStringProperty matCobro;
    public SimpleStringProperty equWin;
    public Cancha canId;
    public Equipo team1;
    public Equipo team2;
    
    //Constructors
    public MatchDto() {
        matId = new SimpleStringProperty();
        matDate = new SimpleObjectProperty<>();
        matDisputados = new SimpleStringProperty();
        matHora = new SimpleStringProperty();
        matMarcador1 = new SimpleStringProperty();
        matMarcador2 = new SimpleStringProperty();
        matResultado = new SimpleStringProperty();
        matCobro = new SimpleStringProperty();
        equWin = new SimpleStringProperty();
        canId = null;
        team1 = null;
        team2 = null;
    }
    
    public MatchDto(Match match){
        this();
        if(match.getMatId() != null){
            matId.set(match.getMatId().toString());
        }
        matDate.set(DateUtil.Date2LocalDate(match.getMatDate()));
        matDisputados.set(match.getMatDisputado());
        matHora.set(String.valueOf(match.getMatHora()));
        matMarcador1.set(String.valueOf(match.getMatMarcador1()));
        matMarcador2.set(String.valueOf(match.getMatMarcador2()));
        matResultado.set(String.valueOf(match.getMatResultado()));
        matCobro.set(String.valueOf(match.getMatCobro()));
        equWin.set(String.valueOf(match.getEquWin()));
        canId  = match.getCanId();
        team1 = match.getEquId1();
        team2 = match.getEquId2();
    }

    //Methods
    
    //Setters and Getters
    public Long getMatId() {
        if(matId.getValue()!=null){
            return Long.valueOf(matId.getValue());
        } else {
            return null;
        }
    }

    public void setMatId(Long matId) {
        this.matId.set(matId.toString());
    }

    public LocalDate getMatDate() {
        return matDate.get();
    }

    public void setMatDate(LocalDate matDate) {
        this.matDate.set(matDate);
    }

    public boolean getMatDisputados() {
        return matDisputados.get().equalsIgnoreCase("S");
    }

    public void setMatDisputados(boolean matDisputados) {
        this.matDisputados.set(matDisputados ? "S":"N");
    }

    public Long getMatHora() {
        return Long.valueOf(this.matHora.get());
    }

    public void setMatHora(Long matHora) {
        this.matHora.set(String.valueOf(matHora));
    }

    public Long getMatMarcador1() {
        return Long.valueOf(matMarcador1.get());
    }

    public void setMatMarcador1(Long matMarcador1) {
        this.matMarcador1.set(String.valueOf(matMarcador1));
    }

    public Long getMatMarcador2() {
        return Long.valueOf(matMarcador2.get());
    }

    public void setMatMarcador2(Long matMarcador2) {
        this.matMarcador2.set(String.valueOf(matMarcador2));
    }

    public Long getMatResultado() {
        return Long.valueOf(matResultado.get());
    }

    public void setMatResultado(Long matResultado) {
        this.matResultado.set(String.valueOf(matResultado));
    }

    public Long getMatCobro() {
        return Long.valueOf(matCobro.get());
    }

    public void setMatCobro(Long matCobro) {
        this.matCobro.set(String.valueOf(matCobro));
    }

    public Integer getEquWin() {
        return Integer.valueOf(this.equWin.get());
    }

    public void setEquWin(Integer equWin) {
        this.equWin.set(String.valueOf(equWin));
    }

    public Cancha getCanId() {
        return canId;
    }

    public void setCanId(Cancha canId) {
        this.canId = canId;
    }

    public Equipo getTeam1() {
        return team1;
    }

    public void setTeam1(Equipo team1) {
        this.team1 = team1;
    }

    public Equipo getTeam2() {
        return team2;
    }

    public void setTeam2(Equipo team2) {
        this.team2 = team2;
    }
    
}
