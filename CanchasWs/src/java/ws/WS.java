/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import Model.Administrador;
import Model.AdministradorDto;
import Model.Equipo;
import Service.adminService;
import Service.equipoService;
import java.util.List;
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

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getAdList")
    public List getAdList(@WebParam(name = "id") Long id) {
        
        return adService.getAdminList(id);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getEquiposList")
    public List getEquiposList() {
        
        return equService.fillEquipos();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "jj")
    public AdministradorDto jj() {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getRetoList")
    public List getRetoList(@WebParam(name = "parameterdfdf") AdministradorDto parameterdfdf) {
        //TODO write your implementation code here:
        return null;
    }
    
    
}
