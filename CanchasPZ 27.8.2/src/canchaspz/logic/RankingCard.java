/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canchaspz.logic;

import canchaspz.model.EquipoDto;
import com.jfoenix.controls.JFXButton;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Chris
 */
public class RankingCard extends Card{
    
    private EquipoDto team;
    private Integer position;
    
    @Override
    public void initCard() {
        if(!this.isInitialized()){
            this.setPrefSize(800, 50);
            this.getChildren().add(initPosLbl());
            this.getChildren().add(initLogo());
            this.getChildren().add(initNameLbl());
            this.getChildren().add(initPointsLbl());
            this.getChildren().add(initAvgLbl());
            this.setInitialized(true);
        }
    }
    
    private Label initPosLbl(){
        Label posLbl = new Label();
        posLbl.setPrefHeight(50);
        posLbl.setLayoutX(30);
        posLbl.setText(String.valueOf(position));
        return posLbl;
    }
    
    private ImageView initLogo(){
        ImageView IV;
        try{
            IV = new ImageView(new Image(this.team.getUrl()));
        }catch(NullPointerException | IllegalArgumentException ex){
            //url de la imagen invalido
            IV = new ImageView("canchaspz/resources/DefaulTeamLogo.png");
        }
        IV.setPreserveRatio(false);
        IV.setFitWidth(50);
        IV.setFitHeight(50);
        IV.setLayoutX(85);
        return IV;
    }
    
    private Label initNameLbl(){
        Label nameLbl = new Label();
        nameLbl.setPrefHeight(50);
        nameLbl.setLayoutX(155);
        nameLbl.setText(this.team.getEquNombre());
        return nameLbl;
    }
    
    private Label initPointsLbl(){
        Label nameLbl = new Label();
        nameLbl.setPrefHeight(50);
        nameLbl.setLayoutX(305);
        nameLbl.setText("Puntos: " + String.valueOf(this.team.getEquPts()));
        return nameLbl;
    }
    
    private Label initAvgLbl(){
        Label nameLbl = new Label();
        nameLbl.setPrefHeight(50);
        nameLbl.setLayoutX(425);
        nameLbl.setText("Promedio de rendimiento: " + String.valueOf(this.team.getAvg()) + "%");
        return nameLbl;
    }
    
    public void initInfoBtn(EventHandler<MouseEvent> btnAction){
        JFXButton btn = new JFXButton();
        btn.setPrefSize(125, 30);
        btn.setText("Más información");
        btn.setLayoutY(10);
        btn.setLayoutX(660);
        btn.setOnMouseClicked(btnAction);
        this.getChildren().add(btn);
    }
    
    @Override
    public void selectedStatus(boolean status){
        //Elimino la propiedad de seleccionado
    }
    
    @Override
    public void toggleSelected(){
        //Elimino la propiedad de seleccionado
    }

    public EquipoDto getTeam() {
        return team;
    }

    public void setTeam(EquipoDto team) {
        this.team = team;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
    
}
