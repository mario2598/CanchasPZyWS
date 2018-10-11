/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import Model.Administrador;
import Model.Equipo;
import Service.adminService;
import Service.equipoService;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author mario
 */
@WebService(serviceName = "WS")
public class WS {
     @EJB
     adminService adService;
     @EJB
     equipoService equService;

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getAdmin")
    public Administrador getAdmin(@WebParam(name = "usu") String usu, @WebParam(name = "contra") String contra) {
        Administrador admin;
        admin =  adService.getAdmin(usu, contra);
        return admin;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getEquipo")
    public Equipo getEquipo(@WebParam(name = "usu") String usu, @WebParam(name = "contra") String contra) {
       Equipo equipo;
       equipo = equService.getEquipo(usu, contra);
        return equipo;
    }
    
    
}
