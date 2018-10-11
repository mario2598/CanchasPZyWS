/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Model.Administrador;
import java.util.logging.Logger;
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
    }
