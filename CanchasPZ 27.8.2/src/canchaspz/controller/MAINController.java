/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canchaspz.controller;

import canchaspz.CanchasPZ;
import canchaspz.controller.Controller;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Chris
 */
public class MAINController extends Controller implements Initializable {

    @FXML
    private JFXButton exitBtn, searchBtn;
    @FXML
    private JFXHamburger hambBtn;
    @FXML
    private StackPane holderSP, hambMenuSP, dialogSP;
    @FXML
    private JFXDrawer hambMenu;
    @FXML
    private Label hambMenuBack;
    
    private HamburgerBackArrowBasicTransition transition;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dialogSP.setVisible(false);
        hambMenuSP.setVisible(false);
        hambMenuBack.setVisible(false);
        hambMenuBack.setStyle("-fx-background-color: #000000");
        initHambMenu();
    }    
    
    private void initHambMenu(){
        transition = new HamburgerBackArrowBasicTransition(hambBtn);
        transition.setRate(-1);
        FadeTransition ft = new FadeTransition(Duration.millis(500));
        ft.setNode(hambMenuBack);
        hambBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, (e)->{
            transition.setRate(transition.getRate()*-1);
            //System.out.println("canchaspz.controller.MAINController.initHambMenu()");        
            transition.play();
            if(hambMenu.isClosed()){
                hambMenuSP.setVisible(true);
                hambMenuBack.setVisible(true);
                ft.setFromValue(0);
                ft.setToValue(0.4);
                hambMenu.open();
                ft.play();
            } else {
                hambMenu.close();
            }
        });
        hambMenu.setOnDrawerClosing(event -> {
            ft.setDuration(Duration.millis(250));
            ft.setFromValue(0.4);
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
    
    public void closeMenu() {
        if(hambMenu.isOpened()){
            transition.setRate(transition.getRate()*-1);
            transition.play();
            hambMenu.close();
        }
    }
    
    public StackPane getHolderPane(){
        return this.holderSP;
    }

    @Override
    public void initialize() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
