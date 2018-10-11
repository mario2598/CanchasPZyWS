/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canchaspz.service;

import canchaspz.model.Administrador;
import canchaspz.model.Equipo;
import canchaspz.model.Reto;
import canchaspz.model.RetoDto;
import canchaspz.util.EntityManagerHelper;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author mario
 */
public class RetoService {
    EntityManager em = EntityManagerHelper.getInstance().getManager();
    private EntityTransaction et;
    Reto reto = new Reto();
    
     public Reto guardarReto(RetoDto reto) {
        try {
            Reto r = new Reto(reto);
            et = em.getTransaction();
            et.begin();
            em.persist(r);
            et.commit();
            System.out.print(r.getRetoId().toString());
            return r;
        } catch (Exception ex) {
            et.rollback();
            return null;
        }
    }
      public Reto removeReto(RetoDto r) {
        try {
            Reto reto;
            et = em.getTransaction();
            et.begin();
            Query qryreto = em.createNamedQuery("Reto.findByRetoId",Reto.class);
            String a = r.getRetoId().toString();
            System.out.print(r.getRetoId().toString());
            qryreto.setParameter("retoId",r.getRetoId());                 
            try {
            reto = (Reto) qryreto.getSingleResult();
            } catch (NoResultException ex) {
            reto = null;
            }
             if(reto == null){
                    et.rollback();
                    return null; // en caso que el usuario no este registrado
                
            }else{
                em.remove(reto);
            }
            et.commit();
            return reto;
           
        } catch (Exception ex) {
            et.rollback();
            return null;
        }
        
    }
}
