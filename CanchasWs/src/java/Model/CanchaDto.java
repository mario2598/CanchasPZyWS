/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import util.DateUtil;

/**
 *
 * @author mario
 */
@XmlRootElement(name = "CanchaDto")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class CanchaDto {
    /*
    public SimpleStringProperty ID;
    private Administrador admId;
    public SimpleStringProperty posterUrl;
    public SimpleStringProperty nombre;
    public SimpleStringProperty tel;
    public SimpleStringProperty cantJugadores;
    public SimpleStringProperty direccion;
    public SimpleStringProperty latitud;
    public SimpleStringProperty longitud;
    public SimpleStringProperty abre;
    public SimpleStringProperty cierra;
    public SimpleStringProperty precioDia;
    public SimpleStringProperty precioNoches;
    private List<Match> matches;
    private List<Reto> retos;
    
    //Constructors
    public CanchaDto(){
        this.ID = new SimpleStringProperty();
        this.admId = null;
        this.posterUrl = new SimpleStringProperty();
        this.nombre = new SimpleStringProperty();
        this.direccion = new SimpleStringProperty();
        this.cantJugadores = new SimpleStringProperty();
        this.latitud = new SimpleStringProperty();
        this.cierra = new SimpleStringProperty();
        this.abre = new SimpleStringProperty();
        this.tel = new SimpleStringProperty();
        this.precioNoches = new SimpleStringProperty();
        this.precioDia = new SimpleStringProperty();
        this.longitud = new SimpleStringProperty();
        this.matches = new ArrayList<>();
        this.retos = new ArrayList<>();

    }
    
    public CanchaDto(String name, String cantJugadores){
        this();
        this.nombre.set(name);
        this.cantJugadores.set(cantJugadores);
    }
    
    public CanchaDto(Cancha cancha){
        this();
        if(cancha.getCanId()!=null)
            this.ID.set(cancha.getCanId().toString());
        refreshData(cancha);
    }
    
    public CanchaDto(CanchaDto cancha){
        this();
        if(cancha.getCanID()!=null)
            this.ID.set(cancha.getCanID().toString());
        refreshData(cancha);
    }
    
    //Methods
    public void refreshData(Cancha cancha){
        this.nombre.set(cancha.getCanNombre());
        this.direccion.set(cancha.getCanDireccion());
        this.posterUrl.set(cancha.getCanUrl());
        this.tel.set(cancha.getCanTel().toString());
        this.cantJugadores.set(cancha.getCanCantJugadores().toString());
        this.direccion.set(cancha.getCanDireccion());
        try{
            this.latitud.set(cancha.getCanLatitud().toString());
            this.longitud.set(cancha.getCanLongitud().toString());
        } catch (NullPointerException ex){
            
        }
        this.abre.set(cancha.getCanAbre().toString());
        this.cierra.set(cancha.getCanCierra().toString());
        this.precioDia.set(cancha.getCanPrecioDia().toString());
        this.precioNoches.set(cancha.getCanPrecioNoches().toString());
        this.matches = cancha.getMatchList();
        this.retos =  cancha.getRetoList();
        this.admId = cancha.getAdmId();
    }
    
    public void refreshData(CanchaDto cancha){
        this.nombre.set(cancha.getCanNombre());
        this.direccion.set(cancha.getCanDireccion());
        this.posterUrl.set(cancha.getCanUrl());
        this.tel.set(cancha.getCanTel().toString());
        this.cantJugadores.set(cancha.getCanCantJugadores().toString());
        this.direccion.set(cancha.getCanDireccion());
        this.latitud.set(cancha.getCanLatitud().toString());
        this.longitud.set(cancha.getCanLongitud().toString());
        this.abre.set(cancha.getCanAbre().toString());
        this.cierra.set(cancha.getCanCierra().toString());
        this.precioDia.set(cancha.getCanPrecioDia().toString());
        this.precioNoches.set(cancha.getCanPrecioNoches().toString());
        this.matches = cancha.getMatchList();
        this.retos =  cancha.getRetoList();
        this.admId = cancha.getAdmId();
    }
    
    public boolean isOpen(Integer hour){
        Long longH = Long.valueOf(hour);
        if(getCanAbre()<getCanCierra()){
            return getCanAbre()<=longH && getCanCierra()>longH;
        } else if(getCanCierra()<getCanAbre()){
            return !(getCanCierra()<=longH && getCanAbre()>longH);
        } else {
            return false;
        }
    }
    
    public boolean hayCampo(LocalDate date, Integer hour){
        if(isOpen(hour))
            return !(getMatchArrayList().stream().anyMatch(match -> DateUtil.Date2LocalDate(match.getMatDate()).equals(date) && match.getMatHora().equals(hour.longValue())));
        else 
            return false;
    }
    
    public void addMatch(Match match){
      //  this.matches.add(match);
      this.matches.add(match);
    }
    /**
     * retorna la cantidad de partidos que se jugaron entre dos fechas
     * @param startDate
     * @param endDate
     * @return 
     

    public Long getCanID() {
        if(ID.get()!=null)
            return Long.valueOf(ID.get());
        else
            return null;
    }

    public void setCanID(Long ID) {
        if(ID!=null)
            this.ID.set(ID.toString()); 
    }

    public String getCanNombre() {
        return nombre.getValue();
    }

    public void setCanNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public Long getCanTel() {
        return Long.valueOf(tel.getValue());
    }

    public void setCanTel(Long tel) {
        this.tel.set(tel.toString());
    }

    public Long getCanCantJugadores() {
        return Long.valueOf(cantJugadores.getValue());
    }

    public List<Reto> getRetoList() {
        return retos;
    }

    public void setRetoList(List<Reto> retos) {
        this.retos = retos;
    }

    public void setCanCantJugadores(Long cantJugadores) {
        this.cantJugadores.set(cantJugadores.toString());
    }

    public String getCanDireccion() {
        return direccion.getValue();
    }

    public void setCanDireccion(String direccion) {
        this.direccion.set(direccion);
    }

    public Double getCanLatitud() {
        return Double.valueOf(latitud.getValue());
    }

    public void setCanLatitud(Double latitud) {
        this.latitud.set(String.valueOf(latitud));
    }

    public Double getCanLongitud() {
        return Double.valueOf(longitud.getValue());
    }

    public void setCanLongitud(Double longitud) {
        this.longitud.set(longitud.toString());
    }

    public Long getCanAbre() {
        return Long.valueOf(abre.getValue());
    }

    public void setCanAbre(Long abre) {
        this.abre.set(abre.toString());
    }

    public Long getCanCierra() {
        return Long.valueOf(cierra.getValue());
    }

    public void setCanCierra(Long cierra) {
        this.cierra.set(cierra.toString());
    }

    public Long getCanPrecioDia() {
        return Long.valueOf(precioDia.getValue());
    }

    public void setCanPrecioDia(Long precioDia) {
        this.precioDia.set(precioDia.toString());
    }

    public Long getCanPrecioNoches() {
        return Long.valueOf(precioNoches.getValue());
    }

    public void setCanPrecioNoches(Long precioNoches) {
        this.precioNoches.set(precioNoches.toString());
    }

    public Administrador getAdmId() {
        return admId;
    }

    public void setAdmId(Administrador admId) {
        this.admId = admId;
    }

    public List<Match> getMatchList() {
        return matches;
    }

    public void setMatchList(List<Match> matches) {
        this.matches = matches;
    }
     
    public ArrayList<Match> getMatchArrayList(){
        return new ArrayList<>(this.matches);
    }
    
    public ArrayList<Reto> getRetoArrayList(){
        return new ArrayList<>(this.retos);
    }

    public String getCanUrl() {
        return posterUrl.get();
    }

    public void setCanUrl(String posterUrl) {
        this.posterUrl.set(posterUrl);
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public List<Reto> getRetos() {
        return retos;
    }

    public void setRetos(List<Reto> retos) {
        this.retos = retos;
    }
    
    public String getNombre() {
        return nombre.getValue();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }
    */
}
