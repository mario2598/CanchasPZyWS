/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canchaspz.controller;

import canchaspz.controller.Controller;
import canchaspz.util.AppContext;
import canchaspz.util.FlowController;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Chris
 */
public class MENUController extends Controller implements Initializable {

    @FXML
    private JFXButton aboutBtn;
    @FXML
    private JFXButton configBtn;
    @FXML
    private JFXButton canchasBtn;
    @FXML
    private JFXButton equiposBtn;
    @FXML
    private JFXButton retosBtn;
    @FXML
    private JFXButton rankingBtn;
    @FXML
    private JFXButton historialBtn;
    @FXML
    private ImageView imgCancha;
    @FXML
    private Label nmCancha;
    @FXML
    private ImageView backMenuImage;
    @FXML
    private AnchorPane btnsHolder;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(AppContext.getInstance().getEquipo() == null){
          retosBtn.setDisable(true);
        }
        // TODO
        //backMenuImage.fitWidthProperty().bind(btnsHolder.heightProperty());
    }    

    @Override
    public void initialize() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //cambié esta parte porque el profe pide usar el goView,ahora el stack holderSP está en el centro,
    //entoces con solo hacer goView lo manda al holderSP
    @FXML
    public void canchasBtnAction(){
        ((SimpleStringProperty) AppContext.getInstance().get("viewName")).setValue("Canchas disponibles");
        FlowController.getInstance().goView("CANCHAS");
        FlowController.getInstance().closeHambMenu();
    }
    
    @FXML
    public void equiposBtnAction(){
        ((SimpleStringProperty) AppContext.getInstance().get("viewName")).setValue("Ranking de equipos");
        FlowController.getInstance().goView("EQUIPOS");
        FlowController.getInstance().closeHambMenu();
    }
    
    @FXML
    public void retosBtnAction(){
        ((SimpleStringProperty) AppContext.getInstance().get("viewName")).setValue("Retos disponibles");
        FlowController.getInstance().goView("RETOS");
        FlowController.getInstance().closeHambMenu();
    }
    
    @FXML
    public void rankingBtnAction(){
        ((SimpleStringProperty) AppContext.getInstance().get("viewName")).setValue("Ranking de equipos");
        FlowController.getInstance().goView("RANKING");
        FlowController.getInstance().closeHambMenu();
    }
    @FXML
    public void historialBtnAction(){
        ((SimpleStringProperty) AppContext.getInstance().get("viewName")).setValue("Historial de partidos");
        FlowController.getInstance().goView("HISTORIAL");
        FlowController.getInstance().closeHambMenu();
    }
    
    @FXML
    public void confiUsu(){
        ((SimpleStringProperty) AppContext.getInstance().get("viewName")).setValue("Reporte de ganancias");
        FlowController.getInstance().goView("userInfo");
        FlowController.getInstance().closeHambMenu();
    }
    
}
