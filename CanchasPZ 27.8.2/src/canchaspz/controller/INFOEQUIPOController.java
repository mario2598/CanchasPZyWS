/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canchaspz.controller;

import canchaspz.logic.MatchCard;
import canchaspz.model.EquipoDto;
import canchaspz.model.Match;
import canchaspz.model.MatchDto;
import canchaspz.util.AppContext;
import com.jfoenix.controls.JFXDialogLayout;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

/**
 * FXML Controller class
 *
 * @author robri
 */
public class INFOEQUIPOController extends DialogController implements Initializable {

    @FXML
    private Label playedMatchsLbl;
    @FXML
    private Label sumLbl;
    @FXML
    private Label avrgLbl;
    @FXML
    private Label posLbl;
    @FXML
    private ImageView imgLogo;
    @FXML
    private Label teamNameLbl;
    @FXML
    private TilePane historyTP;
    
    private EquipoDto team;
    private SimpleStringProperty playedMatchs;
    private SimpleStringProperty wonMatchs;
    private SimpleStringProperty drawMatchs;
    private SimpleStringProperty looseMatchs;
    private SimpleStringProperty totalPoints;
    private SimpleStringProperty pos;
    private SimpleStringProperty average;
    @FXML
    private JFXDialogLayout root;
    @FXML
    private Label wonMatchsLbl;
    @FXML
    private Label drawMatchsLbl;
    @FXML
    private Label looseMatchsLbl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.average = new SimpleStringProperty();
        this.playedMatchs = new SimpleStringProperty();
        this.wonMatchs = new SimpleStringProperty();
        this.drawMatchs = new SimpleStringProperty();
        this.looseMatchs = new SimpleStringProperty();
        this.pos = new SimpleStringProperty();
        this.totalPoints = new SimpleStringProperty();
        
    }   
    
    @Override
    public void initialize() {
        this.team=AppContext.getInstance().getEquipo();
        unbind();
        loadTeamInfo();
        this.historyTP.getChildren().clear();
        loadTeamHistory();
        bind();
        
    }
    
    /**
     * asingna valores a los properties
     */
    public void loadTeamInfo(){
        HashMap<String,Integer> info=this.team.getHistoryInfo();
        this.imgLogo.setImage(new Image(this.team.getUrl()));
        this.playedMatchs.set(info.get("played").toString());
        this.totalPoints.set(info.get("points").toString());
        this.average.set(info.get("avg").toString()+"%");
        this.wonMatchs.set(info.get("won").toString());
        this.drawMatchs.set(info.get("draw").toString());
        this.looseMatchs.set(info.get("loose").toString());
        this.pos.set("0");
    }
    
    public void bind(){
        this.teamNameLbl.textProperty().bind(team.equNombre);
        this.playedMatchsLbl.textProperty().bind(this.playedMatchs);
        this.wonMatchsLbl.textProperty().bind(this.wonMatchs);
        this.drawMatchsLbl.textProperty().bind(this.drawMatchs);
        this.looseMatchsLbl.textProperty().bind(this.looseMatchs);
        this.sumLbl.textProperty().bind(this.totalPoints);
        this.avrgLbl.textProperty().bind(this.average);
        this.posLbl.textProperty().bind(this.pos);
    }
    
    public void unbind(){
        this.teamNameLbl.textProperty().unbind();
        this.playedMatchsLbl.textProperty().unbind();
        this.sumLbl.textProperty().unbind();
        this.avrgLbl.textProperty().unbind();
        this.posLbl.textProperty().unbind();
    }
    
    public void loadTeamHistory(){
        ArrayList<Match> localList = new ArrayList<>();
        ArrayList<Match> visitList = new ArrayList<>();
        List localOrdered=this.team.matchList.stream().sorted((o1,o2)->o1.getMatDate().compareTo(o2.getMatDate())).collect(Collectors.toList());
        List visitOrdered=this.team.matchList1.stream().sorted((o1,o2)->o1.getMatDate().compareTo(o2.getMatDate())).collect(Collectors.toList());
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
            this.historyTP.getChildren().add(card);
        });
        
        visitList.stream().filter(match->"s".equals(match.getMatDisputado())).map((m) -> {
            MatchCard card=new MatchCard();
            card.setMatch(new MatchDto(m));
            return card;
        }).map((card) -> {
            card.initCard();
            return card;
        }).forEachOrdered((card) -> {
            this.historyTP.getChildren().add(card);
        });
        
    }

    public void pirntInfo(){
        
    }
}
