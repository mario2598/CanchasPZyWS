/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canchaspz.controller;

import canchaspz.model.Cancha;
import canchaspz.model.CanchaDto;
import canchaspz.model.Equipo;
import canchaspz.model.Match;
import canchaspz.model.MatchDto;
import canchaspz.model.Reto;
import canchaspz.model.RetoDto;
import canchaspz.service.MatchService;
import canchaspz.service.RetoService;
import canchaspz.util.AppContext;
import canchaspz.util.EntityManagerHelper;
import canchaspz.util.FlowController;
import canchaspz.util.Mensaje;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import static javafx.scene.control.Alert.AlertType.ERROR;
import static javafx.scene.control.Alert.AlertType.INFORMATION;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * FXML Controller class
 *
 * @author mario
 */
public class ChallengesInfoController extends DialogController implements Initializable {

    @FXML
    private Label dateLbl;
    @FXML
    private JFXComboBox<Integer> hoursCB;
    @FXML
    private Label fieldLbl;
    @FXML
    private ImageView imgLogo;
    @FXML
    private Label teamNameLbl;
    @FXML
    private Label playedMatchsLbl;
    @FXML
    private Label sumLbl;
    @FXML
    private Label avrgLbl;
    @FXML
    private Label posLbl;
    @FXML
    private TilePane historyTP;
    @FXML
    private JFXButton btnAceptar;
    Image img ;
    RetoDto r;
    MatchDto m = new MatchDto();
    Mensaje msj = new Mensaje();
    EntityManager em = EntityManagerHelper.getInstance().getManager();
    private EntityTransaction et;
    public List<Reto> retoList;
    public List<Reto> newRetoList;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void aceptar(ActionEvent event) {
        CanchaDto canchaDto = new CanchaDto(r.getCanchaId());
        MatchService mService = new MatchService();
        RetoService rService = new RetoService();
        String s  = btnAceptar.getText();
        Cancha cancha =new Cancha(r.getCanchaId());
        Equipo equ = new Equipo(AppContext.getInstance().getEquipo());
        if("Aceptar Reto".equals(btnAceptar.getText())){
        if(verificarNivel(r,equ)){
            if(canchaDto.hayCampo(r.getRetoFecha(),hoursCB.getValue())){
          if(hoursCB.getValue() != null){     
        m.setCanId(cancha);
        m.setTeam1(r.getEquipo1Id());
        m.setTeam2(equ);
        if(hoursCB.getValue() <= 17){
          m.setMatCobro(r.getCanchaId().getCanPrecioDia()); 
        }
        else{
            m.setMatCobro(r.getCanchaId().getCanPrecioNoches());  
        }
        m.setMatDate(r.getRetoFecha());
        m.setMatDisputados(false);
        m.setMatHora(hoursCB.getValue().longValue());
        Reto veri = mService.guardarMatch(m,r);
        Cancha appConCan = (new ArrayList<>(AppContext.getInstance().canchaList)).stream()
                .filter(f -> f.getCanId().equals(cancha.getCanId()))
                .findAny()
                .get();
        appConCan.getMatchList().add(new Match(m));
        appConCan.getRetoList().remove(new Reto(r));
        //Tal vez haga falta guardar y remover el partido y el reto, de la lista de equipos del appcontext
        if(veri != null){
           msj.show(INFORMATION, "RETO ACEPTADO", "EL RETO SE ACEPTO CORRECTAMENTE");
           AppContext.getInstance().getRetoList().remove(veri);
           AppContext.getInstance().getCanchaList().stream().filter(c->Objects.equals(c.getCanId(), cancha.getCanId())).findAny().get().getRetoList().remove(veri);
           AppContext.getInstance().getEquipo().getRetoList().remove(veri);
           FlowController.getInstance().closeDialog();
           FlowController.getInstance().goView("RETOS");
           
        }
        else{
           msj.show(ERROR, "ERROR EN EL SISTEMA", "EL RETO NO SE ACEPTO CORRECTAMENTE");
           FlowController.getInstance().closeDialog();  
           FlowController.getInstance().goView("RETOS");
        }
        }
        else{ //primer if
            msj.show(ERROR, "CAMPOS VACIOS", "ES NECESARIO ESCOGER LA HORA DEL RETO");
        }  
        }else{
            msj.show(ERROR,"ERROR ACEPTANDO RETO", "Esa hora esta ocupada en esta cancha");
        }
        }else{
            msj.show(ERROR,"ERROR ACEPTANDO RETO", "La puntuacion de tu Equipo esta fuera del rango aceptado en este reto"); 
        }
            
        }
        else{
           Reto veri2 = rService.removeReto(r);
           if(veri2 != null){
           msj.show(INFORMATION, "RETO ELIMINADO", "EL RETO SE ELIMINO CORRECTAMENTE");
           FlowController.getInstance().closeDialog();
           AppContext.getInstance().getRetoList().remove(veri2);
           AppContext.getInstance().getCanchaList().stream().filter(c->Objects.equals(c.getCanId(), cancha.getCanId())).findAny().get().getRetoList().remove(veri2);
           AppContext.getInstance().getEquipo().getRetoList().remove(veri2);
           FlowController.getInstance().goView("RETOS");
           
        }
        else{
           msj.show(ERROR, "ERROR EN EL SISTEMA", "EL RETO NO SE ELIMINO CORRECTAMENTE");
           FlowController.getInstance().closeDialog();  
           FlowController.getInstance().goView("RETOS");
        }
           
        }
 
        
    }
    private Boolean verificarNivel(RetoDto r,Equipo e){
        Integer i = 0;
        System.out.println(r.getRetoNivel().toString()+ i.toString());
        if(r.getRetoNivel() == i.longValue()){
            return true;
        }
        else{
            if(r.getEquipo1Id().getEquPts().intValue()-5 <= e.getEquPts().intValue() && e.getEquPts().intValue() <= r.getEquipo1Id().getEquPts().intValue()+5){
                return true;
            }else{
                return false;
            }
        }
 
        
    }

    
    private Integer loadInfo(){
        r = AppContext.getInstance().getRetoActual();
        if(r.getEquipo1Id().getEquUrl() != null){
             img = new Image(r.getEquipo1Id().getEquUrl());
             imgLogo.setImage(img);
        }
        for(int i = r.getRetoHoraIni().intValue();i <= r.getRetoHoraFin().intValue();i++){
            hoursCB.getItems().add(i);
        }
        fieldLbl.setText(r.getCanchaId().getCanNombre());
        teamNameLbl.setText(r.getEquipo1Id().getEquNombre());
        dateLbl.setText(r.getRetoFecha().toString());
        return r.getRetoHoraIni().intValue();
    }
    
    @FXML
    private void volver(ActionEvent event) {
        FlowController.getInstance().closeDialog();
    }

    @Override
    public void initialize() {
        r = AppContext.getInstance().getRetoActual();
        if(AppContext.getInstance().getRetoActual().getEquipo1Id().getEquId().intValue() == AppContext.getInstance().getEquipo().getEquId().intValue()){ // en caso que el card se abra en mis retos 
            btnAceptar.setText("Eliminar Reto");
        }
        else{
            btnAceptar.setText("Aceptar Reto");
        }
        hoursCB.getItems().clear();
        
        this.hoursCB.setValue(loadInfo());
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
