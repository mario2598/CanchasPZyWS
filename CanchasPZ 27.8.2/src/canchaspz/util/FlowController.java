/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canchaspz.util;

import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import canchaspz.CanchasPZ;
import canchaspz.controller.CANCHASController;
import canchaspz.controller.ContainerController;
import canchaspz.controller.Controller;
import canchaspz.controller.CustomDialogController;
import canchaspz.controller.DialogController;
import canchaspz.logic.Card;
import canchaspz.model.CanchaDto;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import java.util.function.Consumer;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

/**
 *
 * @author esanchez
 */
public class FlowController {

    private static FlowController INSTANCE = null;
    private static Stage mainStage;
    private static Stage loginStage;
    private static ResourceBundle idioma;
    private static HashMap<String, FXMLLoader> loaders = new HashMap<>();
    private boolean showingDialog = false;
    
    private FlowController() {
    }

    private static void createInstance() {
        if (INSTANCE == null) {
            synchronized (FlowController.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FlowController();
                }
            }
        }
    }

    public static FlowController getInstance() {
        if (INSTANCE == null) {
            createInstance();
        }
        return INSTANCE;
    }

    public void InitializeFlow(Stage stage, ResourceBundle rb) {
        getInstance();
        mainStage = stage;
        idioma = rb;
        stage.setMinWidth(900);
        stage.setMinHeight(600);
    }

    private FXMLLoader getLoader(String name) {
        FXMLLoader loader = loaders.get(name);
        if (loader == null) {
            synchronized (FlowController.class) {
                if (loader == null) {
                    try {
                        loader = new FXMLLoader(CanchasPZ.class.getResource("view/" + name + ".fxml"), idioma);
                        loader.load();
                        loaders.put(name, loader);
                    } catch (Exception ex) {
                        loader = null;
                        java.util.logging.Logger.getLogger(FlowController.class.getName()).log(Level.SEVERE, "Creando loader [" + name + "].", ex);
                    }
                }
            }
        }
        return loader;
    }
    
    public void goMain() {
        try {
            FXMLLoader loader = getLoader("Container");
            mainStage.setScene(new Scene(loader.getRoot()));
            if(loginStage!=null && loginStage.isShowing()){
                loginStage.close();   
            }
            mainStage.show();
            FlowController.getInstance().goView("CANCHAS");
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(FlowController.class.getName()).log(Level.SEVERE, "Error inicializando la vista base.", ex);
        }
    }
    
    public void goLogin() {
        try {
            Stage logStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(CanchasPZ.class.getResource("view/logIn.fxml"));
            Parent root = loader.load();
            logStage.setScene(new Scene(root));
            this.loginStage = logStage;
            logStage.show();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(FlowController.class.getName()).log(Level.SEVERE, "Error inicializando la vista base.", ex);
        }
    }

    public void goView(String viewName) {
        goView(viewName, null);
    }
    
    
    public void goView(String viewName, String accion) {
        FXMLLoader loader = getLoader(viewName);
        Controller controller = loader.getController();
        
        controller.setAccion(accion);
        controller.initialize();
        Stage stage = controller.getStage();
        if (stage == null) {
            stage = mainStage;
            controller.setStage(stage);
        }
        
        //aquí obtiene el holderSP
        StackPane holderSP = (StackPane)(((StackPane) ((BorderPane) stage.getScene().getRoot()).getCenter())).getChildren().get(0);
        
        holderSP.getChildren().clear();
        holderSP.getChildren().add(loader.getRoot());
}

    public void goView(String viewName, String location, String accion) {
        FXMLLoader loader = getLoader(viewName);
        Controller controller = loader.getController();
        controller.setAccion(accion);
        controller.initialize();
        Stage stage = controller.getStage();
        if (stage == null) {
            stage = this.mainStage;
            controller.setStage(stage);
        }
        switch (location) {
            case "Center":
                //(((StackPane) ((BorderPane) stage.getScene().getRoot()).getCenter()).getChildren()).clear();
                ((StackPane) ((BorderPane) stage.getScene().getRoot()).getCenter()).getChildren().add(0,loader.getRoot());
                break;
            case "Top":
                break;
            case "Bottom":
                break;
            case "Right":
                break;
            case "Left":
                break;
            default:
                break;
        }
    }
    
    public void goViewLog(String viewName, String accion) {
        FXMLLoader loader = getLoader(viewName);
        Controller controller = loader.getController();     
        controller.setAccion(accion);
        controller.initialize();
        Stage stage = controller.getStage();
        if (stage == null) {
            stage = loginStage;
            controller.setStage(stage);
        }
        
        //aquí obtiene el holderSP
        HBox holderSP = (HBox)(((StackPane) ((BorderPane) stage.getScene().getRoot()).getCenter())).getChildren().get(0);
        
        holderSP.getChildren().clear();
        holderSP.getChildren().add(loader.getRoot());
    }

    public void goViewOnDialog(String viewName, StackPane sp){
        goViewOnDialog(viewName, sp, null);
    }
    
    public void goViewOnDialog(String viewName, StackPane sp, Card card){
        FXMLLoader loader = getLoader(viewName);
        JFXDialogLayout dialogLay = loader.getRoot();
        DialogController dialogCntrl = loader.getController();
        if(card!=null){
            dialogCntrl.setCard(card);
        }
        dialogCntrl.initialize();
        StackPane dialogSP = sp;
        JFXDialog dialog = new JFXDialog(dialogSP, dialogLay, JFXDialog.DialogTransition.CENTER, true);
        dialogSP.setVisible(true);
        dialog.setOnDialogClosed(event -> {
            dialogSP.setVisible(false);
            showingDialog = false;
            event.consume();
        });
        showingDialog = true;
        dialog.show();
    }
    
    public void showInfoDialog(String title, String body, Consumer<MouseEvent> eventOk, StackPane sp){
        showDialog(title, body, CustomDialogController.TYPE.INFORMATION, eventOk, null, sp);
    }
    
    public void showQuestionDialog(String title, String body, Consumer<MouseEvent> eventOk, Consumer<MouseEvent> eventNo, StackPane sp){
        showDialog(title, body, CustomDialogController.TYPE.QUESTION, eventOk, eventNo, sp);
    }
    
    public void showErrorDialog(String title, String body, Consumer<MouseEvent> eventOk, StackPane sp){
        showDialog(title, body, CustomDialogController.TYPE.ERROR, eventOk, null, sp);
    }
    
    private void showDialog(String title, String body, CustomDialogController.TYPE type, Consumer<MouseEvent> eventOk, Consumer<MouseEvent> eventNo, StackPane sp){
        FXMLLoader loader = getLoader("customDialog");
        JFXDialogLayout dialogLay = loader.getRoot();
        CustomDialogController controller = (CustomDialogController) loader.getController();     
        controller.setAccion(null);
        controller.setTitle(title);
        controller.setBody(body);
        controller.setType(type);
        controller.setYesBtnAction(eventOk);
        controller.setNoBtnAction(eventNo);
        controller.initialize();
        StackPane dialogSP = sp;
        JFXDialog dialog = new JFXDialog(dialogSP, dialogLay, JFXDialog.DialogTransition.CENTER, true);
        controller.setParent(dialog);
        dialogSP.setVisible(true);
        dialog.setOnDialogClosed(event -> {
            dialogSP.setVisible(false);
            showingDialog = false;
            event.consume();
        });
        showingDialog = true;
        dialog.show();
    }
    
    public void closeDialog(){
        JFXDialog dialog = ((JFXDialog) getDialogsPane().getChildren().get(0));
        dialog.close();
    }
    
    public void closeInternalDialog(String viewName){
        ((DialogController) getLoader(viewName).getController()).closeDialog();
    }
    
    public void closeHambMenu(){
        ((ContainerController) getLoader("Container").getController()).closeMenu();
    }
    
    public void addNewField(CanchaDto field){
        ((CANCHASController) getLoader("CANCHAS").getController()).addField(field);
    }
    
    public StackPane getHolderSP(){
        return ((ContainerController) getLoader("Container").getController()).getHolderPane();
    }
    
    public StackPane getDialogsPane(){
        FXMLLoader loader = getLoader("Container");
        ContainerController aux =  (ContainerController) loader.getController();
        return aux.getDialogsPane();
    }
    
    public Stage getMainStage(){
        return mainStage;
    }

    public Controller getController(String viewName) {
        return getLoader(viewName).getController();
    }

    public static void setIdioma(ResourceBundle idioma) {
        FlowController.idioma = idioma;
    }
    
    public void initialize() {
        this.loaders.clear();
    }

    public void salir() {
        this.mainStage.close();
    }

    public boolean isShowingDialog() {
        return showingDialog;
    }

    public void setShowingDialog(boolean showingDialog) {
        this.showingDialog = showingDialog;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

}
