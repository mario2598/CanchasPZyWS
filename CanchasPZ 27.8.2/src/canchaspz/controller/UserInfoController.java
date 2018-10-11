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
import canchaspz.util.AppContext;
import canchaspz.util.FlowController;
import canchaspz.util.Formato;
import canchaspz.util.Mensaje;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import static javafx.scene.control.Alert.AlertType.ERROR;
import static javafx.scene.control.Alert.AlertType.INFORMATION;
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
public class UserInfoController extends Controller implements Initializable {

     @FXML
    private VBox hBoxAdmin;

    @FXML
    private JFXTextField txtAdminName;

    @FXML
    private JFXPasswordField pfAdminPass;

    @FXML
    private HBox adminEditBtns;

    @FXML
    private JFXButton btnCancelAdmin;

    @FXML
    private JFXButton btnAcceptAdmin;

    @FXML
    private JFXButton btnEditAdmin;

    @FXML
    private JFXButton btnVolverAdm;

    @FXML
    private VBox hBoxTeam;

    @FXML
    private ImageView imgLogo1;

    @FXML
    private JFXTextField txtTeamName;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    private JFXPasswordField pfUserPass;

    @FXML
    private JFXTextField txtContact1;

    @FXML
    private JFXTextField txtTel1;

    @FXML
    private JFXTextField txtContact2;

    @FXML
    private JFXTextField txtTel2;

    @FXML
    private HBox userEditBtns;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnAccept;

    @FXML
    private JFXButton btnEditUser;

    @FXML
    private JFXButton btnVolverEqu;
    AdministradorDto adminAux;
    EquipoDto equAux;
    Mensaje msj = new Mensaje();
    final FileChooser fileChooser = new FileChooser();
    private String url = null;
    Boolean veriUsu = false;
    Boolean veriDatos = false;

