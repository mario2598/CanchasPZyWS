/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Model.Administrador;
import Model.Equipo;
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
public class equipoService {
    @PersistenceContext(unitName = "CanchasWsPU")
    private EntityManager em;
    
    public Equipo getEquipo(String usu, String contra){
        Equipo equipo;
        Query qryUsuContraEqu = em.createNamedQuery("Equipo.findByEquUsuPass",Equipo.class);   
        qryUsuContraEqu.setParameter("equPassword",contra);
        qryUsuContraEqu.setParameter("equUsu",usu);
         try {
               equipo =  (Equipo) qryUsuContraEqu.getSingleResult();
           
        } catch (NoResultException ex) {
               equipo = null;
        }
        return equipo;
    }
        
       
    }

