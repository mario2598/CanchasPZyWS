/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canchaspz.controller;

import canchaspz.logic.FieldCard;
import canchaspz.model.Cancha;
import canchaspz.model.CanchaDto;
import canchaspz.service.CanchaService;
import canchaspz.util.AppContext;
import canchaspz.util.FlowController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;

/**
 * FXML Controller class
 *
 * @author Chris
 */
public class CANCHASController extends Controller implements Initializable {
    //FXML Attributes
    @FXML
    private TilePane canchasTilePane;
    @FXML
    private BorderPane root;
    @FXML
    private JFXButton BtnFilter, BtnAdd, BtnEdit, BtnRemove;
    @FXML
    private HBox filterHbox;
    @FXML
    private JFXDatePicker filterDateP;
    @FXML
    private JFXTimePicker filterTimeP;
    @FXML
    private JFXButton filterBtnOk, filterBtnNo;
    
    //Attributes
    private ArrayList<FieldCard> fieldCards;
    private ArrayList<FieldCard> filteredCards;
    private boolean hayFiltro;
    CanchaService cs;
    
    //Initializers
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cs = new CanchaService();
        this.fieldCards = new ArrayList<>();
        if(canchasTilePane!=null){
            canchasTilePane.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                if(event.getButton().equals(MouseButton.PRIMARY)){
                    deselectAllCards();
                }
                event.consume();
            });
        }
        if(AppContext.getInstance().getAdmin()==null){
            BtnAdd.setVisible(false);
            BtnEdit.setVisible(false);
            BtnRemove.setVisible(false);
        } else {
            BtnFilter.setVisible(false);
        }
        loadFieldCards();
        
    }    

    @Override
    public void initialize() {
        if(fieldCards!=null)
            hideFilterOptions();
    }
    
    //Methods
    private void loadFieldCards(){
        if(AppContext.getInstance().getAdmin()!=null){
            ArrayList<Cancha> fieldList = new ArrayList<>(AppContext.getInstance().getAdmin().getCanchaList());
            if(!fieldList.isEmpty()){
                fieldList.stream().forEach(field -> {
                    CanchaDto fieldDto = new CanchaDto(field);
                    addField(fieldDto);
                });
            }
        } else {
            AppContext.getInstance().getCanchaList().stream().forEach(field -> {
                CanchaDto fieldDto = new CanchaDto(field);
                addField(fieldDto);
            });
        }
    }
    
    public void addField(CanchaDto newField){
        FieldCard newCard = new FieldCard();
        newCard.setField(newField);
        newCard.initCard();
        addFieldCard(newCard);
    }
    
    public void addFieldCard(FieldCard newCard){
        this.fieldCards.add(newCard);
        this.canchasTilePane.getChildren().add(newCard);
    }
    
    public void deselectAllCards(){
        fieldCards.stream().filter(card -> card.isSelected())
                          .forEach(card -> card.toggleSelected());
    }
    
    @FXML
    private void filterBtnAction(){
        if(!filterHbox.isVisible())
            filterHbox.setVisible(true);
        else
            hideFilterOptions();
    }

    @FXML
    private void filterBtnOkAction(ActionEvent event) {
        if(filterDateP.getValue()!=null && filterTimeP.getValue()!=null){
            List<FieldCard> auxList = fieldCards.stream()
                    .filter(card -> card.getField().hayCampo(filterDateP.getValue(), filterTimeP.getValue().getHour()))
                    .collect(Collectors.toList());
            this.filteredCards = new ArrayList<>(auxList);
            this.canchasTilePane.getChildren().clear();
            this.canchasTilePane.getChildren().addAll(this.filteredCards);
            AppContext.getInstance().set("FieldFilter", true);
            AppContext.getInstance().set("FieldFilteredDate", filterDateP.getValue());
            AppContext.getInstance().set("FieldFilteredHour", filterTimeP.getValue());
            this.hayFiltro = true;
        } else {
            String title = "Filtrar canchas por espacios disponibles";
            String body = "Selecciona la fecha y hora en la que quieres reservar";
            FlowController.getInstance().showInfoDialog(title, body, e -> {}, FlowController.getInstance().getDialogsPane());
        }
    }

    @FXML
    private void filterBtnNoAction(ActionEvent event) {
        hideFilterOptions();
    }
    
    
    @FXML
    private void addBtnAction(){
        deselectAllCards();
        AppContext.getInstance().set("editingField", null);
        FlowController.getInstance().goViewOnDialog("AddField", FlowController.getInstance().getDialogsPane());
    }
    
    @FXML
    private void editBtnAction(){
        if(fieldCards.stream().filter(card -> card.isSelected()).count()==1){
            CanchaDto field = fieldCards.stream().filter(card -> card.isSelected()).map(FieldCard::getField).findFirst().get();
            deselectAllCards();
            if(field!=null){
                AppContext.getInstance().set("editingField", field);
                FlowController.getInstance().goViewOnDialog("AddField", FlowController.getInstance().getDialogsPane());
            }
        } else {
            String title = "Modificar cancha";
            String body = "Selecciona una sola carta para modificarla. Puedes seleccionar con click derecho";
            Consumer<MouseEvent> eventOk = eventOk1 -> {
                //Evento del boton ACEPTAR
                deselectAllCards();
            };
            FlowController.getInstance().showInfoDialog(title, body, eventOk, FlowController.getInstance().getDialogsPane());
        }
    }
    
    @FXML
    private void deleteBtnAction(){
        ArrayList<FieldCard> toDeleteList = new ArrayList<>(fieldCards.stream().filter(card -> card.isSelected()).collect(Collectors.toList()));
        Integer count = toDeleteList.size();
        String title = "Eliminar canchas", body;
        Consumer<MouseEvent> eventOk, eventNo;
        if(count<=0){
            body = "Selecciona una o varias canchas para eliminarlas. Puedes seleccionarlas con click derecho";
            eventOk = event1 -> {
                //Evento del boton ACEPTAR
                deselectAllCards();
            };
            FlowController.getInstance().showInfoDialog(title, body, eventOk, FlowController.getInstance().getDialogsPane());
        } else if(count == 1) {
            body = "Estás seguro que deseas eliminar la cancha: " + toDeleteList.get(0).getField().getCanNombre() + "?";
            body += "\nSi las eliminas perderas todos los registros de partidos que esta tenga.";
            eventOk = event1 -> {
                //evento del boton ACEPTAR cuando se haya seleccionado una card
                deleteCardList(toDeleteList);
                deselectAllCards();
            };
            eventNo = event2 -> {
                //evento del boton CANCELAR cuando se haya seleccionado una card
                deselectAllCards();
            };
            FlowController.getInstance().showQuestionDialog(title, body, eventOk, eventNo, FlowController.getInstance().getDialogsPane());
        } else {
            body = "Estás seguro que deseas eliminar las "+ String.valueOf(count-1) +" canchas que seleccionaste?";
            body += "\nSi las eliminas perderas todos los registros de partidos que estas tengan.";
            eventOk = event1 -> {
                //evento del boton ACEPTAR cuando se haya seleccionado una card
                deleteCardList(toDeleteList);
                deselectAllCards();
            };
            eventNo = event2 -> {
                //evento del boton CANCELAR cuando se haya seleccionado una card
                deselectAllCards();
            };
            FlowController.getInstance().showQuestionDialog(title, body, eventOk, eventNo, FlowController.getInstance().getDialogsPane());
        }
    }
    
    private void deleteCardList(ArrayList<FieldCard> cards){
        ArrayList<Cancha> adminList = new ArrayList<>(AppContext.getInstance().getAdmin().getCanchaList());
        adminList.removeAll(cards);
        ArrayList<Cancha> completeList = new ArrayList<>(AppContext.getInstance().getCanchaList());
        completeList.removeAll(cards);
        this.canchasTilePane.getChildren().removeAll(cards);
        this.fieldCards.removeAll(cards);
        cards.stream().map(FieldCard::getField).forEach(fieldDto -> {
            cs.eliminarCancha(fieldDto);
        });
    }
    
    private void hideFilterOptions(){
        filterHbox.setVisible(false);
        filterDateP.setValue(null);
        filterTimeP.setValue(null);
        if(hayFiltro){
            this.canchasTilePane.getChildren().clear();
            this.canchasTilePane.getChildren().addAll(this.fieldCards);
            AppContext.getInstance().set("FieldFilter", false);
            hayFiltro = false;
        }
    }
    
    //Setters and Getters
    
    
}
