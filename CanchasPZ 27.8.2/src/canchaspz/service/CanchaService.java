/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canchaspz.service;

import canchaspz.model.Cancha;
import canchaspz.model.CanchaDto;
import canchaspz.util.EntityManagerHelper;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author mario
 */
public class CanchaService {
    EntityManager em = EntityManagerHelper.getInstance().getManager();
    private EntityTransaction et;

    public Boolean guardarCancha(CanchaDto fieldDto) {
        boolean saved = false;
        if(fieldDto != null){
            et = em.getTransaction();
            et.begin();
            try{
                Cancha field, fieldAux;
                if (fieldDto.getCanID()!= null && fieldDto.getCanID()> 0) {
                    field = em.find(Cancha.class, fieldDto.getCanID());
                    if (field == null) {
                        if(et.isActive())
                            et.rollback();
                    } else {
                        field = new Cancha(fieldDto);
                        fieldAux = em.merge(field);
                        if(fieldAux != null){
                            saved = true;
                            fieldDto.setCanID(fieldAux.getCanId());
                            et.commit();
                        } else {
                            if(et.isActive())
                                et.rollback();
                        }
                    }
                } else {
                    field = new Cancha(fieldDto);
                    em.persist(field);
                    fieldAux = em.merge(field);
                    if(fieldAux!=null){
                        saved = true;
                        fieldDto.setCanID(fieldAux.getCanId());
                        et.commit();
                    } else {
                        if(et.isActive())
                            et.rollback();
                    }
                }
            }catch (Exception ex) {
                if(et.isActive())
                    et.rollback();
                saved = false;
                System.out.println("Error guardando Cancha: " + fieldDto.getCanNombre() + "\nError: " + ex);
            }
        }
        return saved;
    }
    
    public boolean eliminarCancha(CanchaDto fieldDto){
        boolean deleted = false;
        if(fieldDto!=null){
            Cancha auxCancha;
            et = em.getTransaction();
            et.begin();
            try {
                if (fieldDto.getCanID()!= null && fieldDto.getCanID()> 0) {
                    auxCancha = em.find(Cancha.class, fieldDto.getCanID());
                    if (auxCancha == null) {
                        if(et.isActive())
                            et.rollback();
                    } else {
                        em.remove(auxCancha);
                        deleted = true;
                        et.commit();
                    }
                }
            } catch (Exception ex) {
                if(et.isActive())
                    et.rollback();
                System.out.println("Error eliminando Cancha: " + fieldDto.getCanNombre() + "\nError: " + ex);
            }
        }
        return deleted;
    }
}
