/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canchaspz.logic;

import canchaspz.model.EquipoDto;
import canchaspz.model.MatchDto;
import canchaspz.util.AppContext;
import canchaspz.util.FlowController;
import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author robri
 */
public class MatchCard extends Card{
    
    //private AnchorPane root;
    private HBox root;
    private HBox hbContTeam1;
    private HBox hbContTeam2;
    private VBox vbInfoTeam1;
    private VBox vbInfoTeam2;
    private VBox vbContField;
    MatchDto match;
    EquipoDto team1;
    EquipoDto team2;
    Separator localState;
    Separator visitorState;

    public MatchCard() {
        this.root = new HBox();
        this.hbContTeam1 = new HBox();
        this.hbContTeam2 = new HBox();
        this.vbContField = new VBox();
        this.vbInfoTeam1 = new VBox();
        this.vbInfoTeam2 = new VBox();
        
        
        //this.root=new AnchorPane();
    }
    
    
    @Override
    public void initCard() {
        if(!this.isInitialized()){
            this.root.getStylesheets().clear();
            this.root.getStylesheets().add("canchaspz/view/StyleHCards.css");
            this.vbInfoTeam1.getStyleClass().add("vbox-team-cont");
            this.vbInfoTeam2.getStyleClass().add("vbox-team-cont");
            this.root.setSpacing(40);
            this.hbContTeam1.setSpacing(10);
            this.hbContTeam2.setSpacing(10);
            this.vbContField.setSpacing(5);
            this.root.setId("root");
            this.root.setPrefSize(425, 45);
            initTeam1();
            initField();
            initTeam2();
            initContainers();
            initResultState();
            this.setInitialized(true);
        } 
    }
    
    public void initUnplayedCard() {
        if(!this.isInitialized()){
            this.root.getStylesheets().clear();
            this.root.getStylesheets().add("canchaspz/view/StyleHCards.css");
            this.vbInfoTeam1.getStyleClass().add("vbox-team-cont");
            this.vbInfoTeam2.getStyleClass().add("vbox-team-cont");
            this.root.setSpacing(40);
            this.hbContTeam1.setSpacing(10);
            this.hbContTeam2.setSpacing(10);
            this.vbContField.setSpacing(5);
            this.root.setId("root");
            this.root.setPrefSize(425, 45);
            initTeam1Unplayed();
            initFieldUnplayed();
            initTeam2Unplayed();
            initContainersUnplayed();
            this.setInitialized(true);
        } 
    }
    
    public void initContainers(){
        this.root.getChildren().add(initLocalState());
        this.hbContTeam1.setOnMouseClicked(event->{
            AppContext.getInstance().setEquipo(team1);
            FlowController.getInstance().goViewOnDialog("INFOEQUIPO", FlowController.getInstance().getDialogsPane());
        });
        this.root.getChildren().add(this.hbContTeam1);
        this.root.getChildren().add(this.vbContField);
        this.hbContTeam2.setOnMouseClicked(envent->{
            AppContext.getInstance().setEquipo(team2);
            FlowController.getInstance().goViewOnDialog("INFOEQUIPO", FlowController.getInstance().getDialogsPane());
        });
        this.root.getChildren().add(this.hbContTeam2);
        this.root.getChildren().add(initVisitorState());
        this.getChildren().add(this.root);
    }
    
    public void initContainersUnplayed(){
        this.hbContTeam1.setOnMouseClicked(event->{
            AppContext.getInstance().setEquipo(team1);
            FlowController.getInstance().goViewOnDialog("INFOEQUIPO", FlowController.getInstance().getDialogsPane());
        });
        this.root.getChildren().add(this.hbContTeam1);
        this.root.getChildren().add(this.vbContField);
        this.hbContTeam2.setOnMouseClicked(envent->{
            AppContext.getInstance().setEquipo(team2);
            FlowController.getInstance().goViewOnDialog("INFOEQUIPO", FlowController.getInstance().getDialogsPane());
        });
        this.root.getChildren().add(this.hbContTeam2);
        this.getChildren().add(this.root);
    }
    
    public void initTeam1(){
        this.vbInfoTeam1.getChildren().add(initPoster1());
        this.vbInfoTeam1.getChildren().add(initName1());
        this.hbContTeam1.getChildren().add(this.vbInfoTeam1);
        this.hbContTeam1.getChildren().add(initScore1());
    }
    
    public void initTeam1Unplayed(){
        this.vbInfoTeam1.getChildren().add(initPoster1());
        this.vbInfoTeam1.getChildren().add(initName1());
        this.hbContTeam1.getChildren().add(this.vbInfoTeam1);
        this.hbContTeam1.getChildren().add(initScore1Unplayed());
    }
    
    public void initTeam2(){
        this.vbInfoTeam2.getChildren().add(initPoster2());
        this.vbInfoTeam2.getChildren().add(initName2());
        this.hbContTeam2.getChildren().add(initScore2());
        this.hbContTeam2.getChildren().add(this.vbInfoTeam2);
    }
    
