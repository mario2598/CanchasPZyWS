/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canchaspz.service;

import canchaspz.model.Administrador;
import canchaspz.model.Equipo;
import canchaspz.model.EquipoDto;
import canchaspz.util.EntityManagerHelper;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author mario
 */
public class EquipoService {
    EntityManager em = EntityManagerHelper.getInstance().getManager();
    private EntityTransaction et;
    
    public Boolean guardarEquipo(EquipoDto equ) {
        try {
            et = em.getTransaction();
            et.begin();
            System.out.print(equ.getEquUsu());
            Equipo equ2 = new Equipo();
            Equipo equ1 = new Equipo();
            Query qryUsu = em.createNamedQuery("Equipo.findByEquUsu",Equipo.class);           
            qryUsu.setParameter("equUsu",equ.getEquUsu());   
            try {
            equ1 = (Equipo) qryUsu.getSingleResult();
            } catch (NoResultException ex) {
            equ1 = null;
            }           
            if(equ1 != null){
                    et.rollback();
                    return false; // en caso que el usuario no este registrado
            }               
            else{                               
                equ1 = new Equipo(equ);
                em.persist(equ1);
               
            }
            et.commit();
            return true;
        } catch (Exception ex) {
            et.rollback();
            return false;          
        }
        
    }
    
    public Boolean actualizarEquipo(EquipoDto equ,Boolean veri) {
        try {
            et = em.getTransaction();
            et.begin();
            System.out.print(equ.getEquId().toString());
            Equipo equ2 = new Equipo();
            Equipo equ1 = new Equipo();
            Query qryUsu = em.createNamedQuery("Equipo.findByEquUsu",Equipo.class);
            Query qryId = em.createNamedQuery("Equipo.findByEquId",Equipo.class);
            qryUsu.setParameter("equUsu",equ.getEquUsu()); 
            if(veri == false){
             try {
            equ1 = (Equipo) qryUsu.getSingleResult();
            } catch (NoResultException ex) {
            equ1 = null;
            }           
            if(equ1 != null){
                    et.rollback();
                    return false; // en caso que el usuario no este registrado
            }   
            }    
            if(equ.getEquId() != null){           
            qryId.setParameter("equId",equ.getEquId());  
            }               

            try {
                  equ1 = (Equipo) qryId.getSingleResult();
            } catch (NoResultException ex) {
               equ1 = null;
            }
            if(equ1 != null){
                equ1 = new Equipo(equ);
                equ2 = em.merge(equ1);                            
             }
            else{                    
                equ1 = new Equipo(equ);
                em.persist(equ1);
                }         
            et.commit();
            return true;
        } catch (Exception ex) {
            et.rollback();
            return false;          
        }
        
    }
    
}
