/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canchaspz.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author mario
 */
public class EquipoDto {
    public SimpleStringProperty equId;
    public SimpleStringProperty equUsu;
    public SimpleStringProperty equPassword;
    public SimpleStringProperty equNombre;
    public SimpleStringProperty equNomJug1;
    public SimpleStringProperty equNomJug2;
    public SimpleStringProperty equTelJug2;
    public SimpleStringProperty equTelJug1;
    public SimpleStringProperty equPts;
    public SimpleStringProperty url;
    public List<Reto> retoList;
    public List<Reto> retoList1;
    public List<Match> matchList;
    public List<Match> matchList1;
    public RetoDto retoId;
    
    public EquipoDto() {
        this.equId = new SimpleStringProperty();
        this.equUsu =  new SimpleStringProperty();
        this.equPassword =  new SimpleStringProperty();
        this.equNombre =  new SimpleStringProperty();
        this.equNomJug1 =  new SimpleStringProperty();
        this.equNomJug2 =  new SimpleStringProperty();
        this.equTelJug2 =  new SimpleStringProperty();
        this.equTelJug1 =  new SimpleStringProperty();
        this.equPts = new SimpleStringProperty();
        this.url = new SimpleStringProperty();
        this.retoList = new ArrayList<>();
        this.retoList1 = new ArrayList<>();
        this.matchList = new ArrayList<>();
        this.matchList1 = new ArrayList<>();
        this.retoId = null;
    }
    
    public EquipoDto(String nom) {
        this();
        this.equNombre.set(nom);
    }
    
    public EquipoDto(Equipo equ) {
        this();
        this.equId.set(equ.getEquId().toString());
        this.equPts.set(equ.getEquPts().toString());
        this.equUsu.set(equ.getEquUsu());
        this.equPassword.set(equ.getEquPassword());
        this.equNombre.set(equ.getEquNombre());
        this.equNomJug1.set(equ.getEquNomJug1());
        this.equNomJug2.set(equ.getEquNomJug2());
        this.equTelJug2.set(equ.getEquTelJug2().toString());
        this.equTelJug1.set(equ.getEquTelJug1().toString());
        this.url.set(equ.getEquUrl());
        this.retoList = equ.getRetoList();
        this.retoList1 = equ.getRetoList1();
        this.matchList.addAll(equ.getMatchList());
        this.matchList1.addAll(equ.getMatchList1());

        
    }
    
     public EquipoDto(EquipoDto equ) {
        this();
        this.equId.set(equ.getEquId().toString());
        this.equPts.set(equ.getEquPts().toString());
        this.equUsu.set(equ.getEquUsu());
        this.equPassword.set(equ.getEquPassword());
        this.equNombre.set(equ.getEquNombre());
        this.equNomJug1.set(equ.getEquNomJug1());
        this.equNomJug2.set(equ.getEquNomJug2());
        this.equTelJug2.set(equ.getEquTelJug2().toString());
        this.equTelJug1.set(equ.getEquTelJug1().toString());
        this.url.set(equ.getUrl());
        this.retoList = equ.getRetoList();
        this.retoList1 = equ.getRetoList1();
        this.matchList.addAll(equ.getMatchList());
        this.matchList1.addAll(equ.getMatchList1());
 
        
    }

    public void convertList(List<Reto> list,EquipoDto equ){
         RetoDto retoDto;
         for(Reto reto : list){
             retoDto = new RetoDto(reto);
            // retoDto.setEquipo1Id(equ);
          //   this.retoList.add(retoDto);
         }
    }

