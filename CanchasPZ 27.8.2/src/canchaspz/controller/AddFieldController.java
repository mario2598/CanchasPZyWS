/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canchaspz.controller;

import canchaspz.logic.MapBuilder;
import canchaspz.model.Administrador;
import canchaspz.model.AdministradorDto;
import canchaspz.model.Cancha;
import canchaspz.model.CanchaDto;
import canchaspz.service.CanchaService;
import canchaspz.util.AppContext;
import canchaspz.util.FlowController;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import com.lynden.gmapsfx.GoogleMapView;
import java.io.File;
import java.net.URL;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Chris
 */
public class AddFieldController extends DialogController implements Initializable {
    //FXML Attributes
    @FXML
    private Label nameIcon, playersIcon, adressIcon, schedIcon, pricesIcon, phonesIcon, geoPosIcon;
    @FXML
    private JFXTextField nameTxt, adressTxt, phoneTxt, txtMapLat, txtMapLong;
    @FXML
    private JFXComboBox<Integer> playersComBox;
    @FXML
    private JFXTextField dayPriceTxt, nightPriceTxt;
    @FXML
    private JFXTimePicker openTimeP, closeTiimeP;
    @FXML
    private StackPane posterPane;
    @FXML
    private ImageView posterIV;
    @FXML
    private StackPane gMapSP;
    @FXML
    private StackPane dialogsPane;
    
    //Attributes
    private CanchaService service;
    private CanchaDto fieldDto;
    private CanchaDto originalField;
    private String validInfoMssg;
    private Boolean registering;
    private MapBuilder map;
    private GoogleMapView GMapView;
    
