/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canchaspz.controller;

import canchaspz.CanchasPZ;
import canchaspz.util.AppContext;
import canchaspz.util.FlowController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author robri
 */
public class ContainerController extends Controller implements Initializable {

    @FXML
    private JFXButton searchBtn;
    @FXML
    private JFXButton exitBtn;
    @FXML
    private StackPane holderSP;
    @FXML
    private StackPane hambMenuSP;
    @FXML
    private JFXDrawer hambMenu;
    @FXML
    private StackPane dialogSP;
    @FXML
    private JFXHamburger hambBtn;
    
    private HamburgerBackArrowBasicTransition transition;
    @FXML
    private Label hambMenuBack;
    @FXML
    private BorderPane root;
    @FXML
    private Label lblViewName;

    /**
     * Initializes the controller class.
     * @param url 
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dialogSP.setVisible(false);
        hambMenuSP.setVisible(false);
        hambMenuBack.setVisible(false);
        hambMenuBack.setStyle("-fx-background-color: #000000");
        initHambMenu();
        lblViewName.textProperty().bind((SimpleStringProperty) AppContext.getInstance().get("viewName"));
    }    

    @Override
    public void initialize() {
        
    }
    
    private void initHambMenu(){
        transition = new HamburgerBackArrowBasicTransition(hambBtn);
        transition.setRate(-1);
        FadeTransition ft = new FadeTransition(Duration.millis(500));
        ft.setNode(hambMenuBack);
        
        hambBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, (e)->{
            if(hambMenu.isClosed() && !FlowController.getInstance().isShowingDialog()){
                transition.setRate(transition.getRate()*-1);       
                transition.play();
                hambMenuSP.setVisible(true);
                hambMenuBack.setVisible(true);
                ft.setDuration(Duration.millis(500));
                ft.setFromValue(0);
                ft.setToValue(0.25);
                hambMenu.open();
                ft.play();
            } else{
                closeMenu();
            }
        });
        
        hambMenu.setOnDrawerClosing(event -> {
            ft.setDuration(Duration.millis(250));
            ft.setFromValue(0.25);
            ft.setToValue(0);
            ft.play();
            closeMenu();
            event.consume();
        });
        
        hambMenu.setOnDrawerClosed(event -> {
            hambMenuSP.setVisible(false);
            hambMenuBack.setVisible(false);
            event.consume();
        });
        
        hambMenuBack.setOnMouseClicked(event -> {
            closeMenu();
            event.consume();
        });
        
        try {
            VBox box = FXMLLoader.load(CanchasPZ.class.getResource("view/MENU.fxml"));
            hambMenu.setSidePane(box);
        }
        catch (IOException ex) {
            System.out.println("Error cargando el menu\nError:" + ex);
        }
    }
    
    public StackPane getHolderPane(){
        return this.holderSP;
    }
    
    @FXML
    public void closeMenu() {
        if(hambMenu.isOpened() && !hambMenu.isClosing()){
            transition.setRate(transition.getRate()*-1);
            transition.play();
            hambMenu.close();
        }
    }
    
    public StackPane getDialogsPane(){
        return this.dialogSP;
    }

}
