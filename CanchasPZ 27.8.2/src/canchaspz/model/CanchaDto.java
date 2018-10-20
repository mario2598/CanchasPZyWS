/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canchaspz.model;

import canchaspz.util.DateUtil;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Chris
 */
public class CanchaDto {
    //Attributes
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
    
     public MatchDto getFromList(String id){
        return null;
      //  return matches.stream().filter(c -> c.getMatId().equals(id)).findAny().orElse(null);
    }
    
     public HashMap<String, Integer> getProfitReportInfo(Date startDate,Date endDate){
        HashMap<String,Integer> info=new HashMap<>();
        Integer spacesAtDay=getSpacesAtDay();
        
        info.put("totalSpaces", getTotalSpacesIntoDates(startDate,endDate,spacesAtDay));
        info.put("occupedSpaces", getOccupedSpaces(startDate,endDate));
        info.put("earnedMoney", getEarnedMoney(startDate,endDate));
        info.put("emptySpaces", getEmptySpaces(startDate,endDate,spacesAtDay));    
        
        return info;
    }
    
    public HashMap<String, SimpleStringProperty> getProfitReportInfoProp(Date startDate,Date endDate){
        HashMap<String,SimpleStringProperty> info=new HashMap<>();
        Integer spacesAtDay=getSpacesAtDay();
        
        info.put("totalSpaces", new SimpleStringProperty(getTotalSpacesIntoDates(startDate,endDate,spacesAtDay).toString()));
        info.put("occupedSpaces", new SimpleStringProperty(getOccupedSpaces(startDate,endDate).toString()));
        info.put("earnedMoney", new SimpleStringProperty(getEarnedMoney(startDate,endDate).toString()));
        info.put("emptySpaces", new SimpleStringProperty(getEmptySpaces(startDate,endDate,spacesAtDay).toString()));    
        
        System.out.println("espacios disponibles por día :"+spacesAtDay);
        System.out.println("espacios entre esas fechas :"+getTotalSpacesIntoDates(startDate,endDate,spacesAtDay).toString());  
        System.out.println("partidos jugados entre esas fechas :"+getOccupedSpaces(startDate,endDate).toString());
        System.out.println("espacios vacíos :"+getEmptySpaces(startDate,endDate,spacesAtDay));
        
        return info;
    }
    
    public Integer getSpacesAtDay(){
        Cancha field = new Cancha(this);
        Integer open=field.getCanAbre().intValue();
        Integer close=field.getCanCierra().intValue();
        Integer spacesAtDay=close-open;
        
        return spacesAtDay;
    }
    
    /**
     * retorna la cantidad de partidos que se jugaron entre dos fechas
     * @param startDate
     * @param endDate
     * @return 
     */
    public Integer getOccupedSpaces(Date startDate,Date endDate){
        //calcula los partidos jugados entre dos fechas
        Cancha field = new Cancha(this);
        Integer occupedSpaces=0;
        ArrayList<Match> arrayListMatches=new ArrayList<>();
        arrayListMatches.addAll(this.getMatches());
        occupedSpaces = arrayListMatches.stream().filter((m) -> ((m.getMatDate().compareTo(startDate))==1||((m.getMatDate().compareTo(startDate))==0)
                &&(m.getMatDate().compareTo(endDate)==-1)||(m.getMatDate().compareTo(endDate)==0))&&"s".equalsIgnoreCase(m.getMatDisputado())).map((_item) -> 1).reduce(occupedSpaces, Integer::sum);
        return occupedSpaces;
    }
    
    /**
     * retorna la cantidad de espacios ocupados en un día
     * @param date
     * @return 
     */
    public Integer getOccupedSpaces(Date date){
        //calcula los partidos jugados entre dos fechas
        Cancha field = new Cancha(this);
        Integer occupedSpaces=0;
        ArrayList<Match> arrayListMatches=new ArrayList<>();
        arrayListMatches.addAll(this.getMatches());
        occupedSpaces = arrayListMatches.stream().filter((m) -> ((m.getMatDate().compareTo(date))==0)&&"s".equalsIgnoreCase(m.getMatDisputado())).map((_item) -> 1).reduce(occupedSpaces, Integer::sum);
        return occupedSpaces;
    }
    