    //Initializers
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        service = new CanchaService();
        playersComBox.setItems(FXCollections.observableArrayList(4, 5, 6));
        nameIcon.setTooltip(new Tooltip("Nombre de la cancha"));
        playersIcon.setTooltip(new Tooltip("Numero de jugadores por equipo"));
        adressIcon.setTooltip(new Tooltip("Dirección"));
        schedIcon.setTooltip(new Tooltip("Horas de apertura y clausura diarias"));
        pricesIcon.setTooltip(new Tooltip("Precio de alquiler por hora en colones"));
        phonesIcon.setTooltip(new Tooltip("Teléfono de contacto"));
        geoPosIcon.setTooltip(new Tooltip("Geoposición exacta para mostrar en el mapa"));
        posterIV.setOnMouseClicked((t) -> {
            if(t.getButton().equals(MouseButton.PRIMARY)){
                //Evento para cargar imagen de la cancha
                loadPoster();
            }
            t.consume();
        });
//        nameTxt.setTextFormatter(Formato.getInstance().maxLengthFormat(25));
//        adressTxt.setTextFormatter(Formato.getInstance().maxLengthFormat(40));
//        dayPriceTxt.setTextFormatter(Formato.getInstance().integerFormat(50));
//        nightPriceTxt.setTextFormatter(Formato.getInstance().integerFormat(50));
//        phoneTxt.setTextFormatter(Formato.getInstance().integerFormat(50));
    }    

    @Override
    public void initialize() {
        closeDialog();
        GMapView = new GoogleMapView();
        gMapSP.getChildren().clear();
        gMapSP.getChildren().add(GMapView);
        map = new MapBuilder(this.GMapView);
        if(AppContext.getInstance().get("editingField")!=null){
            originalField = (CanchaDto) AppContext.getInstance().get("editingField");
            fieldDto = new CanchaDto(originalField);
            System.out.println("Editando la cancha con el id: " + fieldDto.getCanID());
            initForEditing();
            registering = false;
        }
        else{
            initForRegistry();
            registering = true;
        }
    }
    
    //Methods
    private void initForRegistry(){
        map.setLatLon(9.373792938840948, -83.70302908113081);
        map.setInitWithMarker(false);
        map.build();
        posterIV.setImage(new Image("canchaspz/resources/DefaultPoster.png"));
        openTimeP.setValue(null);
        closeTiimeP.setValue(null);
        fieldDto = new CanchaDto();
        bindInfo(true);
        txtMapLat.textProperty().setValue("Click derecho al mapa");
        txtMapLong.textProperty().setValue("Click derecho al mapa");
        playersComBox.setValue(5);
        fieldDto.setCanCantJugadores(Long.valueOf(5));
        fieldDto.setCanUrl("canchaspz/resources/DefaultPoster.png");
        map.initMapListener(txtMapLat.textProperty(), txtMapLong.textProperty());
    }
    
    private void initForEditing(){  
        double lat = fieldDto.getCanLatitud();
        double lon = fieldDto.getCanLongitud();
        map.setLatLon(lat, lon);
        map.setInitWithMarker(true);
        map.build();
        posterIV.setImage(new Image(fieldDto.getCanUrl()));
        openTimeP.setValue(LocalTime.of(Math.toIntExact(fieldDto.getCanAbre()), 0));
        closeTiimeP.setValue(LocalTime.of(Math.toIntExact(fieldDto.getCanCierra()), 0));
        playersComBox.setValue(Math.toIntExact(fieldDto.getCanCantJugadores()));
        bindInfo(true);
        map.initMapListener(txtMapLat.textProperty(), txtMapLong.textProperty());
    }
    
    @FXML
    private void saveBtnAction(MouseEvent event) {
        if(validInfo()){
            if(registering){
                fieldDto.setAdmId(new Administrador(AppContext.getInstance().getAdmin()));
                //Se crea la cancha en la base de datos
                if(service.guardarCancha(fieldDto)){
                    Cancha field = new Cancha(fieldDto);
                    //Se crea la cancha en la lista de canchas del admin
                    AdministradorDto admin = (AdministradorDto) AppContext.getInstance().getAdmin();
                    admin.getCanchaList().add(field);
                    //Se crea la cancha en la lista de canchas del AppContext
                    AppContext.getInstance().getCanchaList().add(field);
                    //Se agrega la card a la seccion de canchas
                    FlowController.getInstance().addNewField(fieldDto);
                } else{
                    String title = "Error guardando la cancha";
                    String body = "Se produjo un error guardando la cancha, intenta mas tarde";
                    FlowController.getInstance().showInfoDialog(title, body, e -> {}, dialogsPane);
                }
            } else {
//                Actualizar la cancha en base de datos
                if(service.guardarCancha(fieldDto)){
                    //Actualiza la cancha en la lista del admin
                    AdministradorDto admin = (AdministradorDto) AppContext.getInstance().getAdmin();
                    ArrayList<Cancha> fieldList = new ArrayList<>(admin.getCanchaList());
                    Cancha match = fieldList.stream().filter(match2 -> match2.getCanId().equals(fieldDto.getCanID())).findFirst().get();
                    match.refreshData(fieldDto);
                    //Actualiza la cancha en la lista de canchas del appcontext
                    fieldList = new ArrayList<>(AppContext.getInstance().getCanchaList());
                    match = fieldList.stream().filter(match3 -> match3.getCanId().equals(fieldDto.getCanID())).findFirst().get();
                    match.refreshData(fieldDto);
                    //Actualizo el dto del card
                    originalField.refreshData(fieldDto);
                } else {
                    String title = "Error guardando cambios";
                    String body = "Se produjo un error guardando los cambios, intenta mas tarde";
                    FlowController.getInstance().showInfoDialog(title, body, e -> {}, dialogsPane);
                }
            }
            FlowController.getInstance().closeDialog();
        } else{
            String title = "Datos faltantes";
            String body = validInfoMssg;
            Consumer<MouseEvent> eventOk = eventOk1 -> {
                //Evento del boton ACEPTAR
            };
            FlowController.getInstance().showInfoDialog(title, body, eventOk, dialogsPane);
        }
    }

    @FXML
    private void cancelBtnAction(MouseEvent event) {
        if(!registering){
            //evento para revertir los cambios
        }
        FlowController.getInstance().closeDialog();
    }
    
    private void loadPoster(){
        String newPosterURI;
        File directory = new File(System.getProperty("user.home") + "\\Pictures");
        if (!directory.exists()){
            directory.mkdirs();
        }
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imágenes", "*.jpg", "*.png"));
        fc.setInitialDirectory(directory);
        File selectedFile = fc.showOpenDialog(FlowController.getInstance().getMainStage());
        if(selectedFile != null){
            newPosterURI = selectedFile.toURI().toString();
            fieldDto.setCanUrl(newPosterURI);
            posterIV.setImage(new Image(newPosterURI));
        }
    }
    
    private void bindInfo(boolean bind){
        ChangeListener<Integer> playersCB = (observable, oldValue, newValue) -> fieldDto.setCanCantJugadores(Long.valueOf(newValue));
        ChangeListener<LocalTime> openH = (observable, oldValue, newValue) -> {
            if(newValue!=null) 
                fieldDto.setCanAbre(Long.valueOf(newValue.getHour()));
        };
        ChangeListener<LocalTime> closeH = (observable, oldValue, newValue) -> {
            if(newValue!=null)
                fieldDto.setCanCierra(Long.valueOf(newValue.getHour()));
        };
        if(bind){
            nameTxt.textProperty().bindBidirectional(fieldDto.nombre);
            adressTxt.textProperty().bindBidirectional(fieldDto.direccion);
            phoneTxt.textProperty().bindBidirectional(fieldDto.tel);
            dayPriceTxt.textProperty().bindBidirectional(fieldDto.precioDia);
            nightPriceTxt.textProperty().bindBidirectional(fieldDto.precioNoches);
            txtMapLat.textProperty().bindBidirectional(fieldDto.latitud);
            txtMapLong.textProperty().bindBidirectional(fieldDto.longitud);
            playersComBox.valueProperty().addListener(playersCB);
            openTimeP.valueProperty().addListener(openH);
            closeTiimeP.valueProperty().addListener(closeH);
        } else {
            nameTxt.textProperty().unbindBidirectional(fieldDto.nombre);
            adressTxt.textProperty().unbindBidirectional(fieldDto.direccion);
            phoneTxt.textProperty().unbindBidirectional(fieldDto.tel);
            dayPriceTxt.textProperty().unbindBidirectional(fieldDto.precioDia);
            nightPriceTxt.textProperty().unbindBidirectional(fieldDto.precioNoches);
            txtMapLat.textProperty().unbindBidirectional(fieldDto.latitud);
            txtMapLong.textProperty().unbindBidirectional(fieldDto.longitud);
            playersComBox.valueProperty().removeListener(playersCB);
            openTimeP.valueProperty().removeListener(openH);
            closeTiimeP.valueProperty().removeListener(closeH);
        }
    }
    
    private boolean validInfo(){
        validInfoMssg = "";
        String mssg = "Faltaron datos de:";
        boolean completed = true;
        if(nameTxt.textProperty().getValue()==null){
            mssg += " - Nombre";
            completed = false;
        }
        if(adressTxt.textProperty().getValue()==null){
            mssg += " - Dirección";
            completed = false;
        }
        if(playersComBox.getValue()==null){
            mssg += " - Cantidad de jugadores";
            completed = false;
        }
        if(phoneTxt.textProperty().getValue()==null){
            mssg += " - Teléfono";
            completed = false;
        }
        if(dayPriceTxt.textProperty().getValue()==null){
            mssg += " - Precio de día";
            completed = false;
        }
        if(nightPriceTxt.textProperty().getValue()==null){
            mssg += " - Precio de noche";
            completed = false;
        }
        if(openTimeP.getValue()==null){
            mssg += " - Hora de apertura diaria";
            completed = false;
        }
        if(closeTiimeP.getValue()==null){
            mssg += " - Hora de clausura diaria";
            completed = false;
        }
        try{
            Double pruebaLat = Double.valueOf(txtMapLat.getText());
            Double pruebaLong = Double.valueOf(txtMapLong.getText());
        } catch(NumberFormatException ex){
            mssg += " - Posición geográfica en el mapa";
            completed = false;
        }
        mssg += ".";
        if(!completed){
            validInfoMssg = mssg;
        }
        return completed;
    }
    
    @Override
    public void closeDialog(){
        if(!dialogsPane.getChildren().isEmpty()){
            ((JFXDialog) dialogsPane.getChildren().get(0)).close();
            dialogsPane.setVisible(false);
        }
    }
    
    @Override
    public void closingAction(){
        bindInfo(false);
    }
    
    private void addMapListener(){
        
    }
    
    
}
