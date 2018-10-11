/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canchaspz.controller;

import canchaspz.logic.HourCard;
import canchaspz.model.CanchaDto;
import canchaspz.model.Match;
import canchaspz.model.MatchDto;
import canchaspz.util.AppContext;
import canchaspz.util.DateUtil;
import canchaspz.util.FlowController;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;

/**
 * FXML Controller class
 *
 * @author Chris
 */
public class INFOCANCHAController extends DialogController implements Initializable {
    @FXML
    private ImageView posterIV;
    @FXML
    private Label fieldNameLbl;
    @FXML
    private Label playersIcon, playersLbl;
    @FXML
    private Label adressIcon, adressLbl;
    @FXML
    private Label schedIcon, openLbl, closeLbl;
    @FXML
    private Label pricesIcon, dayPriceLbl, nightPriceLbl;
    @FXML
    private Label phonesIcon, phone1Lbl, phone2Lbl;
    @FXML
    private StackPane dialogSP;
    @FXML
    private JFXDatePicker datePicker;
    @FXML
    private TilePane hoursTP;
    
    //Attributes
    private CanchaDto fieldDto;
    
    
    //Initializers
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        playersIcon.setTooltip(new Tooltip("Numero de jugadores por equipo"));
        adressIcon.setTooltip(new Tooltip("Dirección (Tocar icono para ver en mapa)"));
        schedIcon.setTooltip(new Tooltip("Horas de apertura y clausura diarias"));
        pricesIcon.setTooltip(new Tooltip("Precio de alquiler por hora en colones"));
        phonesIcon.setTooltip(new Tooltip("Teléfono de contacto"));
        adressIcon.setOnMouseClicked(event -> {
            if(event.getButton().equals(MouseButton.PRIMARY)){
                dialogSP.setVisible(true);
                FlowController.getInstance().goViewOnDialog("map", dialogSP);
                event.consume();
            }
        });
        datePicker.valueProperty().addListener(listener -> {
            hoursTP.getChildren().clear();
            closeDialog();
            dialogSP.setVisible(false);
            loadAgenda();
        });
    }    

    @Override
    public void initialize() {
        CanchaDto field = (CanchaDto) AppContext.getInstance().get("showingField");
        if(field!=null){
            fieldDto = field;
            loadInfo();
        } else {
            System.out.println("showingField vacio en el appContext");
        }
    }
    
    //Methods
    private void loadInfo(){
        posterIV.setImage(new Image(fieldDto.getCanUrl()));
        fieldNameLbl.textProperty().bind(fieldDto.nombre);
        adressLbl.textProperty().bind(fieldDto.direccion);
        phone1Lbl.textProperty().bind(fieldDto.tel);
        playersLbl.setText(fieldDto.getCanCantJugadores() + " jugadores por equipo");
        openLbl.setText("Abre: " + DateUtil.Hour2String(fieldDto.getCanAbre()));
        closeLbl.setText("Cierra: " + DateUtil.Hour2String(fieldDto.getCanCierra()));
        dayPriceLbl.setText("Día : " + fieldDto.getCanPrecioDia());
        nightPriceLbl.setText("Noche: " + fieldDto.getCanPrecioNoches());
        phone2Lbl.setText("");
        try{
            if(((Boolean) AppContext.getInstance().get("FieldFilter")))
                datePicker.setValue(((LocalDate) AppContext.getInstance().get("FieldFilteredDate")));
            else
                datePicker.setValue(LocalDate.now());
        } catch(NullPointerException ex){
            datePicker.setValue(LocalDate.now());
        }
    }
    
    private void loadAgenda(){
        Long open = fieldDto.getCanAbre(), close = fieldDto.getCanCierra();
        for(int i = 0; i < 24; i++){
            if(open<close){
                if(open <= i && i < close)
                    addHour(i);
            } else if(open>close){
                if(!(close <= i && i < open))
                    addHour(i);
            }
        }
    }
    
    private void addHour(Integer hour){
        final Integer h = hour;
        HourCard hc = new HourCard(h);
        hc.setField(fieldDto);
        hc.initCard();
        hc.initButton1("Reservar", event -> {
            Integer difD = DateUtil.daysUntil(datePicker.getValue());
            Integer difH = DateUtil.hoursUntil(h);
            boolean admin = AppContext.getInstance().getAdmin()!=null;
            if(admin || difD>0 || (difD==0 && difH>0)){//true si es admin, si la fecha del datepicker es un dia futuro, o si es presente con mas de 1 hora de antelacion
                if(hc.isAvailable()){
                    //Evento para reservar a la hora i
                    MatchDto match = new MatchDto();
                    match.setMatDate(datePicker.getValue());
                    match.setMatHora(h.longValue());
                    match.setMatCobro(h<17 ? fieldDto.getCanPrecioDia():fieldDto.getCanPrecioNoches());
                    hc.setMatch(match);
                    FlowController.getInstance().goViewOnDialog("AddMatch", dialogSP, hc);
                } else {
                    //Evento para informar sobre espacio ya reservado
                    String title = "Campo no disponible";
                    String body = "La cancha ya esta reservada para este día a las " + DateUtil.Hour2String(h);
                    Consumer<MouseEvent> eventOk = eventOk1 -> {
                        //Evento del boton ACEPTAR
                    };
                    FlowController.getInstance().showInfoDialog(title, body, eventOk, dialogSP);
                }
            } else {
                //Evento para informar sobre espacio ya reservado
                String title = "Campo no disponible";
                String body = "Solo puedes reservar con mínimo una hora de antelación";
                Consumer<MouseEvent> eventOk = eventOk1 -> {
                    //Evento del boton ACEPTAR
                };
                FlowController.getInstance().showInfoDialog(title, body, eventOk, dialogSP);
            }
            event.consume();
        });
        hc.initButton2("Ver reserva", event -> {
            //Evento para ver informacion de la hora de la agenda seleccionada
            if(this.fieldDto.getMatchArrayList().stream().anyMatch(match -> DateUtil.Date2LocalDate(match.getMatDate()).equals(datePicker.getValue()) 
                    && match.getMatHora().equals(h.longValue()))){
                //Si hay un partido para mostrar
                FlowController.getInstance().goViewOnDialog("AddMatch", dialogSP, hc);
            }
            else{
                //Evento para informar que no hay ningun partido programado para esa hora
                String title = "Campo disponible";
                String body = "No hay ningun partido programado para este día a las " + DateUtil.Hour2String(h);
                Consumer<MouseEvent> eventOk = eventOk1 -> {
                    //Evento del boton ACEPTAR
                };
                FlowController.getInstance().showInfoDialog(title, body, eventOk, dialogSP);
            }
            event.consume();
        });
        if(fieldDto.getMatchArrayList().stream().anyMatch(match -> DateUtil.Date2LocalDate(match.getMatDate()).equals(datePicker.getValue()) 
                && match.getMatHora().equals(h.longValue()))){
            hc.setAvailable(false);
            Match match = fieldDto.getMatchArrayList().stream()
                    .filter(match2 -> DateUtil.Date2LocalDate(match2.getMatDate()).equals(datePicker.getValue()) && match2.getMatHora().equals(h.longValue()))
                    .findFirst()
                    .get();
            hc.setMatch(new MatchDto(match));
        } else {
            hc.setAvailable(true);
            hc.initButton3("Ver retos", event -> {
                FlowController.getInstance().closeInternalDialog("INFOCANCHA");
                FlowController.getInstance().closeDialog();
                FlowController.getInstance().goView("RETOS");
                event.consume();
            });
            if(fieldDto.getRetoArrayList().stream().anyMatch(match -> DateUtil.Date2LocalDate(match.getRetoFecha()).equals(datePicker.getValue()) 
                                                            && match.getRetoHoraIni()<= h.longValue() && match.getRetoHoraFin()>= h.longValue())){
                
                hc.retoDisponible(true);
            }
        }
        this.hoursTP.getChildren().add(hc);
    }
    
    @Override
    public void closeDialog(){
        if(!dialogSP.getChildren().isEmpty()){
            ((JFXDialog) dialogSP.getChildren().get(0)).setOnDialogClosed(event -> {
                dialogSP.setVisible(false);
                event.consume();
            });
            ((JFXDialog) dialogSP.getChildren().get(0)).close();
        }
    }
    
    //Setters and Getters
    
}