    /**
     * Initializes the controller class.
     */
   @Override
    public void initialize(URL url, ResourceBundle rb) {
        imgLogo1.setOnMouseClicked((MouseEvent event) -> {
            try {
                addTeamImg();
            } catch (MalformedURLException ex) {
                Logger.getLogger(UserInfoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
       txtUserName.setTextFormatter(Formato.getInstance().maxLengthFormat(20));
       txtTeamName.setTextFormatter(Formato.getInstance().maxLengthFormat(20));
       pfUserPass.setTextFormatter(Formato.getInstance().maxLengthFormat(20));
       pfAdminPass.setTextFormatter(Formato.getInstance().maxLengthFormat(20));
       txtContact1.setTextFormatter(Formato.getInstance().maxLengthFormat(20));
       txtTel1.setTextFormatter(Formato.getInstance().integerFormat(8));
       txtContact2.setTextFormatter(Formato.getInstance().maxLengthFormat(20));
       txtTel2.setTextFormatter(Formato.getInstance().integerFormat(8));
       txtAdminName.setTextFormatter(Formato.getInstance().maxLengthFormat(20));
    }

    private void addTeamImg() throws MalformedURLException {
        Window primaryStage = null;
        File file1 = fileChooser.showOpenDialog(primaryStage);
        if (file1 != null) {
            String thumbURL1 = file1.toURI().toURL().toString();
            equAux.setUrl(thumbURL1);
        }
        bindEquipo();
    }

    @FXML
    private void editUserInfo(ActionEvent event) {
        imgLogo1.setDisable(false);
        bindEquipo();
        btnEditUser.setVisible(false);
        btnCancel.setVisible(true);
        btnAccept.setVisible(true);
        txtTeamName.setDisable(false);
        txtUserName.setDisable(false);
        pfUserPass.setDisable(false);
        txtContact1.setDisable(false);
        txtTel1.setDisable(false);
        txtContact2.setDisable(false);
        txtTel2.setDisable(false);

    }

    @FXML
    private void CancelChanges(ActionEvent event) {
        unbindEquipo();
        btnEditUser.setVisible(true);
        btnCancel.setVisible(false);
        btnAccept.setVisible(false);
        FlowController.getInstance().goView("userInfo");
    }

    @FXML
    private void AcceptChanges(ActionEvent event) {
        EquipoService service = new EquipoService();
        bindEquipo();
        Boolean veri = veriDatosEqu();
        Boolean veri1 = verificarDatosUsuEqu();
        if (veri1 == true && veri == true) {
            veri = service.actualizarEquipo(equAux, veri1);
        } else if (veri1 == false) {
            veri = service.actualizarEquipo(equAux, veri1);
        }
        if (veri == true) {
            bindEquipo();
            AppContext.getInstance().setEquipo(equAux);
            msj.show(INFORMATION, "INFORMACION ACTUALIZADA", "Se actualizo tus datos correctamente");
            FlowController.getInstance().goView("userInfo");
        } else {
            unbindEquipo();
            clear();
            bindEquipo();
            msj.show(ERROR, "ERROR AL ACTUALIZAR", "El nombre de usuario ya existe");
            FlowController.getInstance().goView("userInfo");
        }
    }

    @FXML
    private void volverEqu(ActionEvent event) {
        FlowController.getInstance().goView("CANCHAS");
    }

    @FXML
    private void cancelAdm(ActionEvent event) {
        unbindAdmin();
        btnEditAdmin.setVisible(true);
        btnAcceptAdmin.setVisible(false);
        btnCancelAdmin.setVisible(false);
        FlowController.getInstance().goView("userInfo");
    }

    @FXML
    private void registrarAdm(ActionEvent event) {
        AdminService service = new AdminService();
        bindAdmin();
        Boolean veri = veriDatosAdmin();
        Boolean veri1 = verificarDatosUsuAdmin();
        if (veri1 == true && veri == true) {
            veri = service.actualizarAdmin(adminAux, veri1);
 
        } else if (veri1 == false) {
            veri = service.actualizarAdmin(adminAux, veri);
        }
        if (veri == true) {
            unbindAdmin();
            AppContext.getInstance().setAdmin(adminAux);
            msj.show(INFORMATION, "INFORMACION ACTUALIZADA", "Se actualizo tus datos correctamente");
            FlowController.getInstance().goView("userInfo");
        } else {
            unbindAdmin();
            clear();
            bindAdmin();
            msj.show(ERROR, "ERROR AL ACTUALIZAR", "El nombre de usuario ya existe");
            FlowController.getInstance().goView("userInfo");
        }
    }

    @FXML
    private void editAminInfo(ActionEvent event) {
        bindAdmin();
        btnEditAdmin.setVisible(false);
        btnAcceptAdmin.setVisible(true);
        btnCancelAdmin.setVisible(true);
        txtAdminName.setDisable(false);
        pfAdminPass.setDisable(false);
    }

    @FXML
    private void volverAdmin(ActionEvent event) {
        FlowController.getInstance().goView("Container");
    }

    private void unbindAdmin() {
        txtAdminName.textProperty().unbindBidirectional(adminAux.admUsu);
        pfAdminPass.textProperty().unbindBidirectional(adminAux.admUsu);

    }

    private void bindAdmin() {
        txtAdminName.textProperty().bindBidirectional(adminAux.admUsu);
        pfAdminPass.textProperty().bindBidirectional(adminAux.admPassword);
    }

    private void unbindEquipo() {
        txtTeamName.textProperty().unbindBidirectional(equAux.equNombre);
        txtUserName.textProperty().unbindBidirectional(equAux.equUsu);
        pfUserPass.textProperty().unbindBidirectional(equAux.equPassword);
        txtContact1.textProperty().unbindBidirectional(equAux.equNomJug1);
        txtTel1.textProperty().unbindBidirectional(equAux.equTelJug1);
        txtContact2.textProperty().unbindBidirectional(equAux.equNomJug2);
        txtTel2.textProperty().unbindBidirectional(equAux.equTelJug2);

    }

    private void bindEquipo() {
        Image img = new Image(equAux.getUrl());
        imgLogo1.setImage(img);
        txtTeamName.textProperty().bindBidirectional(equAux.equNombre);
        txtUserName.textProperty().bindBidirectional(equAux.equUsu);
        pfUserPass.textProperty().bindBidirectional(equAux.equPassword);
        txtContact1.textProperty().bindBidirectional(equAux.equNomJug1);
        txtTel1.textProperty().bindBidirectional(equAux.equTelJug1);
        txtContact2.textProperty().bindBidirectional(equAux.equNomJug2);
        txtTel2.textProperty().bindBidirectional(equAux.equTelJug2);
    }

    private void clear() {
        txtTeamName.clear();
        txtUserName.clear();
        pfUserPass.clear();
        txtContact1.clear();
        txtTel1.clear();
        txtContact2.clear();
        txtTel2.clear();
        txtAdminName.clear();
        pfAdminPass.clear();
    }

    private Boolean veriDatosEqu() {
        if (!equAux.getEquPassword().equals(AppContext.getInstance().getEquipo().getEquPassword())) {
            veriDatos = true;
        }
        if (!equAux.getUrl().equals(AppContext.getInstance().getEquipo().getUrl())) {
            veriDatos = true;
        }
        if (!equAux.getEquNombre().equals(AppContext.getInstance().getEquipo().getEquNombre())) {
            veriDatos = true;
        }
        if (!equAux.getEquNomJug1().equals(AppContext.getInstance().getEquipo().getEquNomJug1())) {
            veriDatos = true;
        }
        if (!equAux.getEquNomJug2().equals(AppContext.getInstance().getEquipo().getEquNomJug2())) {
            veriDatos = true;
        }
        if (!Objects.equals(equAux.getEquTelJug1(), AppContext.getInstance().getEquipo().getEquTelJug1())) {
            veriDatos = true;
        }
        if (!Objects.equals(equAux.getEquTelJug2(), AppContext.getInstance().getEquipo().getEquTelJug2())) {
            veriDatos = true;
        }
        return veriDatos;
    }

    private Boolean verificarDatosUsuEqu() {
        if (equAux.getEquUsu().equals(AppContext.getInstance().getEquipo().getEquUsu())) {
            veriUsu = true;
        }
        return veriUsu;
    }
    
    private Boolean veriDatosAdmin() {
        if (!adminAux.getAdmPassword().equals(AppContext.getInstance().getAdmin().getAdmPassword())) {
            veriDatos = true;
        }
        return veriDatos;
    }

    private Boolean verificarDatosUsuAdmin() {
        if (adminAux.getAdmUsu().equals(AppContext.getInstance().getAdmin().getAdmUsu())) {
            veriUsu = true;
        }
        return veriUsu;
    }

    @Override
    public void initialize() {
        if (AppContext.getInstance().getAdmin() == null) {
            imgLogo1.setDisable(true);
            btnEditUser.setVisible(true);
            btnCancel.setVisible(false);
            btnAccept.setVisible(false);
            equAux = new EquipoDto(AppContext.getInstance().getEquipo());
            clear();
            bindEquipo();
            hBoxTeam.setVisible(true);
            hBoxAdmin.setVisible(false);
            txtTeamName.setDisable(true);
            txtUserName.setDisable(true);
            pfUserPass.setDisable(true);
            txtContact1.setDisable(true);
            txtTel1.setDisable(true);
            txtContact2.setDisable(true);
            txtTel2.setDisable(true);

        } else {
            btnEditAdmin.setVisible(true);
            btnAcceptAdmin.setVisible(false);
            btnCancelAdmin.setVisible(false);
            adminAux = new AdministradorDto(AppContext.getInstance().getAdmin());
            clear();
            bindAdmin();
            txtAdminName.setDisable(true);
            pfAdminPass.setDisable(true);
            hBoxTeam.setVisible(false);
            hBoxAdmin.setVisible(true);
        }
    }
    }