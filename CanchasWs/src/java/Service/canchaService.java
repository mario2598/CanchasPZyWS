/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Model.Cancha;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Chris
 */
@Stateless
@LocalBean
public class canchaService {
    @PersistenceContext(unitName = "CanchasWsPU")
    private EntityManager em;
    private EntityTransaction et;
    
    public Cancha getCancha(Long canId){
        Cancha cancha;
        Query qry = em.createNamedQuery("Cancha.findByCanId", Cancha.class);
        qry.setParameter("canId", canId);
        try{
            cancha = (Cancha) qry.getSingleResult();
        } catch (NoResultException ex){
            cancha = null;
        }
        return cancha;
    }
    
    /**
     * Este método guarda una cancha en la base de datos
     * Si esta ya existe en la base de datos actualiza sus informacion
     * @param cancha
     * @return 
     */
    public boolean guardarCancha(Cancha cancha){
        boolean guardada;
        Cancha canchaAux;
        et = em.getTransaction();
        et.begin();
        try{
            if(cancha.getCanId()!=null){
                Query qry = em.createNamedQuery("Cancha.findByCanId", Cancha.class);
                qry.setParameter("canId", cancha.getCanId());
                try{
                    canchaAux = (Cancha) qry.getSingleResult();
                } catch(NoResultException ex){
                    canchaAux = null;
                }
                if(canchaAux != null){
                    //Ya existe una cancha con el mismo id en la base de datos
                    em.merge(cancha);
                    guardada = true;
                } else {
                    canchaAux = cancha;
                    em.persist(canchaAux);
                    guardada = true;
                }
            } else {
                canchaAux = cancha;
                em.persist(canchaAux);
                guardada = true;
            }
            et.commit();
        } catch(Exception ex){
            et.rollback();
            guardada = false;
        }
        return guardada;
    }
    
    public List<Cancha> getListaCanchas(){
        List<Cancha> list;
        Query qry = em.createNamedQuery("Cancha.findAll", Cancha.class);
        try{
            list = qry.getResultList();
        } catch(NoResultException ex){
            list = null;
        }
        return list;
    }
    
    /**
     * Retorna lista de canchas de un admin en especifico
     * @param adminId id del admin propietario de las canchas
     * @return 
     */
    public List<Cancha> getListaCanchas(Long adminId){
        List<Cancha> list;
        Query qry = em.createNamedQuery("Cancha.findByAdminId", Cancha.class);
        qry.setParameter("adminId", adminId);
        try{
            list = qry.getResultList();
        } catch(NoResultException ex){
            list = null;
        }
        return list;
    }
    
}
