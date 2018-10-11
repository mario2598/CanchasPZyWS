/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canchaspz.controller;

import canchaspz.controller.Controller;
import canchaspz.model.AdministradorDto;
import canchaspz.model.EquipoDto;
import canchaspz.service.AdminService;
import canchaspz.service.EquipoService;
import canchaspz.util.FlowController;
import canchaspz.util.Formato;
import canchaspz.util.Mensaje;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import static javafx.scene.control.Alert.AlertType.CONFIRMATION;
import static javafx.scene.control.Alert.AlertType.ERROR;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author mario
 */
public class RegistroController extends Controller implements Initializable {

    @FXML
    private HBox hBoxSelect;
    @FXML
    private ImageView imgEquipo;
    @FXML
    private ImageView imgEsc;
    @FXML
    private ImageView imgAdmin;
    @FXML
    private JFXButton btnVolver;
    @FXML
    private VBox hBoxEqu;
    @FXML
    private JFXTextField txtNomUsu;
    @FXML
    private JFXTextField txtNomEqu;
    @FXML
    private JFXPasswordField pfContra;
    @FXML
    private JFXPasswordField pfContraAdm;
    @FXML
    private JFXTextField txtEnc1;
    @FXML
    private JFXTextField txtTel1;
    @FXML
    private JFXTextField txtEnc2;
    @FXML
    private JFXTextField txtTel2;
    @FXML
    private JFXButton btnVolverRegistro;
    @FXML
    private JFXButton btnRegistrar;
    @FXML
    private VBox hBoxAdmin;
    @FXML
    private JFXTextField txtNomUsuAmd;
    @FXML
    private JFXButton btnVolverAdm;
    @FXML
    private JFXButton btnRegistrar1;
    EquipoDto equ;
    AdministradorDto admin;
    final FileChooser fileChooser = new FileChooser();
    private String url = null;
    private String tipoUsu = null; // tipo de usuario que se va a registrar
    private String requeridos = "Es necesario llenar :  "; // elementos que faltan de llenar
    Mensaje msj = new Mensaje();
    private List<JFXTextField> camposEquList = new ArrayList();  // maneja textfields de equipos
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       fillList();
       txtNomUsu.setTextFormatter(Formato.getInstance().maxLengthFormat(20));
       txtNomEqu.setTextFormatter(Formato.getInstance().maxLengthFormat(20));
       pfContra.setTextFormatter(Formato.getInstance().maxLengthFormat(20));
       pfContraAdm.setTextFormatter(Formato.getInstance().maxLengthFormat(20));
       txtEnc1.setTextFormatter(Formato.getInstance().maxLengthFormat(20));
       txtTel1.setTextFormatter(Formato.getInstance().integerFormat(8));
       txtEnc2.setTextFormatter(Formato.getInstance().maxLengthFormat(20));
       txtTel2.setTextFormatter(Formato.getInstance().integerFormat(8));
       
