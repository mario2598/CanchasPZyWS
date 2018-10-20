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
@XmlRootElement(name = "AdministradorDto")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class AdministradorDto {
    //Attributes
    public Long adminId;
    public String adminUsu;
    public String adminPassword;
    public List<CanchaDto> canchaList;
     
    //Constructors
    public AdministradorDto(){
        
    }
    
    public AdministradorDto(Administrador admin){
        if(admin.getAdmId() != null){
            this.adminId = admin.getAdmId();
        }
        duplicarInfo(admin);
    }
    
    //Methods
    public void duplicarInfo(Administrador admin){
        this.adminUsu = admin.getAdmUsu();
        this.adminPassword = admin.getAdmPassword();
    }
    
    public void convertirListaCanchas(List<Cancha> list){
        this.canchaList = new ArrayList<>();
        for(Cancha cancha : list){
            CanchaDto newC = new CanchaDto(cancha);
            newC.admId = null;
            newC.convertirListaPartidos(cancha.getMatchList());
            newC.convertirListaRetos(cancha.getRetoList());
            canchaList.add(newC);
        }
    }
    
}
