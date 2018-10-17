/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canchaspz.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author mario
 */
public class AdministradorDto {
     public SimpleStringProperty admId;
     public SimpleStringProperty admUsu;
     public  SimpleStringProperty admPassword;
     public List<Cancha> canchaList;
     
     public AdministradorDto(){
        this.admId = new SimpleStringProperty();
        this.admUsu = new SimpleStringProperty();
        this.admPassword = new SimpleStringProperty();
        this.canchaList = new ArrayList<>();
    }

    public List<Cancha> getCanchaList() {
        return canchaList;
    }

    public void setCanchaList(ArrayList<Cancha> canchaList) {
        this.canchaList = canchaList;
    }
     
   /* public AdministradorDto(){
        this();//llama el constructor por defecto para que inicialice
        this.admId.set(admin.getAdmId().toString());
        this.admUsu.set(admin.getAdmUsu());
        this.admPassword.set(admin.getAdmPassword()); 
        this.canchaList =  admin.getCanchaList();
    }*/
     
     public AdministradorDto(AdministradorDto admin){
        this();//llama el constructor por defecto para que inicialice
        this.admId.set(admin.getAdmId().toString());
        this.admUsu.set(admin.getAdmUsu());
        this.admPassword.set(admin.getAdmPassword()); 
        this.canchaList =  admin.getCanchaList();
    }
     
     private void convertirList(List<Cancha> list){
         CanchaDto canchaDto;
         for(Cancha cancha : list){
             canchaDto = new CanchaDto(cancha);
     //        canchaList.add(canchaDto);
         }
             
     }
     
    public Long getAdmId() {
        if(admId.get()!= null && !admId.get().isEmpty())
            return Long.valueOf(admId.get());
        else
            return null;
    }

       public void setAdmId(String id) {
        this.admId.set(id);
    }
       
     public String getAdmUsu() {
            return admUsu.get();   
     }

       public void setAdmUsu(String usu) {
        this.admUsu.set(usu);
     }
       
       public String getAdmPassword() {
        return admPassword.getValue();
    }

    public void setAdmPassword(String password) {
        this.admPassword.set(password);
    }
    public void addToList(Cancha c){
        this.canchaList.add(c);
    }
    
    /**
      * obtiene la información del reporte general
      * @param startDate
      * @param endDate
      * @return 
      */
     public HashMap<String, Integer> getProfitReportInfo(Date startDate,Date endDate){
        HashMap<String,Integer> info=new HashMap<>();
        Integer occupedSpaces=0;
        Integer emptySpaces=0;
        Integer totalProfit=0;
        
        ArrayList<Cancha> arrayListFields=new ArrayList<>();
        arrayListFields.addAll(this.getCanchaList());
        
        for(Cancha c:arrayListFields){
            CanchaDto field=new CanchaDto(c);
            Integer spacesDay= field.getSpacesAtDay();
            occupedSpaces += field.getOccupedSpaces(startDate, endDate);
            emptySpaces += field.getEmptySpaces(startDate, endDate, spacesDay);
            totalProfit += field.getEarnedMoney(startDate, endDate);
        }
        
        info.put("occupedSpaces", occupedSpaces);
        info.put("earnedMoney", totalProfit);
        info.put("emptySpaces", emptySpaces);
        
        return info;
    }
     
    /**
      * obtiene la información del reporte general
      * @param startDate
      * @param endDate
      * @return 
      */
     public HashMap<String, SimpleStringProperty> getProfitReportInfoProp(Date startDate,Date endDate){
        HashMap<String,SimpleStringProperty> info=new HashMap<>();
        Integer occupedSpaces=0;
        Integer emptySpaces=0;
        Integer totalProfit=0;
        
        ArrayList<Cancha> arrayListFields=new ArrayList<>();
        arrayListFields.addAll(this.getCanchaList());
        
        for(Cancha c:arrayListFields){
            CanchaDto field=new CanchaDto(c);
            Integer spacesDay= field.getSpacesAtDay();
            occupedSpaces += field.getOccupedSpaces(startDate, endDate);
            emptySpaces += field.getEmptySpaces(startDate, endDate, spacesDay);
            totalProfit += field.getEarnedMoney(startDate, endDate);
        }
        
        info.put("occupedSpaces", new SimpleStringProperty(occupedSpaces.toString()));
        info.put("earnedMoney", new SimpleStringProperty(totalProfit.toString()));
        info.put("emptySpaces", new SimpleStringProperty(emptySpaces.toString()));
        
        return info;
    }

}