        imgEquipo.setOnMouseClicked((MouseEvent event) -> {
             tipoUsu = "equipo";
             hBoxEqu.setVisible(true);
             hBoxAdmin.setVisible(false);
             hBoxSelect.setVisible(false);
        });
        imgAdmin.setOnMouseClicked((MouseEvent event) -> {
             tipoUsu = "admin";
             hBoxEqu.setVisible(false);
             hBoxAdmin.setVisible(true);
             hBoxSelect.setVisible(false);
        });
    }    

    @FXML
    private void volverMain(ActionEvent event) {
        FlowController.getInstance().goLogin();
    }

    @FXML
    private void volverRegistro(ActionEvent event) {
        hBoxEqu.setVisible(false);
         hBoxSelect.setVisible(true);
    }

    @FXML
    private void registrar(ActionEvent event) { //Registrar equipo
        Integer i = 0;
        Boolean vacio = verificarVacio(); 
        if(vacio == true){ // en caso de que queden campos sin llenar
          msj.show(ERROR, "CAMPOS VACIOS", requeridos);  
          requeridos = "Es necesario llenar :  ";
        }
        else{        
        equ = new EquipoDto();
        equ.setEquNombre(txtNomEqu.getText());
        equ.setEquUsu(txtNomUsu.getText());
        equ.setEquPassword(pfContra.getText());
        equ.setEquNomJug1(txtEnc1.getText());
        equ.setEquNomJug2(txtEnc2.getText());
        equ.setEquPts(Long.valueOf("0"));
        equ.setEquTelJug1(Long.valueOf(txtTel1.getText()));
        equ.setEquTelJug2(Long.valueOf(txtTel2.getText()));;
        equ.setUrl(url);
        EquipoService service = new EquipoService();
        Boolean b = service.guardarEquipo(equ);
        if(b == false){
            msj.show(ERROR, "Error al crear usuario", "El nombre de usuario ya esta creado");
            txtNomUsu.clear();
        }
        else{
            msj.show(CONFIRMATION, "USUARIO CREADO", "El usuario se creo correctamente");
            hBoxEqu.setVisible(false);
            hBoxSelect.setVisible(true);
            FlowController.getInstance().initialize();
            clearAll();
            
        }
        }
        
    }
    
    @FXML
    private void registrarAdm(ActionEvent event) { //Registrar equipo
        Boolean vacio = verificarVacio();
        if(vacio == true){ // en caso de que queden campos sin llenar
          msj.show(ERROR, "CAMPOS VACIOS", requeridos); 
          requeridos = "Es necesario llenar :  ";
        }
        else{
        admin = new AdministradorDto();
        admin.setAdmUsu(txtNomUsuAmd.getText());
        admin.setAdmPassword(pfContraAdm.getText());
        AdminService service = new AdminService();
        Boolean b = service.guardarAdmin(admin);
        if( b == false){
            msj.show(ERROR, "Error al crear usuario", "El nombre de usuario ya esta creado");
            txtNomUsuAmd.clear();
            pfContraAdm.clear();
        }
        else{
            msj.show(CONFIRMATION, "USUARIO CREADO", "El usuario se creo correctamente");
            hBoxAdmin.setVisible(false);
            hBoxSelect.setVisible(true);
            FlowController.getInstance().initialize();
            clearAll();
        }
        }
    }
    
     @FXML
    private void add(ActionEvent event) throws MalformedURLException {  
         Window primaryStage = null;
        File file1 = fileChooser.showOpenDialog(primaryStage);
        if (file1 != null) {
            String thumbURL1 = file1.toURI().toURL().toString();
            Image image1 = new Image(thumbURL1);
            imgEsc.setImage(image1);
            url = thumbURL1;
       }  
    }
    

    @FXML
    private void volverAdm(ActionEvent event) {
        hBoxAdmin.setVisible(false);
         hBoxSelect.setVisible(true);
    }

    @Override
    public void initialize() {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void clearAll() { // Limpiar todos los textfields   
        txtNomUsuAmd.clear();
        pfContraAdm.clear();
        txtNomUsu.clear();
        txtNomEqu.clear();
        pfContra.clear();
        txtEnc1.clear();
        txtEnc2.clear();
        txtTel1.clear();
        txtTel2.clear();
        imgEsc = new ImageView();                  
    }
    
    private void fillList() { // llenar la lista campos con los text field   
        camposEquList.add(txtNomUsu);
        camposEquList.add(txtNomEqu);
        camposEquList.add(txtEnc1);
        camposEquList.add(txtEnc2);
        camposEquList.add(txtTel1);
        camposEquList.add(txtTel2);
    }
    
    
     private Boolean verificarVacio() {//verificar si faltan campos de llenar
         Boolean veri = false;
         if(tipoUsu == "equipo"){
             if(pfContra.getText().isEmpty()){
                requeridos +="|" + pfContra.getPromptText()+"| ";
                veri = true;
            }
        for(JFXTextField campo : camposEquList){
            if(campo.getText().isEmpty()){
                requeridos +="|" + campo.getPromptText()+"| ";
                veri = true;
            }
        }
        } 
         else{
            if(pfContraAdm.getText().isEmpty()){
                requeridos +="|" + pfContra.getPromptText()+"| ";
                veri = true;
            } 
            if(txtNomUsuAmd.getText().isEmpty()){
                requeridos +="|" + txtNomUsuAmd.getPromptText()+"| ";
                veri = true;
            }
         }
         return veri;
         
     }
    
}