    /**
     * invoca a las funciones que calculan la información necesaria del equipo y las pone en un HashMap
     * @return Información en HashMap:
     * "played" = cantidad de partidos jugados, "points" = puntos totales ganados, "avg" = rendimiento del equipo
     */
    public HashMap getHistoryInfo(){
        HashMap<String,Integer> info=new HashMap<String,Integer>();
        info.put("played", getPlayedMatchs());
        info.put("won", getWonGames());
        info.put("draw", getDrawGames());
        info.put("loose", getLooseGames());
        info.put("points", ((getWonGames()*3))+getDrawGames());
        info.put("avg", getAvg());
        
        return info;
    }

    
    /**
     * invoca a las funciones que calculan la información necesaria del equipo y las pone en un HashMap
     * @return Información en HashMap:
     * "played" = cantidad de partidos jugados, "points" = puntos totales ganados, "avg" = rendimiento del equipo
     */
    public HashMap getVisitHistoryInfo(){
        HashMap<String,Integer> info=new HashMap<String,Integer>();
        info.put("played", getVisitPlayedMatchs());
        info.put("won", getVisitWonGames());
        info.put("draw", getVisitDrawGames());
        info.put("loose", getVisitLooseGames());
        info.put("points", ((getVisitWonGames()*3))+getVisitDrawGames());
        info.put("avg", getVisitAvg());
        
        return info;
    }
    
    /**
     * invoca a las funciones que calculan la información necesaria del equipo y las pone en un HashMap
     * @return Información en HashMap:
     * "played" = cantidad de partidos jugados, "points" = puntos totales ganados, "avg" = rendimiento del equipo
     */
    public HashMap getLocalHistoryInfo(){
        HashMap<String,Integer> info=new HashMap<>();
        info.put("played", getLocalPlayedMatchs());
        info.put("won", getLocalWonGames());
        info.put("draw", getLocalDrawGames());
        info.put("loose", getLocalLooseGames());
        info.put("points", ((getLocalWonGames()*3))+getLocalDrawGames());
        info.put("avg", getLocalAvg());
        
        return info;
    }
    
    /**
     * cuenta cuantos partidos ha jugado este equipo
     * @return played cantidad de veces que ha jugado
     */
    public Integer getPlayedMatchs(){
        Integer played = 0;
        played = this.matchList.stream().filter((match) -> ("s".equals(match.getMatDisputado()))).map((_item) -> 1).reduce(played, (accumulator, _item) -> accumulator + _item);
        played = this.matchList1.stream().filter((match) -> ("s".equals(match.getMatDisputado()))).map((_item) -> 1).reduce(played, (accumulator, _item) -> accumulator + _item);
        return played;
    }
    
    /**
     * cuenta cuantos partidos ha jugado este equipo como local
     * @return played cantidad de veces que ha jugado
     */
    public Integer getLocalPlayedMatchs(){
        Integer played = 0;
        played = this.matchList1.stream().filter((match) -> ("s".equals(match.getMatDisputado()))).map((_item) -> 1).reduce(played, (accumulator, _item) -> accumulator + _item);
        return played;
    }
    
    /**
     * cuenta cuantos partidos ha jugado este equipo como visitante
     * @return played cantidad de veces que ha jugado
     */
    public Integer getVisitPlayedMatchs(){
        Integer played = 0;
        played = this.matchList1.stream().filter((match) -> ("s".equals(match.getMatDisputado()))).map((_item) -> 1).reduce(played, (accumulator, _item) -> accumulator + _item);
        return played;
    }
    
    /**
     * cuenta las veces que el ganador haya sido este equipo
     * @return won cantidad de veces que ha ganado
     */
    public Integer getWonGames(){
        Integer won = 0;
        won = this.matchList.stream().filter((match) -> ("s".equals(match.getMatDisputado())&&match.getMatResultado()==2)).map((_item) -> 1).reduce(won, Integer::sum);
        won += this.matchList1.stream().filter((match) -> ("s".equals(match.getMatDisputado())&&match.getMatResultado()==1)).map((_item) -> 1).reduce(won, Integer::sum);
        return won;
    }
    
    /**
     * cuenta las veces que el ganador haya sido este equipo como local
     * @return won cantidad de veces que ha ganado
     */
    public Integer getLocalWonGames(){
        Integer won = 0;
        won = this.matchList1.stream().filter((match) -> ("s".equals(match.getMatDisputado())&&match.getMatResultado()==1)).map((_item) -> 1).reduce(won, Integer::sum);
        return won;
    }
    
    /**
     * cuenta las veces que el ganador haya sido este equipo como visitante
     * @return won cantidad de veces que ha ganado
     */
    public Integer getVisitWonGames(){
        Integer won = 0;
        won = this.matchList.stream().filter((match) -> ("s".equals(match.getMatDisputado())&&match.getMatResultado()==2)).map((_item) -> 1).reduce(won, Integer::sum);
        return won;
    }
    