    /**
     * retorna la cantidad de dinero ganado entre dos fechas,según la cantidad de partidos disputados y el cobro de la cancha
     * @param startDate
     * @param endDate
     * @return 
     */
    public Integer getEarnedMoney(Date startDate,Date endDate){
        //calcula el dinero recaudadoentre dos fechas
        Cancha field = new Cancha(this);
        Integer earnedMoney=0;
        ArrayList<Match> arrayListMatches=new ArrayList<>();
        arrayListMatches.addAll(this.getMatches());
        earnedMoney = arrayListMatches.stream().filter((m) -> ((m.getMatDate().compareTo(startDate))==1||((m.getMatDate().compareTo(startDate))==0)
                &&(m.getMatDate().compareTo(endDate)==-1)||(m.getMatDate().compareTo(endDate)==0))
                &&"s".equalsIgnoreCase(m.getMatDisputado())).map((m) -> m.getMatCobro().intValue()).reduce(earnedMoney, Integer::sum);
        return earnedMoney;
    }
    
    public Integer getEarnedMoney(Date date){
        //calcula el dinero recaudadoentre dos fechas
        Cancha field = new Cancha(this);
        Integer earnedMoney=0;
        ArrayList<Match> arrayListMatches=new ArrayList<>();
        arrayListMatches.addAll(this.getMatches());
        earnedMoney = arrayListMatches.stream().filter((m) -> ((m.getMatDate().compareTo(date))==0)
                &&"s".equalsIgnoreCase(m.getMatDisputado())).map((m) -> m.getMatCobro().intValue()).reduce(earnedMoney, Integer::sum);
        return earnedMoney;
    }
    
    public Integer getTotalSpacesIntoDates(Date startDate,Date endDate,Integer spacesAtDay){
        //cuenta la cantidad de espacios que hubieron entre dos fechas
        Integer totalSpaces=0;
        for(Integer i=0;i<DateUtil.daysUntil2Dates(startDate, endDate);i++){
            totalSpaces+=spacesAtDay;
        }
        
        return totalSpaces;
    }
    
    public Integer getEmptySpaces(Date startDate,Date endDate,Integer spacesAtDay){
        Integer totalSpaces=getTotalSpacesIntoDates(startDate,endDate,spacesAtDay);
        Integer occupedSpaces=getOccupedSpaces(startDate,endDate);
        return totalSpaces-occupedSpaces;
    }
     
    /**
     * retorna la cantidad de espacios vacíos en un día
     * @param date
     * @return 
     */
    public Integer getEmptySpaces(Date date){
        Integer totalSpaces=getSpacesAtDay();
        Integer occupedSpaces=getOccupedSpaces(date);
        return totalSpaces-occupedSpaces;
    }
    
    public ArrayList<String[]> getDayReport(Date startDate,Date endDate){
        Calendar auxDate = Calendar.getInstance();
        auxDate.setTime(startDate);
        Integer iterations=DateUtil.daysUntil2Dates(startDate, endDate);
        ArrayList<String[]> array=new ArrayList<>();
        for(int i=0;i<iterations;i++){
            String[] data={
                DateUtil.date2String(auxDate.getTime()),
                getOccupedSpaces(auxDate.getTime()).toString(),
                getEmptySpaces(auxDate.getTime()).toString(),
                getEarnedMoney(auxDate.getTime()).toString()
            };
            array.add(data);
            auxDate.add(Calendar.DATE, 1);//agrega un día más
        }
        return array;
    }
    
    public ArrayList<String[]> getDayReportPrueba(Date startDate,Date endDate){
        Calendar auxDate = Calendar.getInstance();
        auxDate.setTime(startDate);
        Integer iterations=DateUtil.daysUntil2Dates(startDate, endDate);
        ArrayList<String[]> array=new ArrayList<>();
        for(int i=0;i<iterations;i++){
            String[] data={
                DateUtil.date2String(auxDate.getTime()),
                "0",
                "0",
                "1000"
            };
            array.add(data);
            auxDate.add(Calendar.DATE, 1);//agrega un día más
        }
        return array;
    }
    
    //Setters and Getters
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
    
}
