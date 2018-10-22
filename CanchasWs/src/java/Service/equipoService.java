/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Model.Administrador;
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
        try{
            if(team.getEquId()!=null){
                Query qryUsu = em.createNamedQuery("Equipo.findByEquId", Equipo.class);            
                qryUsu.setParameter("equId", team.getEquId());   
                try {
                    equipoAux = (Equipo) qryUsu.getSingleResult();
                } catch (NoResultException ex) {
                    equipoAux = null;
                }
                if(equipoAux != null){
                    equipoAux = em.merge(team);
                } else {
                    equipoAux = team;
                    em.persist(equipoAux);
                }
            } else {
                Query qryUsu = em.createNamedQuery("Equipo.findByEquUsu", Equipo.class);            
                qryUsu.setParameter("equUsu", team.getEquId());
                try{
                    equipoAux = (Equipo) qryUsu.getSingleResult();
                }catch(NoResultException ex){
                    equipoAux = null;
                }
                if(equipoAux==null){
                    em.persist(team);
                    equipoAux = em.merge(team);
                } else {
                    equipoAux = null;
                }
            }
            em.flush();
            em.getEntityManagerFactory().getCache().evictAll();
        }catch(Exception ex){
            equipoAux = null;
        }
        return equipoAux;
    }
    
    public Boolean eliminarEquipo(Equipo equipo){
        Equipo equAux = null;
        if(equipo!=null && equipo.getEquId()!=null){
            Query qryId = em.createNamedQuery("Equipo.findByEquId", Equipo.class);            
            qryId.setParameter("equId", equipo.getEquId());   
            try {
                equAux = (Equipo) qryId.getSingleResult();
            } catch (NoResultException ex) {
                equAux = null;
            }
            if(equAux != null){
                Equipo canAux2 = equAux;
                em.remove(canAux2);
                em.flush();
                em.getEntityManagerFactory().getCache().evictAll();
                equAux = getEquipo(canAux2.getEquId());
            } else {
                equAux = null;
            }
        }
        return (equAux == null);
    }
       
}

