/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canchaspz.controller;

import canchaspz.model.Cancha;
import canchaspz.model.CanchaDto;
import canchaspz.model.Equipo;
import canchaspz.model.Reto;
import canchaspz.model.RetoDto;
import canchaspz.service.RetoService;
import canchaspz.util.AppContext;
import canchaspz.util.EntityManagerHelper;
import canchaspz.util.FlowController;
import canchaspz.util.Mensaje;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import static javafx.scene.control.Alert.AlertType.ERROR;
import static javafx.scene.control.Alert.AlertType.INFORMATION;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * FXML Controller class
 *
 * @author mario
 */
public class INFORETOController extends DialogController implements Initializable {

    @FXML
    private ImageView imgLogo;
    @FXML
    private JFXRadioButton RBtodos;
    @FXML
    private JFXRadioButton RBsimilares;
    @FXML
    private JFXComboBox<Cancha> CBcanchas;
    @FXML
    private JFXDatePicker DPfecha;
    @FXML
    private JFXComboBox<Integer> CBdespues;
    @FXML
    private JFXComboBox<String> CBmdDespues;
    @FXML
    private JFXComboBox<Integer> CBantes;
    @FXML
    private JFXComboBox<String> CBmdAntes;
    @FXML
    private JFXButton volver;
    @FXML
    private JFXButton crear;
    RetoDto reto;
    CanchaDto cancha;
    String amPm = "am";
    Mensaje msj = new Mensaje();
    EntityManager em = EntityManagerHelper.getInstance().getManager();
    private EntityTransaction et;
    private List<Cancha> canchaList;
    public List<Reto> retoList;
    public List<Reto> newRetoList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillCanchas();
        if(AppContext.getInstance().getEquipo().getUrl() != null){
           Image img = new Image(AppContext.getInstance().getEquipo().getUrl());
           imgLogo.setImage(img);   
        }  
        for(int i = 1;i <=12;i++){
           CBdespues.getItems().add(i);
           CBantes.getItems().add(i);
        }
        CBmdDespues.getItems().add("AM");
        CBmdDespues.getItems().add("PM");
        CBmdAntes.getItems().add("AM");
        CBmdAntes.getItems().add("PM");
        // TODO
    }    

    @FXML
    private void volver(ActionEvent event) {
        FlowController.getInstance().closeDialog();
    }
    
    private void fillCanchas() {
         for(Cancha c : AppContext.getInstance().getCanchaList()){
             CBcanchas.getItems().add(c);
         }
    }
    
    private Integer nivel(){
        if(RBtodos.isSelected()){
            return 0;
        }
        else{
            return 1;
        }
    }
    

    @FXML
    private void crear(ActionEvent event) {
        if(CBcanchas.getValue() == null || DPfecha.getValue() == null || CBdespues.getValue() == null ||CBmdDespues.getValue() == null ||CBantes.getValue() == null || CBmdAntes.getValue() == null){
          msj.show(ERROR, "CAMPOS VACIOS", "Es necesario llenar la informacion");
        }else{
        CanchaDto cancha1;
        reto = new RetoDto();
        Reto r ;
        reto.setRetoId("0");
        RetoService service = new RetoService();
        reto.setCanchaId(cancha1 = new CanchaDto(CBcanchas.getValue()));
        Equipo equ = new Equipo(AppContext.getInstance().getEquipo());
        reto.setEquipo1Id(equ);
        reto.setRetoCompleto("f");
        reto.setRetoNivel(nivel().toString());
        reto.setRetoFecha(DPfecha.getValue());
        reto.setRetoHoraFin(convertir24(CBantes.getValue(),CBmdAntes.getValue()));
        reto.setRetoHoraIni(convertir24(CBdespues.getValue(),CBmdDespues.getValue()));
        AppContext.getInstance().getEquipo().setRetoId(reto);
        r = service.guardarReto(reto);
        if(r !=null){
          msj.show(INFORMATION, "RETO CREADO", "El reto se creo exitosamente");
          AppContext.getInstance().getRetoList().add(r);
          AppContext.getInstance().getEquipo().getRetoList().add(r);
          (new ArrayList<>(AppContext.getInstance().getCanchaList())).stream()
                  .filter(c->c.getCanId().equals(cancha1.getCanID()))
                  .findAny()
                  .get()
                  .getRetoList()
                  .add(r);
          FlowController.getInstance().closeDialog();
          FlowController.getInstance().goView("RETOS");
        }
        else{
             msj.show(INFORMATION, "RETO NO CREADO", "El reto no se creo exitosamente");
        }
       
    }
    }
    private Long convertir24(Integer n, String amPm){
        if(amPm.equals("PM")){
            return n.longValue()+12;
        }
        else{
            return n.longValue();      
        }
    }

    @Override
    public void initialize() {
    //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
