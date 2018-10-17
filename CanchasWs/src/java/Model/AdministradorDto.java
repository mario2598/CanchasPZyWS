/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mario
 */
@XmlRootElement(name = "AdministradorDto")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class AdministradorDto {
  
     public String adminId;
     public String adminUsu;
     public  String adminPassword;
     public List<Cancha> canchaList;
     
     public AdministradorDto(){
    }
     
    public AdministradorDto(Administrador admin) {
        if(admin.getAdmId() != null){
           this.adminId = admin.getAdmId().toString(); 
        }
        canchaList = admin.getCanchaList();       
        this.adminUsu = admin.getAdmUsu();
        this.adminPassword = admin.getAdmPassword();
        this.canchaList = admin.getCanchaList();
    }

    private void convertirList(ArrayList<CanchaDto> list){
         Cancha cancha;
         for(CanchaDto canchaDto : list){
       //      cancha = new Cancha(canchaDto);
         //    canchaList.add(cancha);
         }
             
     }

    public List<Cancha> getCanchaList() {
        return canchaList;
    }

    public void setCanchaList(ArrayList<Cancha> canchaList) {
        this.canchaList = canchaList;
    }
     
     private void convertirList(List<Cancha> list){
         CanchaDto canchaDto;
         for(Cancha cancha : list){
         //    canchaDto = new CanchaDto(cancha);
     //        canchaList.add(canchaDto);
         }
             
     }
     
    public Long getAdmId() {
        if(adminId== null || adminId.isEmpty())
            return null;
        else
            return Long.valueOf(adminId);
    }

       public void setAdmId(String id) {
        adminId= id;
    }
       
     public String getAdmUsu() {
            return adminUsu;   
     }

       public void setAdmUsu(String usu) {
        adminUsu = usu;
     }
       
       public String getAdmPassword() {
        return adminPassword;
    }

    public void setAdmPassword(String password) {
        adminPassword = password;
    }
    public void addToList(Cancha c){
       canchaList.add(c);
    }
    
    /**
      * obtiene la información del reporte general
      * @param startDate
      * @param endDate
      * @return 
      */

    /**
      * obtiene la información del reporte general
      * @param startDate
      * @param endDate
      * @return 
      */
    
  
}
