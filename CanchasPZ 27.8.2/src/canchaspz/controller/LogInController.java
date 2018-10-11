/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canchaspz.controller;

import canchaspz.model.Administrador;
import canchaspz.model.AdministradorDto;
import canchaspz.model.Equipo;
import canchaspz.model.EquipoDto;
import canchaspz.util.AppContext;
import canchaspz.util.EntityManagerHelper;
import canchaspz.util.FlowController;
import canchaspz.util.Formato;
import canchaspz.util.Mensaje;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import static javafx.scene.control.Alert.AlertType.ERROR;
import static javafx.scene.control.Alert.AlertType.INFORMATION;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ToggleGroup;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import ws.WS;
import ws.WS_Service;

/**
 * FXML Controller class
 *
 * @author mario
 */
public class LogInController implements Initializable {

    @FXML
    private JFXTextField tfNomUsu;
    @FXML
    private JFXPasswordField pfContra;
    @FXML
    private JFXRadioButton rbAdmin;
    @FXML
    private ToggleGroup tipoUsu;
    @FXML
    private JFXRadioButton rbEquipo;
    @FXML
    private JFXButton btnIniciar;
    @FXML
    private Hyperlink registrarse;
    Administrador admin = new Administrador();
    Equipo equipo = new Equipo();
    WS ws;
    WS_Service ws_service = new WS_Service();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ws = ws_service.getWSPort(); //Aqui crea el ws sacando el puerto del service
        tfNomUsu.setTextFormatter(Formato.getInstance().maxLengthFormat(20));
        pfContra.setTextFormatter(Formato.getInstance().maxLengthFormat(20));
    }    

    @FXML
    private void iniciarSecion(ActionEvent event) {
        Mensaje msj = new Mensaje();
        if(tfNomUsu.getText() == null || pfContra.getText() == null){ 
            msj.show(ERROR,"Error al iniciar secion", "Usuario o Contraseña vacios!");
        }else{
          Boolean b =  evaluarPalabra(pfContra.getText(),tfNomUsu.getText());
          if(b == false){
              msj.show(ERROR,"Error al iniciar secion", "Usuario o Contraseña incorrectos!");
          }
          else{
              msj.show(INFORMATION,"Inicio Secion Correcto", "Usuario y Contraseña correctos!");
           /* if(rbAdmin.isSelected()){ 
                AdministradorDto adm = new AdministradorDto(admin);
                AppContext.getInstance().setAdmin(adm);
                AppContext.getInstance().fillCanchas();
                AppContext.getInstance().fillEquipos();
            }
              if(rbEquipo.isSelected()){  
                  EquipoDto equ = new EquipoDto(equipo);
                  AppContext.getInstance().setEquipo(equ);
                  AppContext.getInstance().fillCanchas();
                    AppContext.getInstance().fillEquipos();
              }
                AppContext.getInstance();
                SimpleStringProperty viewName = new SimpleStringProperty();
                viewName.setValue("Canchas disponibles");
                AppContext.getInstance().set("viewName", viewName);
              FlowController.getInstance().goMain();
          }*/
        }
       }
    }

    @FXML
    private void registrarse(ActionEvent event) {
        FlowController.getInstance().goViewLog("registro", null);
    }
    
    public Boolean evaluarPalabra(String password, String usu){ // aqui el recibe un administrador es donde tiene que cambiar el app context para que guarde un administrador pero no de las entidades 

        if(rbAdmin.isSelected()){      
          if(ws.getAdmin(usu, password)!= null){ // aqui recibe el administrador del ws, si viene lleno devuelve true si no false 
            return true;
        }
          else{
              return false;
          }
        }
        else{      
             if(ws.getEquipo(usu, password)!= null){ // lo mismo con el equipo 
            return true;
        }
          else{
             return false;
          }
        }
        }
}
