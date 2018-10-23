/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Model.Administrador;
import java.lang.annotation.AnnotationFormatError;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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
    public Administrador guardarAdmin(Administrador admin){
        Administrador adminAux;
        try{
            if(admin.getAdmId()!=null){
                Query qryId = em.createNamedQuery("Administrador.findByAdmId", Administrador.class);            
                qryId.setParameter("admId", admin.getAdmId());   
                try {
                    adminAux = (Administrador) qryId.getSingleResult();
                } catch (NoResultException ex) {
                    adminAux = null;
                }
                if(adminAux != null){
                    adminAux = em.merge(admin);
                } else {
                    adminAux = admin;
                    em.persist(adminAux);
                }
            } else {
                Query qryUsu = em.createNamedQuery("Administrador.findByAdmUsu", Administrador.class);            
                qryUsu.setParameter("admUsu", admin.getAdmUsu());
                try{
                    adminAux = (Administrador) qryUsu.getSingleResult();
                }catch(NoResultException ex){
                    adminAux = null;
                }
                if(adminAux==null){
                    em.persist(admin);
                    adminAux = em.merge(admin);
                } else {
                    adminAux = null;
                }
            }
            em.flush();
            em.getEntityManagerFactory().getCache().evictAll();
        }catch(Exception | AnnotationFormatError ex){
            adminAux = null;
        }
        return adminAux;
    }
    
    /**
     * 
     * @param admin
     * @return 
     */
    public Boolean eliminarAdmin(Administrador admin){
        Administrador adminAux = null;
        if(admin!=null && admin.getAdmId()!=null){
            Query qryId = em.createNamedQuery("Administrador.findByAdmId", Administrador.class);            
            qryId.setParameter("admId", admin.getAdmId());   
            try {
                adminAux = (Administrador) qryId.getSingleResult();
            } catch (NoResultException ex) {
                adminAux = null;
            }
            if(adminAux != null){
                Administrador adminAux2 = adminAux;
                em.remove(adminAux2);
                em.flush();
                em.getEntityManagerFactory().getCache().evictAll();
                adminAux = getAdmin(adminAux2.getAdmId());
            } else {
                adminAux = null;
            }
        }
        return (adminAux == null);
    }
    
 }
