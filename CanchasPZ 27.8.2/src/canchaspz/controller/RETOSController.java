/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canchaspz.controller;

import canchaspz.logic.Card;
import canchaspz.logic.PopUpButton;
import canchaspz.logic.RetoCard;
import canchaspz.logic.TeamCard;
import canchaspz.model.Cancha;
import canchaspz.model.Equipo;
import canchaspz.model.EquipoDto;
import canchaspz.model.Reto;
import canchaspz.model.RetoDto;
import canchaspz.service.RetoService;
import canchaspz.util.AppContext;
import canchaspz.util.DateUtil;
import canchaspz.util.EntityManagerHelper;
import canchaspz.util.FlowController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 * FXML Controller class
 *
 * @author Chris
 */
public class RETOSController extends Controller implements Initializable {
    //FXML Attributes
    @FXML
    private TilePane retosTilePane;
    @FXML
    private JFXButton btnMisRetos;
    @FXML
    private JFXButton btnAgregar;
    @FXML
    private JFXComboBox comboCanchas;
     @FXML
    private JFXDatePicker DPdia;
     @FXML
    private JFXButton btnfiltrar;
    
    
    //Attributes
    private ArrayList<Card> teamCards = new ArrayList();
    private List<Reto> retoList ;
    private List<Reto> misRetosList ;
    @FXML
    private BorderPane root;
    EntityManager em = EntityManagerHelper.getInstance().getManager();
    private EntityTransaction et;
    @FXML
    private HBox popBtnsCont;
    private PopUpButton popButton;
    private Boolean canchas = false;
    private Boolean fecha = false;
    
    
    //Initializers
     @Override
    public void initialize(URL url, ResourceBundle rb) {
       this.popButton = new PopUpButton("Filtros");
       this.popBtnsCont.getChildren().add(this.popButton);      
       this.popButton.initPopupBtn(this.popBtnsCont);       
       this.popButton.addButton("Canchas", event ->{
           canchas = true;
           fecha = false;
           DPdia.setVisible(false);
           comboCanchas.setVisible(true);
           btnfiltrar.setVisible(true);
       });
       this.popButton.addButton("Fecha", event ->{
          fecha = true;
          canchas = false;
          DPdia.setVisible(true);
          comboCanchas.setVisible(false);
          btnfiltrar.setVisible(true);
       });
       this.popButton.addButton("Todos", event ->{
           fecha = false;
           canchas = false;
           addBD();
           DPdia.setVisible(false);
           comboCanchas.setVisible(false);
           btnfiltrar.setVisible(false);
       });
        for(Cancha c : AppContext.getInstance().getCanchaList()){
             comboCanchas.getItems().add(c);
         }
    }    