    /**
     * cuenta las veces que el perdedor haya sido este equipo
     * @return won cantidad de veces que ha ganado
     */
    public Integer getLooseGames(){
        Integer loose = 0;
        //loose = this.matchList.stream().filter((match) -> ("s".equals(match.getMatDisputado())&&match.getMatResultado()==1)).map((_item) -> 1).reduce(loose, Integer::sum);
        //loose += this.matchList1.stream().filter((match) -> ("s".equals(match.getMatDisputado())&&match.getMatResultado()==2)).map((_item) -> 1).reduce(loose, Integer::sum);
        loose = this.matchList.stream().filter((m) -> ("s".equals(m.getMatDisputado())&&m.getMatResultado()==1)).map((_item) -> 1).reduce(loose, Integer::sum);
        loose += this.matchList1.stream().filter((m) -> ("s".equals(m.getMatDisputado())&&m.getMatResultado()==2)).map((_item) -> 1).reduce(loose, Integer::sum);
        System.out.println("juegos perdidos: "+loose.toString());
        return loose;
    }
    
    /**
     * cuenta las veces que el perdedor haya sido este equipo
     * @return won cantidad de veces que ha ganado
     */
    public Integer getLocalLooseGames(){
        Integer loose = 0;
        //loose = this.matchList.stream().filter((match) -> ("s".equals(match.getMatDisputado())&&match.getMatResultado()==1)).map((_item) -> 1).reduce(loose, Integer::sum);
        //loose += this.matchList1.stream().filter((match) -> ("s".equals(match.getMatDisputado())&&match.getMatResultado()==2)).map((_item) -> 1).reduce(loose, Integer::sum);
        loose = this.matchList1.stream().filter((m) -> ("s".equals(m.getMatDisputado())&&m.getMatResultado()==2)).map((_item) -> 1).reduce(loose, Integer::sum);
        System.out.println("juegos perdidos: "+loose.toString());
        return loose;
    }
    
    /**
     * cuenta las veces que el perdedor haya sido este equipo
     * @return won cantidad de veces que ha ganado
     */
    public Integer getVisitLooseGames(){
        Integer loose = 0;
        //loose = this.matchList.stream().filter((match) -> ("s".equals(match.getMatDisputado())&&match.getMatResultado()==1)).map((_item) -> 1).reduce(loose, Integer::sum);
        //loose += this.matchList1.stream().filter((match) -> ("s".equals(match.getMatDisputado())&&match.getMatResultado()==2)).map((_item) -> 1).reduce(loose, Integer::sum);
        loose = this.matchList.stream().filter((m) -> ("s".equals(m.getMatDisputado())&&m.getMatResultado()==1)).map((_item) -> 1).reduce(loose, Integer::sum);
        System.out.println("juegos perdidos: "+loose.toString());
        return loose;
    }
    
    public Integer getDrawGames(){
        Integer won = 0;
        won = this.matchList.stream().filter((match) -> ("s".equals(match.getMatDisputado())&&match.getMatResultado()==0)).map((_item) -> 1).reduce(won, Integer::sum);
        won = this.matchList1.stream().filter((match) -> ("s".equals(match.getMatDisputado())&&match.getMatResultado()==0)).map((_item) -> 1).reduce(won, Integer::sum);
        return won;
    }
    
    public Integer getLocalDrawGames(){
        Integer won = 0;
        won = this.matchList1.stream().filter((match) -> ("s".equals(match.getMatDisputado())&&match.getMatResultado()==0)).map((_item) -> 1).reduce(won, Integer::sum);
        return won;
    }
    
    public Integer getVisitDrawGames(){
        Integer won = 0;
        won = this.matchList.stream().filter((match) -> ("s".equals(match.getMatDisputado())&&match.getMatResultado()==0)).map((_item) -> 1).reduce(won, Integer::sum);
        return won;
    }
    
    /**
     * //obtiene los partidos jugados y los partidos ganados para en base a ellos,aplicar la fórmula del rendimiento
     * @return avg rendimiento de un equipo
     */
    
