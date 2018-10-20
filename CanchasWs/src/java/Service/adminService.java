/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Model.Administrador;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author mario
 */
@Stateless
@LocalBean
public class adminService {

    @PersistenceContext(unitName = "CanchasWsPU") //asi se crean  estas  los service y se inyectan en la parte de WS con la anotacion @EJB
    private EntityManager em;
    private EntityTransaction et;
    
    public Administrador getAdmin(String usu, String contra){
        Administrador admin; 
        Query qryUsuContra = em.createNamedQuery("Administrador.findByAdmPassword",Administrador.class);
        qryUsuContra.setParameter("admPassword",contra);
        qryUsuContra.setParameter("admUsu",usu);
        try {
            admin = (Administrador) qryUsuContra.getSingleResult();
        } catch (NoResultException ex) {
            admin = null;
        }
        return admin; 
    }
    
    public Administrador getAdmin(Long adminId){
        Administrador admin;
        Query qry = em.createNamedQuery("Administrador.findByAdmId", Administrador.class);
        qry.setParameter("admId", adminId);
        try{
            admin = (Administrador) qry.getSingleResult();
        } catch(NoResultException ex){
            admin = null;
        }
        return admin;
    }
    
    /**
     * Persistencia de un administrador
     * Si este ya existe en la base de datos se actualizaran sus datos
     * @param admin
     * @return 
     */
    public boolean guardarAdmin(Administrador admin){
        boolean guardado;
        try{
            et = em.getTransaction();
            et.begin();
            Administrador adminAux;
            if(admin.getAdmId()!=null){
                Query qryUsu = em.createNamedQuery("Administrador.findByAdmUsu",Administrador.class);            
                qryUsu.setParameter("admUsu", admin.getAdmUsu());   
                try {
                    adminAux = (Administrador) qryUsu.getSingleResult();
                } catch (NoResultException ex) {
                    adminAux = null;
                }
            } else {
                adminAux = null;
            }
            if(adminAux != null){
                adminAux = admin;
                em.merge(adminAux);
                guardado = true;
            } else {
                adminAux = admin;
                em.persist(adminAux);
                et.commit();
                guardado = true;
            }
        }catch(Exception ex){
            et.rollback();
            guardado = false;
        }
        return guardado;
    }
    
 }