    @Override
    public void initialize() {
         this.teamCards = new ArrayList<>();
        if(retosTilePane!=null){//sin esta condicion me tira error no se por qué
            retosTilePane.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                if(event.getButton().equals(MouseButton.PRIMARY)){
                    deselectAllCards();
                }
                event.consume();
            });
        }
        if("Mis Retos".equals(btnMisRetos.getText())){
           addBD();  
        }
        else{
           misRetos();
        } 
    }
    
    //Methods
         private void filtroCancha(){
        retosTilePane.getChildren().clear();
        
         for(Reto r : AppContext.getInstance().getRetoList()){
            if(r.getCanchaId() == comboCanchas.getValue() && r.getEquipo1Id().getEquId().equals(AppContext.getInstance().getEquipo().getEquId())){
            RetoDto retoDto = new RetoDto(r);
            EquipoDto equDto = new EquipoDto(r.getEquipo1Id());
            RetoCard newCard = new RetoCard();
            newCard.setReto(retoDto);
            newCard.setEquipo(equDto);
            newCard.initCard();
            addTeamCard(newCard);   
            }       
        }
     deselectAllCards();       
    }
    
     private void filtroDate(){
        retosTilePane.getChildren().clear();
        String date;
         for(Reto r : AppContext.getInstance().getRetoList()){
             date = r.getRetoFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString();
             System.out.println(date+" = "+DPdia.getValue().toString());
            if(date == null ? DPdia.getValue().toString() == null : date.equals(DPdia.getValue().toString())  && !Objects.equals(r.getEquipo1Id().getEquId(), AppContext.getInstance().getEquipo().getEquId())){
            RetoDto retoDto = new RetoDto(r);
            EquipoDto equDto = new EquipoDto(r.getEquipo1Id());
            RetoCard newCard = new RetoCard();
            newCard.setReto(retoDto);
            newCard.setEquipo(equDto);
            newCard.initCard();
            addTeamCard(newCard);    
            }       
        }
     deselectAllCards();       
    }
    
    public void addTeamCard(Card newCard){
        this.teamCards.add(newCard);
        this.retosTilePane.getChildren().add(newCard);
    }
    
    public void deselectAllCards(){
        teamCards.stream().filter(card -> card.isSelected())
                          .forEach(card -> card.toggleSelected());
    }
    
    @FXML
    private void filterBtnAction(ActionEvent event) {
        if(canchas){
          filtroCancha();  
        }
        if(fecha){
           filtroDate(); 
        }
        
        
    }

    private void misRetos(){
          retosTilePane.getChildren().clear();
          if("Mis Retos".equals(btnMisRetos.getText())){
            btnMisRetos.setText("Retos");
            misRetosList = AppContext.getInstance().getEquipo().getRetoList();
            for(Reto r : misRetosList){
            RetoDto retoDto = new RetoDto(r);
            EquipoDto equDto = new EquipoDto(r.getEquipo1Id());
            RetoCard newCard = new RetoCard();
            newCard.setReto(retoDto);
            newCard.setEquipo(equDto);
            newCard.initCard();
            addTeamCard(newCard);  
        }
          }
          else{
              btnMisRetos.setText("Mis Retos");
              addBD();
          }  
           deselectAllCards();   
    }
    
    @FXML
    private void sortBtnAction(ActionEvent event) {       
        misRetos();  
    }

        @FXML
    private void addBtnAction(ActionEvent event) {
        FlowController.getInstance().goViewOnDialog("INFORETO", FlowController.getInstance().getDialogsPane());
    }
    
    private void addBD() {
        retosTilePane.getChildren().clear();
        misRetosList = AppContext.getInstance().getRetoList();
        ArrayList<Reto> retosCaducados = new ArrayList<>();
        (new ArrayList<>(AppContext.getInstance().getRetoList())).stream().forEach(r -> {
            if(!(r.getEquipo1Id().getEquId().equals(AppContext.getInstance().getEquipo().getEquId()))){
                Integer diasFaltantes = DateUtil.daysUntil(r.getRetoFecha());
                if(diasFaltantes<0){//Si el dia ya paso
                    retosCaducados.add(r);
                } else if(diasFaltantes==0){//Si el dia es hoy
                    if(r.getRetoHoraFin()<LocalTime.now().getHour()+1){//Si el reto ya paso
                        retosCaducados.add(r);
                    } else {
                        Equipo equ = (new ArrayList<>(AppContext.getInstance().equipoList)).stream()
                        .filter(e -> e.getEquId().equals(r.getEquipo1Id().getEquId()))
                        .findAny()
                        .get();
                        agregarCard(r, equ);
                    }
                } else {//Si el dia no ha pasado ni tampoco es hoy
                    Equipo equ = (new ArrayList<>(AppContext.getInstance().equipoList)).stream()
                        .filter(e -> e.getEquId().equals(r.getEquipo1Id().getEquId()))
                        .findAny()
                        .get();
                    agregarCard(r, equ);
                }
            }
        });
        //Aqui hace falta borrar los retos caducados de la base de datos. no se si asi se hace
        retosCaducados.stream().forEach(r -> {
            RetoService rs = new RetoService();
            rs.removeReto(new RetoDto(r));
        });
    }
    
    private void agregarCard(Reto r, Equipo equ){
        RetoCard newCard = new RetoCard();
        newCard.setReto(new RetoDto(r));
        newCard.setEquipo(new EquipoDto(equ));
        newCard.initCard();
        addTeamCard(newCard);
    }
    

    
}
