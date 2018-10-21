/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author mario
 */
public class report {
    String nombre;
    String inicio;
    String fin;
    Integer ocupados;
    Integer desocupados;
    Integer monto;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getfin() {
        return fin;
    }

    public void setfin(String Ffinal) {
        this.fin = Ffinal;
    }

    public Integer getOcupados() {
        return ocupados;
    }

    public void setOcupados(Integer ocupados) {
        this.ocupados = ocupados;
    }

    public Integer getDesocupados() {
        return desocupados;
    }

    public void setDesocupados(Integer desocupados) {
        this.desocupados = desocupados;
    }

    public Integer getMonto() {
        return monto;
    }

    public void setMonto(Integer monto) {
        this.monto = monto;
    }

    public report() {
    }

  
}
