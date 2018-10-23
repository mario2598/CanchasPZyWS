/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Model.Cancha;
import Model.Equipo;
import Model.Match;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Chris
 */
@Stateless
@LocalBean
public class matchService {
    @PersistenceContext(unitName = "CanchasWsPU")
    private EntityManager em;
    
    public Match getMatch(Long matchId){
        Match retorno;
        if(matchId!=null){
            Query qry = em.createNamedQuery("Match.findByMatId", Match.class);
            qry.setParameter("matId", matchId);
            try{
                retorno = (Match) qry.getSingleResult();
            } catch(NoResultException ex){
                retorno = null;
            }
        } else {
            retorno = null;
        }
        return retorno;
    }
    
    /**
     * Guarda un partido en la base de datos, si este ya existe actualiza su informacion
     * @param match
     * @return 
     */
    public Match guardarMatch(Match match){
        Match matchAux;
        try{
            if(match.getMatId()!=null){
                Query qry = em.createNamedQuery("Match.findByMatId", Match.class);
                qry.setParameter("matId", match.getMatId());
                try{
                    matchAux = (Match) qry.getSingleResult();
                } catch(NoResultException ex){
                    matchAux = null;
                }
                if(matchAux != null){
                    matchAux = em.merge(match);
                } else {
                    matchAux = match;
                    em.persist(matchAux);
                }
            } else {
                matchAux = match;
                em.persist(matchAux);
            }
            em.flush();
        } catch(Exception ex){
            matchAux = null;
        }
        return matchAux;
    }
    
    /**
     * Consulta de ambas listas de partidos de un equipo
     * En la posicion 0 = partidos en el lado izquierdo
     * En la posicion 1 = partidos en el lado derecho
     * @param team Equipo a consultar lista de partidos
     * @return lista de listas de tipo match
     */
    public ArrayList<List<Match>> getMatchList(Equipo team){
        ArrayList<List<Match>> lists = new ArrayList<>();
        List<Match> list1;
        List<Match> list2;
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
                list1 = teamAux.getMatchList();
                list2 = teamAux.getMatchList1();
                lists.add(list1);
                lists.add(list2);
            } else {
                lists = null;
            }
        } else {
            lists = null;
        }
        return lists;
    }
    
    /**
     * Consulta lista completa de partidos presentes en la base de datos
     * @return Lista completa de partidos
     */
    public List<Match> getMatchList(){
        List<Match> list;
        Query qry = em.createNamedQuery("Match.findAll", Match.class);
        try{
            list = qry.getResultList();
        } catch(NoResultException ex){
            list = null;
        }
        return list;
    }
    
    /**
     * Consulta lista de partidos de una cancha en especifico
     * @param cancha Cancha a consultar
     * @return Lista de tipo match
     */
    public List<Match> getMatchList(Cancha cancha){
        List<Match> list;
        if(cancha.getCanId()!=null){
            Query qry = em.createNamedQuery("Match.findByCanId", Match.class);
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
     * Consulta lista de partidos de una cancha y fecha en especifico
     * @param cancha Cancha a consultar
     * @param fecha Fecha a consultar
     * @return Lista de tipo match
     */
    public List<Match> getMatchList(Cancha cancha, Date fecha){
        List<Match> fullList = getMatchList(cancha);
        List<Match> retornableList;
        if(fullList != null){
            ArrayList<Match> list = new ArrayList<>(fullList);
            try{
                retornableList = list.stream().filter(m -> m.getMatDate().equals(fecha)).collect(Collectors.toList());
            } catch(NullPointerException ex){
                retornableList = null;
            }
        } else {
            retornableList = null;
        }
        return retornableList;
    }
    
    /**
     * Consulta lista de partidos de una cancha, fecha y hora en especifico
     * @param cancha Cancha a consultar
     * @param fecha Fecha a consultar
     * @param hora Hora a consultar
     * @return Lista de tipo match
     */
    public Match getMatch(Cancha cancha, Date fecha, Integer hora){
        List<Match> fullList = getMatchList(cancha);
        Match retornableMatch;
        if(fullList != null){
            ArrayList<Match> list = new ArrayList<>(fullList);
            try{
                retornableMatch = list.stream()
                        .filter(m -> m.getMatDate().equals(fecha) && m.getMatHora().equals(hora))
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
