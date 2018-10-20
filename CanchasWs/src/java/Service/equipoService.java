/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Model.Equipo;
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
 * @author mario
 */
@Stateless
@LocalBean
public class equipoService {
    @PersistenceContext(unitName = "CanchasWsPU")
    private EntityManager em;
    private EntityTransaction et;
    
    public Equipo getEquipo(String usu, String contra){
        Equipo equipo;
        Query qryUsuContraEqu = em.createNamedQuery("Equipo.findByEquUsuPass", Equipo.class);   
        qryUsuContraEqu.setParameter("equPassword",contra);
        qryUsuContraEqu.setParameter("equUsu",usu);
        try {
            equipo = (Equipo) qryUsuContraEqu.getSingleResult();
        } catch (NoResultException ex) {
            equipo = null;
        }
        return equipo;
    }
    
    public Equipo getEquipo(Long equipoId){
        Equipo equipo;
        Query qry = em.createNamedQuery("Equipo.findByEquId", Equipo.class);
        qry.setParameter("equId", equipoId);
        try{
            equipo = (Equipo) qry.getSingleResult();
        } catch(NoResultException ex){
            equipo = null;
        }
        return equipo;
    }
    
    public List<Equipo> getListaEquipos(){
        List<Equipo> equipoList;
        Query tmQry = em.createNamedQuery("Equipo.findAll", Equipo.class);
        try{
            equipoList = tmQry.getResultList();
        } catch (NoResultException ex){
            equipoList = null;
        }
        return equipoList;
    }
    
    /**
     * Persistencia de un equipo
     * Si este ya existe en la base de datos se actualizaran sus datos
     * @param team Equipo a persistir
     * @return Resultado tras intentar la persistencia
     */
    public Equipo guardarEquipo(Equipo team){
        Equipo equipoAux;
        et = em.getTransaction();
        et.begin();
        try{
            if(team.getEquId()!=null){
                Query qryUsu = em.createNamedQuery("Equipo.findByEquId", Equipo.class);            
                qryUsu.setParameter("equId", team.getEquId());   
                try {
                    equipoAux = (Equipo) qryUsu.getSingleResult();
                } catch (NoResultException ex) {
                    equipoAux = null;
                }
            } else {
                equipoAux = null;
            }
            if(equipoAux != null){
                equipoAux = team;
                em.merge(equipoAux);
            } else {
                equipoAux = team;
                em.persist(equipoAux);
            }
            et.commit();
        }catch(Exception ex){
            et.rollback();
            equipoAux = null;
        }
        return equipoAux;
    }
       
}

