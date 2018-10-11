/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canchaspz.service;

import canchaspz.model.Match;
import canchaspz.model.MatchDto;
import canchaspz.model.Reto;
import canchaspz.model.RetoDto;
import canchaspz.util.DateUtil;
import canchaspz.util.EntityManagerHelper;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author mario
 */
public class MatchService {
    EntityManager em = EntityManagerHelper.getInstance().getManager();
    private EntityTransaction et;
    Reto reto;
    Match match;
    
    public Reto guardarMatch(MatchDto m,RetoDto r) {
        try {
            match = new Match(m);
            et = em.getTransaction();
            et.begin();
            Query qryreto = em.createNamedQuery("Reto.findByRetoId",Reto.class);
            qryreto.setParameter("retoId",r.getRetoId());                 
            try {
            reto = (Reto) qryreto.getSingleResult();
            } catch (NoResultException ex) {
            reto = null;
            }
            if(reto == null){
                    et.rollback();
                    return null; 
                
            }else{
                em.persist(match);
                em.remove(reto);
            }
            et.commit();
            return reto;
        } catch (Exception ex) {
            et.rollback();
             return null;
        }
    }
    
    public boolean persistMatch(MatchDto matchDto){
        boolean saved = false;
        if(matchDto!=null){
            et = em.getTransaction();
            et.begin();
            try{
                Match persMatch, auxMatch;
                if(matchDto.getMatId()!=null && matchDto.getMatId()>0){
                    persMatch = em.find(Match.class, matchDto.getMatId());
                    if(persMatch==null){
                        if(et.isActive())
                            et.rollback();
                    } else {
                        persMatch = new Match(matchDto);
                        auxMatch = em.merge(persMatch);
                        if(auxMatch!=null){
                            saved = true;
                            et.commit();
                        }
                    }
                } else {
                    persMatch = new Match(matchDto);
                    em.persist(persMatch);
                    System.out.println("Se ha persistido el partido con la fecha: " 
                                + DateUtil.LocalDate2Date(matchDto.getMatDate()) + " y hora: " 
                                + DateUtil.Hour2String(matchDto.getMatHora()));
                    if(persMatch.getMatId()!=null){
                        saved = true;
                        matchDto.setMatId(persMatch.getMatId());
                        et.commit();
                    } else {
                        if(et.isActive())
                            et.rollback();
                    }
                }
            } catch (Exception ex){
                if(et.isActive())
                    et.rollback();
                saved = false;
                System.out.println("Error al guardar partido del dia: " 
                        + DateUtil.LocalDate2String(matchDto.getMatDate())
                        + " hora " + DateUtil.Hour2String(matchDto.getMatHora()));
            }
        }
        return saved;
    }
    
    public boolean removeMatch(MatchDto matchDto){
        boolean deleted = false;
        if(matchDto!=null){
            Match auxMatch;
            et = em.getTransaction();
            et.begin();
            try{
                if(matchDto.getMatId()!=null && matchDto.getMatId()>0){
                    auxMatch = em.find(Match.class, matchDto.getMatId());
                    if(auxMatch==null){
                        if(et.isActive())
                            et.rollback();
                    } else {
                        deleted = true;
                        em.remove(auxMatch);
                        et.commit();
                    }
                }
            } catch(Exception ex) {
                if(et.isActive())
                    et.rollback();
                System.out.println("Error al eliminar partido del dia: " 
                        + DateUtil.LocalDate2String(matchDto.getMatDate())
                        + " hora " + DateUtil.Hour2String(matchDto.getMatHora()));
            }
        }
        return deleted;
    }
    
    public void removeByID(Long id){
        et = em.getTransaction();
        et.begin();
        if(id!=null && id>0){
            Match auxMatch = em.find(Match.class, id);
            if(auxMatch==null){
                if(et.isActive())
                    et.rollback();
            } else {
                em.remove(auxMatch);
                et.commit();
            }
        }
    }
    
}
