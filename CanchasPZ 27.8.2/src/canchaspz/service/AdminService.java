/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canchaspz.service;

import canchaspz.model.Administrador;
import canchaspz.model.AdministradorDto;
import canchaspz.model.Equipo;
import canchaspz.util.EntityManagerHelper;
import canchaspz.util.Mensaje;
import static javafx.scene.control.Alert.AlertType.ERROR;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author mario
 */
public class AdminService {
     EntityManager em = EntityManagerHelper.getInstance().getManager();
    private EntityTransaction et;
    
     public Boolean guardarAdmin(AdministradorDto admin) {
        try {
            et = em.getTransaction();
            et.begin();
            Administrador admin1 = new Administrador();
            Query qryUsu = em.createNamedQuery("Administrador.findByAdmUsu",Administrador.class);            
            qryUsu.setParameter("admUsu",admin.getAdmUsu());   
            try {
            admin1 = (Administrador) qryUsu.getSingleResult();
            } catch (NoResultException ex) {
            admin1 = null;
            }
            if(admin1 != null){
                 et.rollback();
                 return false; // en caso que el nombre ya exista
            }
            
            else{                    
                admin1 = new Administrador(admin);
                em.persist(admin1);
            }
            et.commit();
            return true;
        } catch (Exception ex) {
            et.rollback();
            return false;
            
        }
        
    }
     
         public Boolean actualizarAdmin(AdministradorDto admin,Boolean veri) {
        try {
            et = em.getTransaction();
            et.begin();
            String usu;
            Administrador admin1 = new Administrador();
            Administrador admin2 = new Administrador();
            Query qryUsu = em.createNamedQuery("Administrador.findByAdmUsu",Administrador.class);
            Query qryId = em.createNamedQuery("Administrador.findByAdmId",Administrador.class);
            qryUsu.setParameter("admUsu",admin.getAdmUsu());   
            
            if(veri == false){
            try {
            admin1 = (Administrador) qryUsu.getSingleResult();
            } catch (NoResultException ex) {
            admin1 = null;
            }
            if(admin1 != null){
                 et.rollback();
                 return false; // en caso que el nombre ya exista
            }   
            }
            
            
            if(admin.getAdmId() != null){           
            qryId.setParameter("admId",Long.valueOf(admin.getAdmId()));  
            }
            
            try {
             admin1 = (Administrador) qryUsu.getSingleResult();
            } catch (NoResultException ex) {
            admin1 = null;
            }
            if(admin1 != null){
                admin1 = new Administrador(admin);
                admin2 = em.merge(admin1);                              
             }
            else{                    
                admin1 = new Administrador(admin);
                em.persist(admin1);
                }
            em.close();
            et.commit();
            return true;
        } catch (Exception ex) {
            et.rollback();
            return false;
            
        }
        
    }
    
}
