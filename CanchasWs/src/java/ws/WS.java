/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import Model.Administrador;
import Model.AdministradorDto;
import Model.Cancha;
import Model.CanchaDto;
import Model.Equipo;
import Model.EquipoDto;
import Model.MatchDto;
import Model.RetoDto;
import Service.adminService;
import Service.canchaService;
import Service.equipoService;
import Service.matchService;
import Service.retoService;
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
     adminService adminService;
     @EJB
     equipoService teamService;
     @EJB
     canchaService canchaService;
     @EJB
     matchService matchService;
     @EJB
     retoService retoService;

    /**
     * Web service operation
     * @param usu
     * @param contra
     * @return 
     */
    @WebMethod(operationName = "getAdmin")
    public Administrador getAdmin(@WebParam(name = "usu") String usu, @WebParam(name = "contra") String contra) {
        Administrador admin;
        admin =  adminService.getAdmin(usu, contra);
        return admin;
    }

    /**
     * Web service operation
     * @param usu
     * @param contra
     * @return 
     */
    @WebMethod(operationName = "getEquipo")
    public Equipo getEquipo(@WebParam(name = "usu") String usu, @WebParam(name = "contra") String contra) {
        Equipo equipo;
        equipo = teamService.getEquipo(usu, contra);
        return equipo;
    }

    /**
     * Web service operation
     * @return 
     */
    @WebMethod(operationName = "getListaCanchas")
    public List getListaCanchas() {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     * @return 
     */
    @WebMethod(operationName = "getListaRetos")
    public List getListaRetos() {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     * @param canchaId
     * @return 
     */
    @WebMethod(operationName = "getListaRetosDesdeCancha")
    public List getListaDesdeFromCancha(@WebParam(name = "canchaId") Long canchaId) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     * @param equipoId
     * @return 
     */
    @WebMethod(operationName = "getListaRetosDesdeEquipo")
    public List getListaRetosDesdeEquipo(@WebParam(name = "equipoId") Long equipoId) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     * @param adminId
     * @return 
     */
    @WebMethod(operationName = "getListaCanchasDesdeAdmin")
    public List getListaCanchasDesdeAdmin(@WebParam(name = "adminId") Long adminId) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     * @return 
     */
    @WebMethod(operationName = "getListaPartidos")
    public List getListaPartidos() {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     * @param canchaId
     * @return 
     */
    @WebMethod(operationName = "getListaPartidosDesdeCancha")
    public Long getListaPartidosDesdeCancha(@WebParam(name = "canchaId") Long canchaId) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     * @param equipoId
     * @return 
     */
    @WebMethod(operationName = "getListaPartidosDesdeEquipo")
    public Long getListaPartidosDesdeEquipo(@WebParam(name = "equipoId") Long equipoId) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     * @param matchId
     * @return 
     */
    @WebMethod(operationName = "getMatch")
    public MatchDto getMatch(@WebParam(name = "matchId") Long matchId) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     * @param retoId
     * @return 
     */
    @WebMethod(operationName = "getReto")
    public RetoDto getReto(@WebParam(name = "retoId") Long retoId) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     * @param canchaId
     * @return 
     */
    @WebMethod(operationName = "getCancha")
    public CanchaDto getCancha(@WebParam(name = "canchaId") Long canchaId) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     * @param administradorDto
     * @return 
     */
    @WebMethod(operationName = "setAdmin")
    public AdministradorDto setAdmin(@WebParam(name = "administradorDto") AdministradorDto administradorDto) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     * @param canchaDto
     * @return 
     */
    @WebMethod(operationName = "setCancha")
    public CanchaDto setCancha(@WebParam(name = "canchaDto") CanchaDto canchaDto) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     * @param equipoDto
     * @return 
     */
    @WebMethod(operationName = "setEquipo")
    public EquipoDto setEquipo(@WebParam(name = "equipoDto") EquipoDto equipoDto) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     * @param matchDto
     * @return 
     */
    @WebMethod(operationName = "setMatch")
    public MatchDto setMatch(@WebParam(name = "matchDto") MatchDto matchDto) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     * @param retoDto
     * @return 
     */
    @WebMethod(operationName = "setReto")
    public RetoDto setReto(@WebParam(name = "retoDto") RetoDto retoDto) {
        //TODO write your implementation code here:
        return null;
    }

//    /**
//     * Metodo para pruebas
//     * @return 
//     */
//    @WebMethod(operationName = "testing")
//    public Boolean testing() {
//        //TODO write your implementation code here:
////        Cancha field;
////        try{
////            field = canchaService.getCancha(Long.valueOf("64"));
//////            CanchaDto fieldDto = new CanchaDto(field);
//////            fieldDto.convertirListaPartidos(field.getMatchList());
//////            fieldDto.convertirListaRetos(field.getRetoList());
////            return true;
////        } catch(NumberFormatException ex){
////            return false;
////        }
//        return false;
//    }
//    
//    /**
//     * Web service operation
//     */
//    @WebMethod(operationName = "operation")
//    public Void operation() {
//        //TODO write your implementation code here:
//        return null;
//    }
}
