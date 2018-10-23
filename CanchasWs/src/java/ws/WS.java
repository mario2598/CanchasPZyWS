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
import Model.Match;
import Model.MatchDto;
import Model.Reto;
import Model.RetoDto;
import Model.report;
import Service.adminService;
import Service.canchaService;
import Service.equipoService;
import Service.matchService;
import Service.retoService;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.servlet.ServletContext;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

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
     @Inject
     ServletContext context;

    /**
     * Web service operation
     * Metodo para testeo del webservice
     */
    @WebMethod(operationName = "testing")
    public Boolean testing() {
        return true;
    }

    /**
     * Web service operation
     * Consulta del administrador con sus respectivas canchas
     * Cada cancha tendra sus respectivos partidos y retos
     * Cada partido y reto tendra los equipos unicamente con su id
     * (El resto de informacion de los equipos estara vacia)
     * De ser necesario los otros datos, consultar la base de datos mediante el id
     * @param usu
     * @param contra
     * @return AdministradorDto
     */
    @WebMethod(operationName = "getAdmin")
    public AdministradorDto getAdmin(@WebParam(name = "usu") String usu, @WebParam(name = "contra") String contra) {
        Administrador admin;
        AdministradorDto adminDto = null;
        if((usu!=null && !usu.isEmpty()) && (contra!=null && !contra.isEmpty())){
            admin = adminService.getAdmin(usu, contra);
            if(admin!=null){
                adminDto = new AdministradorDto(admin);
                adminDto.convertirListaCanchas(admin.getCanchaList());
            }
        }
        return adminDto;
    }
    
    /**
     * Web service operation
     * Consulta de un equipo con sus respectivos partidos y retos
     * Cada partido y reto con sus respectivos equipos rivales y canchas
     * Cada equipo y cancha contendra unicamente su respectivo id
     * (El resto de la informacion de los equipos estara vacia)
     * De ser necesario los otros datos, consultar la base de datos mediante el id
     * @param usu
     * @param contra
     * @return EquipoDto
     */
    @WebMethod(operationName = "getEquipo")
    public EquipoDto getEquipo(@WebParam(name = "usu") String usu, @WebParam(name = "contra") String contra) {
        Equipo equipo;
        equipo = teamService.getEquipo(usu, contra);
        EquipoDto equipoDto = new EquipoDto(equipo);
        equipoDto.convertirListaPartidos(equipo.getMatchList(), equipo.getMatchList1());
        equipoDto.convertirListaRetos(equipo.getRetoList());
        return equipoDto;
    }
    
    /**
     * Web service operation
     * Consulta de la lista completa de canchas en la base de datos
     * @return Lista de tipo CanchaDto
     */
    @WebMethod(operationName = "getListaCanchas")
    public List<CanchaDto> getListaCanchas() {
        List<Cancha> list;
        list = canchaService.getListaCanchas();
        if(list!=null && !list.isEmpty()){
            List<CanchaDto> listDto = new ArrayList<>();
            for(Cancha cancha : list){
                CanchaDto newC = new CanchaDto(cancha);
                newC.convertirListaPartidos(cancha.getMatchList());
                newC.convertirListaRetos(cancha.getRetoList());
                listDto.add(newC);
            }
            return listDto;
        } else {
            return null;
        }
    }
    
    /**
     * Web service operation
     * Consulta de la lista completa de retos presentes en la base de datos
     * @return Lista de tipo RetoDto
     */
    @WebMethod(operationName = "getListaRetos")
    public List<RetoDto> getListaRetos() {
        List<Reto> list;
        list = retoService.getRetoList();
        if(list!=null && !list.isEmpty()){
            List<RetoDto> listDto = new ArrayList<>();
            for(Reto reto : list){
                RetoDto newR = new RetoDto(reto);
                newR.copiarSoloIDEquipos(reto);
                newR.copiarSoloIdCancha(reto);
                listDto.add(newR);
            }
            return listDto;
        } else {
            return null;
        }
    }

    /**
     * Web service operation
     * Consulta lista de retos de una cancha en especifico
     * @param canchaId
     * @return Lista de tipo RetoDto
     */
    @WebMethod(operationName = "getListaRetosDesdeCancha")
    public List<RetoDto> getListaRetosDesdeCancha(@WebParam(name = "canchaId") Long canchaId) {
        Cancha cancha = canchaService.getCancha(canchaId);
        List<Reto> list = retoService.getRetoList(cancha);
        if(list!=null && !list.isEmpty()){
           List<RetoDto> listDto = new ArrayList<>();
            for(Reto reto : list){
                RetoDto newR = new RetoDto(reto);
                newR.copiarSoloIDEquipos(reto);
                newR.copiarSoloIdCancha(reto);
                listDto.add(newR);
            }
            return listDto; 
        } else {
            return null;
        }
    }

    /**
     * Web service operation
     * Consulta de la lista completa de equipos presentes en la base de datos
     * @return lista de equipos
     */
    @WebMethod(operationName = "getListaEquipos")
    public List<EquipoDto> getListaEquipos() {
        List<Equipo> list;
        list = teamService.getListaEquipos();
        if(list!=null && !list.isEmpty()){
            List<EquipoDto> listDto = new ArrayList<>();
            for(Equipo team : list){
                EquipoDto newE = new EquipoDto(team);
                newE.convertirListaPartidos(team.getMatchList(), team.getMatchList1());
                newE.convertirListaRetos(team.getRetoList());
                listDto.add(newE);
            }
            return listDto;
        } else {
            return null;
        }
    }

    /**
     * Web service operation
     * Consulta lista de retos de un equipo en especifico
     * @param equipoId
     * @return Lista de tipo RetoDto
     */
    @WebMethod(operationName = "getListaRetosDesdeEquipo")
    public List<RetoDto> getListaRetosDesdeEquipo(@WebParam(name = "equipoId") Long equipoId) {
        Equipo equipo = teamService.getEquipo(equipoId);
        List<Reto> list = retoService.getRetoList(equipo);
        if(list!=null && !list.isEmpty()){
           List<RetoDto> listDto = new ArrayList<>();
            for(Reto reto : list){
                RetoDto newR = new RetoDto(reto);
                newR.copiarSoloIDEquipos(reto);
                newR.copiarSoloIdCancha(reto);
                listDto.add(newR);
            }
            return listDto; 
        } else {
            return null;
        }
    }

    /**
     * Web service operation
     * Consulta lista de canchas de un administrador en especifico
     * @param adminId
     * @return Lista de tipo CanchaDto
     */
    @WebMethod(operationName = "getListaCanchasDesdeAdmin")
    public List<CanchaDto> getListaCanchasDesdeAdmin(@WebParam(name = "adminId") Long adminId) {
        Administrador admin = adminService.getAdmin(adminId);
        if(admin!=null && admin.getAdmId()!=null){
            List<Cancha> list = canchaService.getListaCanchas(admin.getAdmId());
            if(list!=null && !list.isEmpty()){
                List<CanchaDto> listDto = new ArrayList<>();
                for(Cancha cancha : list){
                    CanchaDto newC = new CanchaDto(cancha);
                    newC.convertirListaPartidos(cancha.getMatchList());
                    newC.convertirListaRetos(cancha.getRetoList());
                    listDto.add(newC);
                }
                return listDto;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Web service operation
     * Consulta lista completa de partidos presentes en la base de datos
     * @return Lista de tipo MatchDto
     */
    @WebMethod(operationName = "getListaPartidos")
    public List<MatchDto> getListaPartidos() {
        List<Match> list;
        list = matchService.getMatchList();
        if(list!=null && !list.isEmpty()){
            List<MatchDto> listDto = new ArrayList<>();
            for(Match match : list){
                MatchDto newM = new MatchDto(match);
                newM.copiarSoloIDEquipos(match);
                newM.copiarSoloIdCancha(match);
                listDto.add(newM);
            }
            return listDto;
        } else {
            return null;
        }
    }

    /**
     * Web service operation
     * Consulta lista de partidos de una cancha en especifico
     * @param canchaId
     * @return Lista de tipo MatchDto
     */
    @WebMethod(operationName = "getListaPartidosDesdeCancha")
    public List<MatchDto> getListaPartidosDesdeCancha(@WebParam(name = "canchaId") Long canchaId) {
        Cancha cancha = canchaService.getCancha(canchaId);
        if(cancha!=null && cancha.getCanId()!=null){
            List<Match> list = matchService.getMatchList(cancha);
            if(list!=null && !list.isEmpty()){
                List<MatchDto> listDto = new ArrayList<>();
                for(Match match : list){
                    MatchDto newM = new MatchDto(match);
                    newM.copiarSoloIDEquipos(match);
                    newM.copiarSoloIdCancha(match);
                    listDto.add(newM);
                }
                return listDto;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Web service operation
     * Consulta lista de partidos de un equipo en especifico, tanto de local como de visitante
     * Posicion 0 de la primera lista = lista de partidos en el lado izquierdo
     * Posicion 1 de la primera lista = lista de partidos en el lado derecho
     * Las listas son de tipo object, se requiere casting al lado del cliente
     * @param equipoId 
     * @return Lista de tipo lista de tipo match
     */
    @WebMethod(operationName = "getListaPartidosDesdeEquipo")
    public List<Object> getListaPartidosDesdeEquipo(@WebParam(name = "equipoId") Long equipoId) {
        Equipo equipo = teamService.getEquipo(equipoId);
        if(equipo!=null && equipo.getEquId()!=null){
            List<List<Match>> list = matchService.getMatchList(equipo);
            if(list!=null){
                List<Object> listListDto = new ArrayList<>();
                for(List<Match> listL : list){
                    List<MatchDto> listM = new ArrayList<>();
                    for(Match match : listL){
                        MatchDto newM = new MatchDto(match);
                        newM.copiarSoloIDEquipos(match);
                        newM.copiarSoloIdCancha(match);
                        listM.add(newM);
                    }
                    listListDto.add(listM);
                }
                return listListDto;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Web service operation
     * Conlsuta de un partido en especifico, por id
     * El partido contendra los equipos y la cancha perteneciente
     * Los equipos y la cancha contendran unicamente sus respectivos id
     * (El resto de la informacion de estos estara vacia)
     * De ser necesario dicha informacion, consultar la base de datos con el id
     * @param matchId
     * @return MatchDto
     */
    @WebMethod(operationName = "getMatch")
    public MatchDto getMatch(@WebParam(name = "matchId") Long matchId) {
        Match match = matchService.getMatch(matchId);
        if(match!=null && match.getMatId()!=null){
            MatchDto newM = new MatchDto(match);
            newM.copiarSoloIDEquipos(match);
            newM.copiarSoloIdCancha(match);
            return newM;
        } else {
            return null;
        }
    }

    /**
     * Web service operation
     * Conlsuta de un reto en especifico, por id
     * El reto contendra los equipos y la cancha perteneciente
     * Los equipos y la cancha contendran unicamente sus respectivos id
     * (El resto de la informacion de estos estara vacia)
     * De ser necesario dicha informacion, consultar la base de datos con el id
     * @param retoId
     * @return RetoDto
     */
    @WebMethod(operationName = "getReto")
    public RetoDto getReto(@WebParam(name = "retoId") Long retoId) {
        Reto reto = retoService.getReto(retoId);
        if(reto!=null && reto.getRetoId()!=null){
            RetoDto newR = new RetoDto(reto);
            newR.copiarSoloIDEquipos(reto);
            newR.copiarSoloIdCancha(reto);
            return newR;
        } else {
            return null;
        }
    }

    /**
     * Web service operation
     * Consulta de una cancha en especifico, por id
     * Contendra sus relaciones con toda su informacion excepto por los equipos y el administrador
     * Estos solo contendran su id correspondiente
     * De ser necesaria la informacion de estos, consultar la base de datos con dicho id
     * @param canchaId
     * @return CanchaDto
     */
    @WebMethod(operationName = "getCancha")
    public CanchaDto getCancha(@WebParam(name = "canchaId") Long canchaId) {
        Cancha cancha = canchaService.getCancha(canchaId);
        if(cancha!=null && cancha.getCanId()!=null){
            CanchaDto newC = new CanchaDto(cancha);
            newC.convertirListaPartidos(cancha.getMatchList());
            newC.convertirListaRetos(cancha.getRetoList());
            newC.copiarSoloIdAdmin(cancha);
            return newC;
        } else {
            return null;
        }
    }

    /**
     * Web service operation
     * Consulta de un administrador en especifico, por id
     * Contendra sus relaciones con toda su informacion excepto por los equipos
     * Estos solo contendran su id correspondiente
     * De ser necesaria la informacion de estos, consultar la base de datos con dicho id
     * @param adminId
     * @return AdministradorDto
     */
    @WebMethod(operationName = "getAdminById")
    public AdministradorDto getAdminById(@WebParam(name = "adminId") Long adminId) {
        Administrador admin = adminService.getAdmin(adminId);
        if(admin!=null && admin.getAdmId()!=null){
            AdministradorDto adminDto = new AdministradorDto(admin);
            adminDto.convertirListaCanchas(admin.getCanchaList());
            return adminDto;
        } else {
            return null;
        }
    }

    /**
     * Web service operation
     * Consulta de un equipo en especifico, por id
     * Contendra sus relaciones con toda su informacion excepto por
     * Los equipos de cada partido, y la cancha correspondiente a cada partido
     * Estos solo contendran su id correspondiente
     * De ser necesaria la informacion de estos, consultar la base de datos con dicho id
     * @param equipoId 
     * @return EquipoDto
     */
    @WebMethod(operationName = "getEquipoById")
    public EquipoDto getEquipoById(@WebParam(name = "equipoId") Long equipoId) {
        Equipo equipo = teamService.getEquipo(equipoId);
        if(equipo!=null && equipo.getEquId()!=null){
            EquipoDto equipoDto = new EquipoDto(equipo);
            equipoDto.convertirListaPartidos(equipo.getMatchList(), equipo.getMatchList1());
            equipoDto.convertirListaRetos(equipo.getRetoList());
            return equipoDto;
        } else {
            return null;
        }
    }

    /**
     * Web service operation
     * Guarda un administrador en la base datos, junto con todas sus relaciones
     * Si este ya existe actualizara sus datos
     * @param administradorDto
     * @return El mismo administrador pero con el ID nuevo generado por la base de datos
     */
    @WebMethod(operationName = "setAdmin")
    public AdministradorDto setAdmin(@WebParam(name = "administradorDto") AdministradorDto administradorDto) {
        if(administradorDto!=null){
            Administrador admin = new Administrador(administradorDto);
            admin.convertirListaCanchas(administradorDto.canchaList);
            Administrador adminAux = adminService.guardarAdmin(admin);
            AdministradorDto retorno = administradorDto;
            if(adminAux!=null && adminAux.getAdmId()!=null){
                retorno.adminId = adminAux.getAdmId();
            } else {
                retorno = null;
            }
            return retorno;
        } else {
            return null;
        }
    }

    /**
     * Web service operation
     * Guarda una cancha en la base de datos, junto con todas sus relaciones
     * Si esta ya existe actualizara sus datos
     * @param canchaDto
     * @return La misma cancha pero con su nuevo ID generado por la base de datos
     */
    @WebMethod(operationName = "setCancha")
    public CanchaDto setCancha(@WebParam(name = "canchaDto") CanchaDto canchaDto) {
        if(canchaDto!=null){
            Cancha cancha = new Cancha(canchaDto);
            cancha.convertirListaPartidos(canchaDto.matchList);
            cancha.convertirListaRetos(canchaDto.retoList);
            Administrador admin = adminService.getAdmin(canchaDto.admId.adminId);
            admin.getCanchaList().add(cancha);
            cancha.setAdmId(admin);
            Cancha canchaAux = canchaService.guardarCancha(cancha);
            CanchaDto retorno = canchaDto;
            if(canchaAux!=null && canchaAux.getCanId()!=null){
                retorno.canId = canchaAux.getCanId();
            } else {
                retorno = null;
            }
            return retorno;
        } else {
            return null;
        }
    }

    /**
     * Web service operation
     * Guarda un equipo en la base de datos, junto con todas sus relaciones
     * Si este ya existe, se actualizara sus datos
     * @param equipoDto
     * @return El mismo equipo pero con su nuevo ID generado por la base de datos
     */
    @WebMethod(operationName = "setEquipo")
    public EquipoDto setEquipo(@WebParam(name = "equipoDto") EquipoDto equipoDto) {
        if(equipoDto!=null){
            Equipo equipo = new Equipo(equipoDto);
            equipo.convertirListaPartidos(equipoDto.matchList, equipoDto.matchList1);
            equipo.convertirListaRetos(equipoDto.retoList);
            Equipo equipoAux = teamService.guardarEquipo(equipo);
            EquipoDto retorno = equipoDto;
            if(equipoAux != null && equipoAux.getEquId()!=null)
                retorno.equId = equipoAux.getEquId();
            else
                retorno = null;
            return retorno;
        } else {
            return null;
        }
    }

    /**
     * Web service operation
     * Guarda un partido en la base de datos, junto con tdoas sus relaciones
     * Si este ya existe, se actualizaran sus datos
     * @param matchDto
     * @return El mismo partido pero con su nuevo ID generado por la base de datos
     */
    @WebMethod(operationName = "setMatch")
    public MatchDto setMatch(@WebParam(name = "matchDto") MatchDto matchDto) {
        if(matchDto!=null){
            Match match = new Match(matchDto);
            Equipo team1 = null, team2 = null;
            Cancha field;
            if(matchDto.equId1!=null){
                team1 = teamService.getEquipo(matchDto.equId1.equId);
                team1.getMatchList().add(match);
            }
            if(matchDto.equId2!=null){
                team2 = teamService.getEquipo(matchDto.equId2.equId);
                team2.getMatchList1().add(match);
            }
            field = canchaService.getCancha(matchDto.canId.canId);
            field.getMatchList().add(match);
            match.setEquId1(team1);
            match.setEquId2(team2);
            match.setCanId(field);
            Match matchAux = matchService.guardarMatch(match);
            MatchDto retorno = matchDto;
            if(matchAux != null && matchAux.getMatId()!= null)
                retorno.matId = matchAux.getMatId();
            else
                retorno = null;
            return retorno;
        } else {
            return null;
        }
    }

    /**
     * Web service operation
     * Guarda un reto en la base de datos, junto con todas sus relaciones
     * Si este ya existe, se actualizaran sus datos
     * @param retoDto
     * @return El mismo reto pero con su nuevo ID generado por la base de datos
     */
    @WebMethod(operationName = "setReto")
    public RetoDto setReto(@WebParam(name = "retoDto") RetoDto retoDto) {
        if(retoDto!=null){
            Reto reto = new Reto(retoDto);
            Equipo team1 = null;
            Cancha field = null;
            if(retoDto.equipo1Id!=null){
                team1 = teamService.getEquipo(retoDto.equipo1Id.equId);
                team1.getRetoList().add(reto);
                teamService.guardarEquipo(team1);
            }
            field = canchaService.getCancha(retoDto.canchaId.canId);
            field.getRetoList().add(reto);
            canchaService.guardarCancha(field);
            reto.setEquipo1Id(team1);
            reto.setEquipo2Id(null);
            reto.setCanchaId(field);
            Reto retoAux = retoService.guardarReto(reto);
            RetoDto retorno = retoDto;
            if(retoAux != null && retoAux.getRetoId()!=null)
                retorno.retoId = retoAux.getRetoId();
            else
                retorno = null;
            return retorno;
        } else {
            return null;
        }
    }

//    /**
//     * Web service operation
//     * @param reporte
//     * @return
//     * @throws JRException
//     * @throws FileNotFoundException 
//     */
//    @WebMethod(operationName = "crearReporte")
//    public File crearReporte(@WebParam(name = "reporte") cobro reporte) throws JRException, FileNotFoundException {
////         String userHomeDirect = System.getProperty("user.home");
////         String outPutFile = "src/cineuna/jasperReport/jasperPrueba.pdf";
////         List<cobro> list = new ArrayList<>();         
////         list.add(reporte);
////         JRBeanCollectionDataSource cobrojrb = new JRBeanCollectionDataSource(list);
////         JasperReport jasperReport = JasperCompileManager.compileReport("src/cineuna/jasperReport/reporteCanchasPZu.jrxml");
////         Map<String, Object> parametros = new HashMap<>();
////         parametros.put("dataSource", cobrojrb);
////         JasperPrint jasperprint = JasperFillManager.fillReport(jasperReport, parametros, new JREmptyDataSource());
////         
////         OutputStream outputStream = new FileOutputStream(new File(outPutFile));
////            /* Write content to PDF file */
////         JasperExportManager.exportReportToPdfStream(jasperprint, outputStream);
////         File png = new File("src/cineuna/jasperReport/jasperPrueba.pdf");
////         return png;
//    }

    /**
     * Web service operation
     * @param adminId
     * @return 
     */
    @WebMethod(operationName = "deleteAdmin")
    public Boolean deleteAdmin(@WebParam(name = "adminId") Long adminId) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     * @param equipoId
     * @return 
     */
    @WebMethod(operationName = "deleteEquipo")
    public Boolean deleteEquipo(@WebParam(name = "equipoId") Long equipoId) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     * @param canchaId
     * @return 
     */
    @WebMethod(operationName = "deleteCancha")
    public Boolean deleteCancha(@WebParam(name = "canchaId") Long canchaId) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     * @param matchId
     * @return 
     */
    @WebMethod(operationName = "deleteMatch")
    public Boolean deleteMatch(@WebParam(name = "matchId") Long matchId) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     * @param retoId
     * @return 
     */
    @WebMethod(operationName = "deleteReto")
    public Boolean deleteReto(@WebParam(name = "retoId") Long retoId) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Web service operation
     * @param reporte
     * @return 
     * @throws net.sf.jasperreports.engine.JRException 
     * @throws java.io.FileNotFoundException 
     * @throws java.io.IOException
     */
    @WebMethod(operationName = "ganerateJasperReport")
    public byte[] ganerateJasperReport(@WebParam(name = "reporte") report reporte) throws JRException, FileNotFoundException, IOException {
         String ruta = context.getRealPath("/");
         String JasperRuta = ruta+ "\\jasper\\reporteCanchasPZ.jrxml";
         String pdfRuta = ruta+ "\\jasper\\jasperPrueba.pdf";
         String outPutFile = pdfRuta;
         List<report> list = new ArrayList<>();         
         list.add(reporte);
         JRBeanCollectionDataSource cobrojrb = new JRBeanCollectionDataSource(list);
         JasperReport jasperReport = JasperCompileManager.compileReport(JasperRuta);
         Map<String, Object> parametros = new HashMap<>();
         parametros.put("dataSource", cobrojrb);
         JasperPrint jasperprint = JasperFillManager.fillReport(jasperReport, parametros, new JREmptyDataSource());        
         OutputStream outputStream = new FileOutputStream(new File(outPutFile));
            /* Write content to PDF file */
         JasperExportManager.exportReportToPdfStream(jasperprint, outputStream);
         byte[] ReportBytes = canchaService.convertDocToByteArray(outPutFile);
         return ReportBytes;
    }

    /**
     * Web service operation
     */

}
