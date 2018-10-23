/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Model.Cancha;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
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
public class canchaService {
    @PersistenceContext(unitName = "CanchasWsPU")
    private EntityManager em;
    
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
    public Cancha guardarCancha(Cancha cancha){
        Cancha canchaAux;
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
                    canchaAux = em.merge(cancha);
                } else {
                    canchaAux = cancha;
                    em.persist(canchaAux);
                }
            } else {
                canchaAux = cancha;
                em.persist(canchaAux);
            }
            em.flush();
            em.getEntityManagerFactory().getCache().evictAll();
        } catch(Exception ex){
            canchaAux = null;
        }
        return canchaAux;
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
    
    public Boolean eliminarCancha(Cancha cancha){
        Cancha canAux = null;
        if(cancha!=null && cancha.getCanId()!=null){
            Query qryId = em.createNamedQuery("Cancha.findByCanId", Cancha.class);            
            qryId.setParameter("canId", cancha.getCanId());   
            try {
                canAux = (Cancha) qryId.getSingleResult();
            } catch (NoResultException ex) {
                canAux = null;
            }
            if(canAux != null){
                Cancha canAux2 = canAux;
                em.remove(canAux2);
                em.flush();
                em.getEntityManagerFactory().getCache().evictAll();
                canAux = getCancha(canAux2.getCanId());
            } else {
                canAux = null;
            }
        }
        return (canAux == null);
    }
    
    public static byte[] convertDocToByteArray(String path)throws FileNotFoundException, IOException{
        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        try {
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);
            }
        } catch (IOException ex) {
        }
        byte[] bytes = bos.toByteArray();
        return bytes;
    }
    
}