    public Integer getAvg(){
        Integer posiblePoints = getPlayedMatchs()*3;
        Integer obtainedPoints = (getWonGames()*3)+getDrawGames();
        System.out.println("posibles: "+posiblePoints.toString());
        System.out.println("obtenidos: "+obtainedPoints.toString());
        Integer avg;

        if(obtainedPoints!=0)
        avg = (obtainedPoints*100)/posiblePoints;
        else 
            avg=0;
        return avg;
    }
    
    /**
     * //obtiene los partidos jugados y los partidos ganados como local para en base a ellos,aplicar la fórmula del rendimiento
     * @return avg rendimiento de un equipo
     */
    
    public Integer getLocalAvg(){
        Integer posiblePoints = getLocalPlayedMatchs()*3;
        Integer obtainedPoints = (getLocalWonGames()*3)+getLocalDrawGames();
        Integer avg;

        if(obtainedPoints!=0)
        avg = (obtainedPoints*100)/posiblePoints;
        else 
            avg=0;
        return avg;
    }
    
    /**
     * //obtiene los partidos jugados y los partidos ganados como visitante para en base a ellos,aplicar la fórmula del rendimiento
     * @return avg rendimiento de un equipo
     */
    
    public Integer getVisitAvg(){
        Integer posiblePoints = getVisitPlayedMatchs()*3;
        Integer obtainedPoints = (getVisitWonGames()*3)+getVisitDrawGames();
        Integer avg;

        if(obtainedPoints!=0)
        avg = (obtainedPoints*100)/posiblePoints;
        else 
            avg=0;
        return avg;
    }
    
    public RetoDto getRetoId() {
        return retoId;
    }

    public void setRetoId(RetoDto retoId) {
        this.retoId = retoId;
    }
    
    public Long getEquId() {
        if(equId.get()!= null && !equId.get().isEmpty())
            return Long.valueOf(equId.get());
        else
            return null;
         }
    public List<Match> getMatchList() {
        return matchList;
    }

    public void setMatchList(List<Match> matchList) {
        this.matchList = matchList;
    }

    public List<Match> getMatchList1() {
        return matchList1;
    }

    public void setMatchList1(List<Match> matchList1) {
        this.matchList1 = matchList1;
    }

    public void setEquId(Long equId) {
        this.equId.set(equId.toString());
    }

    public String getUrl() {
        return url.get();
    }

    public void setUrl(String url) {
        this.url.set(url);
    }

    public String getEquUsu() {
        return equUsu.get();
    }

    public void setEquUsu(String equUsu) {
        this.equUsu.set(equUsu);
    }

    public String getEquPassword() {
        return equPassword.get();
    }

    public void setEquPassword(String equPassword) {
        this.equPassword.set(equPassword);
    }

    public String getEquNombre() {
        return equNombre.get();
    }

    public void setEquNombre(String equNombre) {
        this.equNombre.set(equNombre);
    }

    public List<Reto> getRetoList1() {
        return retoList1;
    }

    public void setRetoList1(List<Reto> retoList1) {
        this.retoList1 = retoList1;
    }

    public String getEquNomJug1() {
        return equNomJug1.get();
    }

    public void setEquNomJug1(String equNomJug1) {
        this.equNomJug1.set(equNomJug1);
    }

    public String getEquNomJug2() {
        return equNomJug2.get();
    }

    public void setEquNomJug2(String equNomJug2) {
        this.equNomJug2.set(equNomJug2);
    }

    public Long getEquTelJug2() {
        return Long.valueOf(equTelJug2.get());
    }

    public void setEquTelJug2(Long equTelJug2) {
        this.equTelJug2.set(equTelJug2.toString());
    }

    public Long getEquTelJug1() {
        return Long.valueOf(equTelJug1.get());
    }

    public void setEquTelJug1(Long equTelJug1) {
        this.equTelJug1.set(equTelJug1.toString());
    }
    
    public Long getEquPts() {
        return Long.valueOf(equPts.getValue());
    }

    public void setEquPts(Long equPts) {
        this.equPts.set(equPts.toString());
    }

    public List<Reto> getRetoList() {
        return retoList;
    }

    public void setRetoList(List<Reto> retoList) {
        this.retoList = retoList;
    }
    
    
    
}
