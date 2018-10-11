/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canchaspz.controller;

import canchaspz.logic.MatchCard;
import canchaspz.logic.PopUpButton;
import canchaspz.model.Cancha;
import canchaspz.model.CanchaDto;
import canchaspz.model.EquipoDto;
import canchaspz.model.Match;
import canchaspz.model.MatchDto;
import canchaspz.util.AppContext;
import canchaspz.util.FlowController;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;

/**
 * FXML Controller class
 *
 * @author Chris
 */
public class HISTORIALController extends Controller implements Initializable {

    @FXML
    private TilePane tpPlayed;
    @FXML
    private TilePane tpUnPlayed;
    @FXML
    private HBox hbBoxTop;

    PopUpButton filter;
    private ArrayList<CanchaDto> fields;
    @FXML
    private AnchorPane root;
    @FXML
    private Label option1Lbl;
    @FXML
    private Label option2Lbl;
    @FXML
    private HBox infoCont1;
    @FXML
    private HBox infoCont2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.fields=new ArrayList();
        this.hbBoxTop.setAlignment(Pos.CENTER_LEFT);
        this.tpPlayed.setVgap(10);
        this.tpUnPlayed.setVgap(10);
    }    

    
    
    @Override
    public void initialize() {
        this.infoCont1.getChildren().clear();
        this.infoCont2.getChildren().clear();
        this.tpPlayed.getChildren().clear();
        this.tpUnPlayed.getChildren().clear();
        if(AppContext.getInstance().getAdmin()!=null)
        initFieldHistory();
        else
            initTeamHistory();
    }
     
    public void initFieldHistory(){
        this.option1Lbl.setText("Disputados");
        this.option2Lbl.setText("Por disputar");
        filter=new PopUpButton("Mis Canchas");
        filter.initPopupBtn(this.hbBoxTop);
        this.hbBoxTop.getChildren().clear();
        this.hbBoxTop.getChildren().add(filter);
        
        List list =  AppContext.getInstance().getAdmin().getCanchaList();
        ArrayList<Cancha> arrayFields=new ArrayList<>();
        arrayFields.addAll(list);
        
        JFXButton ButtonReports=new JFXButton("Reporte");
        ButtonReports.setOnMouseClicked(e->{
            FlowController.getInstance().goViewOnDialog("ProfitReport", FlowController.getInstance().getDialogsPane());
        });
        
        arrayFields.stream().forEach(c->{
            filter.addButton(c.getCanNombre(), e->{
                this.infoCont2.getChildren().clear();
                this.tpPlayed.getChildren().clear();
                this.tpUnPlayed.getChildren().clear();
                loadPlayedMatchs(new CanchaDto(c));
                loadUnPlayedMatchs(new CanchaDto(c));
                AppContext.getInstance().setCanchaActual(new CanchaDto(c));
                this.infoCont2.getChildren().add(ButtonReports);
            }); 
        });
        
        filter.addButton("Todas", event->{
            AppContext.getInstance().setCanchaActual(null);
            this.infoCont2.getChildren().clear();
            this.infoCont2.getChildren().add(ButtonReports);
                this.tpPlayed.getChildren().clear();
                this.tpUnPlayed.getChildren().clear();
                ArrayList<Cancha> arrayListFields=new ArrayList<>();
                arrayListFields.addAll(AppContext.getInstance().getCanchaList());
                
                //por cada cancha carga los partidos
                arrayListFields.stream().map((f) -> {
                    loadPlayedMatchs(new CanchaDto(f));
                return f;
            }).forEachOrdered((f) -> {
                loadUnPlayedMatchs(new CanchaDto(f));
            });
        });
        
    }
 
    public void initTeamHistory(){
        this.option1Lbl.setText("Local");
        this.option2Lbl.setText("Visitante");
        ArrayList<Match> localList = new ArrayList<>();
        ArrayList<Match> visitList = new ArrayList<>();
        List localOrdered=AppContext.getInstance().getEquipo().matchList.stream().sorted((o1,o2)->o1.getMatDate().compareTo(o2.getMatDate())).collect(Collectors.toList());
        List visitOrdered=AppContext.getInstance().getEquipo().matchList1.stream().sorted((o1,o2)->o1.getMatDate().compareTo(o2.getMatDate())).collect(Collectors.toList());
        localList.addAll(localOrdered);
        visitList.addAll(visitOrdered);
        
        localList.stream().filter(match->"s".equals(match.getMatDisputado())).map((m) -> {
            MatchCard card=new MatchCard();
            card.setMatch(new MatchDto(m));
            return card;
        }).map((card) -> {
            card.initCard();
            return card;
        }).forEachOrdered((card) -> {
            this.tpUnPlayed.getChildren().add(card);
        });
        
        visitList.stream().filter(match->"s".equals(match.getMatDisputado())).map((m) -> {
            MatchCard card=new MatchCard();
            card.setMatch(new MatchDto(m));
            return card;
        }).map((card) -> {
            card.initCard();
            return card;
        }).forEachOrdered((card) -> {
            this.tpPlayed.getChildren().add(card);
        });
        loadLocalTeamStats();
        loadVisitTeamStats();
    }
    
    public void loadLocalTeamStats(){
        this.infoCont1.getChildren().clear();
        EquipoDto team=AppContext.getInstance().getEquipo();
        String localAvg= "Rendimiento como local: "+ team.getLocalAvg()+" %";
        this.infoCont1.getChildren().add(new Label(localAvg));
    }
    
    public void loadVisitTeamStats(){
        this.infoCont2.getChildren().clear();
        EquipoDto team=AppContext.getInstance().getEquipo();
        String visitAvg= "Rendimiento como visitante: "+ team.getVisitAvg()+" %";
        this.infoCont2.getChildren().add(new Label(visitAvg));  
    }
    
    public void loadMatches(CanchaDto field){
        List matchesList= field.getMatches();
        ArrayList<Match> matchesArrayList = new ArrayList<>();
        matchesArrayList.addAll(matchesList);
                
        matchesArrayList.stream().map((match) -> {
            MatchCard card=new MatchCard();
            card.setMatch(new MatchDto(match));
            return card;
        }).map((card) -> {
            card.initCard();
            return card;
        }).forEachOrdered((card) -> {
            this.tpPlayed.getChildren().add(card);
        });
    }
    
    public void loadPlayedMatchs(CanchaDto field){
        List matchesList= field.getMatches();
        ArrayList<Match> matchesArrayList = new ArrayList<>();
        matchesArrayList.addAll(matchesList);
        
        matchesArrayList.stream().filter((match) -> ("s".equals(match.getMatDisputado()))).map((match) -> {
            MatchCard card=new MatchCard();
            card.setMatch(new MatchDto(match));
            return card;
        }).map((card) -> {
            card.initCard();
            return card;
        }).forEachOrdered((card) -> {
            this.tpPlayed.getChildren().add(card);
        });
    }
    
    public void loadUnPlayedMatchs(CanchaDto field){
        List matchesList= field.getMatches();
        ArrayList<Match> matchesArrayList = new ArrayList<>();
        matchesArrayList.addAll(matchesList);
        
        matchesArrayList.stream().filter((match) -> ("n".equals(match.getMatDisputado()))).map((match) -> {
            MatchCard card=new MatchCard();
            card.setMatch(new MatchDto(match));
            return card;
        }).map((card) -> {
            card.initUnplayedCard();
            return card;
        }).forEachOrdered((card) -> {
            this.tpUnPlayed.getChildren().add(card);
        });
    }
    
    public void loadFieldStats(){
        
    }
}
