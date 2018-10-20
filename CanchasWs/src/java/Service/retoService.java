/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Model.Cancha;
import Model.Equipo;
import Model.Reto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
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
public class retoService {
    @PersistenceContext(unitName = "CanchasWsPU")
    private EntityManager em;
    private EntityTransaction et;
    
    public Reto getReto(Long retoId){
        Reto retorno;
        if(retoId!=null){
            Query qry = em.createNamedQuery("Reto.findByRetoId", Reto.class);
            qry.setParameter("retoId", retoId);
            try{
                retorno = (Reto) qry.getSingleResult();
            } catch(NoResultException ex){
                retorno = null;
            }
        } else {
            retorno = null;
        }
        return retorno;
    }
    
    /**
     * Guarda un reto en la base de datos, si este ya existe actualiza su informacion
     * @param reto
     * @return 
     */
    public Reto guardarReto(Reto reto){
        Reto retoAux;
        et = em.getTransaction();
        et.begin();
        try{
            if(reto.getRetoId()!=null){
                Query qry = em.createNamedQuery("Reto.findByRetoId", Reto.class);
                qry.setParameter("canId", reto.getRetoId());
                try{
                    retoAux = (Reto) qry.getSingleResult();
                } catch(NoResultException ex){
                    retoAux = null;
                }
                if(retoAux != null){
                    //Ya existe un reto con el mismo id en la base de datos
                    retoAux = reto;
                    em.merge(retoAux);
                } else {
                    retoAux = reto;
                    em.persist(retoAux);
                }
            } else {
                retoAux = reto;
                em.persist(retoAux);
            }
            et.commit();
        } catch(Exception ex){
            et.rollback();
            retoAux = null;
        }
        return retoAux;
    }
    
    /**
     * Consulta lista completa de retos presentes en la base de datos
     * @return Lista de tipo reto
     */
    public List<Reto> getRetoList(){
        List<Reto> list;
        Query qry = em.createNamedQuery("Reto.findAll", Reto.class);
        try{
            list = qry.getResultList();
        } catch(NoResultException ex){
            list = null;
        }
        return list;
    }
    
    /**
     * Consulta de lista de retos de un equipo
     * @param team Equipo a consultar lista de retos
     * @return lista de tipo match
     */
    public List<Reto> getRetoList(Equipo team){
        List<Reto> list;
        if(team.getEquId()!=null){
            Equipo teamAux;
            Query qry = em.createNamedQuery("Equipo.findByEquId", Equipo.class);
            qry.setParameter("equId", team.getEquId());
            try{
                teamAux = (Equipo) qry.getSingleResult();
            } catch(NoResultException ex){
                teamAux = null;
            }
            if(teamAux!=null){
                list = teamAux.getRetoList();
            } else {
                list = null;
            }
        } else {
            list = null;
        }
        return list;
    }
    
    /**
     * Consulta lista de retos de una cancha en especifico
     * @param cancha Cancha a consultar
     * @return Lista de tipo reto
     */
    public List<Reto> getRetoList(Cancha cancha){
        List<Reto> list;
        if(cancha.getCanId()!=null){
            Query qry = em.createNamedQuery("Reto.findByCanId", Reto.class);
            qry.setParameter("canId", cancha.getCanId());
            try{
                list = qry.getResultList();
            } catch(NoResultException ex){
                list = null;
            }
        } else {
            list = null;
        }
        return list;
    }
    
    /**
     * Consulta lista de retos de una cancha y fecha en especifico
     * @param cancha Cancha a consultar
     * @param fecha Fecha a consultar
     * @return lista de tipo reto
     */
    public List<Reto> getRetoList(Cancha cancha, Date fecha){
        List<Reto> fullList = retoService.this.getRetoList(cancha);
        List<Reto> retornableList;
        if(fullList != null){
            ArrayList<Reto> list = new ArrayList<>(fullList);
            try{
                retornableList = list.stream().filter(r -> r.getRetoFecha().equals(fecha)).collect(Collectors.toList());
            } catch(NullPointerException ex){
                retornableList = null;
            }
        } else {
            retornableList = null;
        }
        return retornableList;
    }
    
    /**
     * Consulta lista de retos de una cancha, fecha y hora en especifico
     * @param cancha Cancha a consultar
     * @param fecha Fecha a consultar
     * @param hora Hora a consultar
     * @return lista de tipo reto
     */
    public Reto getRetoList(Cancha cancha, Date fecha, Integer hora){
        List<Reto> fullList = retoService.this.getRetoList(cancha);
        Reto retornableMatch;
        if(fullList != null){
            ArrayList<Reto> list = new ArrayList<>(fullList);
            try{
                retornableMatch = list.stream()
                        .filter(r -> r.getRetoFecha().equals(fecha) && r.getRetoHoraIni().equals(hora))
                        .findFirst()
                        .get();
            } catch(NullPointerException ex){
                retornableMatch = null;
            }
        } else {
            retornableMatch = null;
        }
        return retornableMatch;
    }
}