    public void initTeam2Unplayed(){
        this.vbInfoTeam2.getChildren().add(initPoster2());
        this.vbInfoTeam2.getChildren().add(initName2());
        this.hbContTeam2.getChildren().add(initScore2Unplayed());
        this.hbContTeam2.getChildren().add(this.vbInfoTeam2);
    }
    
    public void initField(){
        this.vbContField.getChildren().add(this.initDateL());
        this.vbContField.getChildren().add(this.initHour());
        this.vbContField.getChildren().add(this.initNameField());
    }
    
    public void initFieldUnplayed(){
        this.vbContField.getChildren().add(this.initDateL());
        this.vbContField.getChildren().add(this.initHour());
        this.vbContField.getChildren().add(this.initNameField());
        JFXButton playBtn = new JFXButton("Terminar");
        this.vbContField.getChildren().add(playBtn);
        playBtn.setOnAction(event->{
            System.out.println("jugó partido");
        });
    }
    
    public void initDate(){
        this.root.getChildren().add(initHour());
        this.root.getChildren().add(initDateL());
    }
    
    private Separator initLocalState(){
        //Separator stateSep=new Separator();
        this.localState=new Separator();
        localState.setPrefSize(7,55);
        localState.setMaxSize(7,55);
        localState.setMinSize(7,55);
        //stateSep.setTranslateX(-7);
        
        return localState;
    }
    
    private Separator initVisitorState(){
        this.visitorState=new Separator();
        visitorState.setPrefSize(7,55);
        visitorState.setMaxSize(7,55);
        visitorState.setMinSize(7,55);
        //stateSep.setTranslateX(-7);
        return visitorState;
    }
    
    public ImageView initPoster1(){
        ImageView img1=new ImageView(new Image(this.team1.getUrl()));
        img1.getStyleClass().add("image-view-avatar");
        img1.setFitHeight(50);
        img1.setFitWidth(50);
        return img1;
    }
    
    public ImageView initPoster2(){
        ImageView img2=new ImageView(new Image(this.team2.getUrl()));
        img2.getStyleClass().add("image-view-avatar");
        img2.setFitHeight(50);
        img2.setFitWidth(50);
        img2.setPreserveRatio(false);

        img2.setLayoutX(333);
        img2.setLayoutY(14);
        return img2;
    }
    
    public Label initName1(){
        Label teamName1 = new Label();
        teamName1.getStyleClass().add("label-team-name");
        teamName1.textProperty().bind(this.team1.equNombre);
        return teamName1;
    }
    
    public Label initName2(){
        Label teamName2 = new Label();
        teamName2.getStyleClass().add("label-team-name");
        teamName2.textProperty().bind(this.team2.equNombre);
        return teamName2;
    }
    
    public TextField initScore1Unplayed(){
        TextField score1 = new TextField();
        score1.getStyleClass().add("text-field");
        score1.setPrefSize(70, 40);
        score1.textProperty().bindBidirectional(this.match.matMarcador1);
        return score1;
    }
    
    public TextField initScore2Unplayed(){
        TextField score2 = new TextField();
        score2.getStyleClass().add("text-field");
        score2.setPrefSize(70, 40);
        score2.textProperty().bindBidirectional(this.match.matMarcador2);
        return score2;
    }
    
    public Label initScore1(){
        Label score1 = new Label();
        score1.getStyleClass().add("label-Score");
        score1.textProperty().bind(this.match.matMarcador1);
        return score1;
    }
    
    public Label initScore2(){
        Label score2 = new Label();
        score2.getStyleClass().add("label-Score");
        score2.textProperty().bind(this.match.matMarcador2);
        return score2;
    }
    
    public Label initNameField(){
        Label fieldName = new Label();
        fieldName.setPrefSize(80, 10);
        fieldName.setText(this.match.canId.getCanNombre());
        return fieldName;
    }
    
    public Label initDateL(){
        Label dateLabel = new Label();
        dateLabel.setPrefSize(80, 10);
        dateLabel.setText(this.match.getMatDate().toString());
        return dateLabel;
    }
    
    public Label initHour(){
        Label hourLabel = new Label();
        hourLabel.setPrefSize(80, 10);
        hourLabel.textProperty().bind(this.match.matHora);
        return hourLabel;
    }

    public MatchDto getMatch() {
        return match;
    }

    public void setMatch(MatchDto match) {
        this.match = match;
        this.team1 = new EquipoDto(match.team1);
        this.team2 = new EquipoDto(match.team2);
    }    
   
    public void initResultState(){
        switch(this.match.getMatResultado().intValue()){
            case 0:
                this.localState.setId("separator-yellow");
                this.visitorState.setId("separator-yellow");
                break;
            case 1:
                this.localState.setId("separator-green");
                this.visitorState.setId("separator-red");
                break;
            case 2:
                this.localState.setId("separator-red");
                this.visitorState.setId("separator-green");
                break;
        }
    }
    
}
