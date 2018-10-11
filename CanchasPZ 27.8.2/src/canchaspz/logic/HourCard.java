/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canchaspz.logic;

import canchaspz.model.CanchaDto;
import canchaspz.model.MatchDto;
import canchaspz.util.DateUtil;
import com.jfoenix.controls.JFXButton;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Chris
 */
public class HourCard extends Card{
    private Integer hour;
    private MatchDto match;
    private CanchaDto field;
    private boolean available = true;
    private JFXButton dynamicBtn;

    public HourCard(Integer hour){
        this.hour = hour;
        this.match = new MatchDto();
        this.available = true;
    }
    
    //this.get
    @Override
    public void initCard() {
        if(!this.isInitialized()){
            this.setPrefSize(425, 40);
            this.getChildren().add(initSeparator());
            setAvailable(true);
            this.getChildren().add(initLabel());
            this.setInitialized(true);
        }
    }
    
    public void setAvailable(boolean bool){
        this.available = bool;
        if(bool){
            this.getChildren().get(0).setId("separator-green");
        } else {
            this.getChildren().get(0).setId("separator-red");
        }
    }
    
    public void retoDisponible(boolean bool){
        if(this.dynamicBtn!=null){
            if(bool){
                this.getChildren().get(0).setId("separator-yellow");
                dynamicBtn.setVisible(true);
            }
            else{
                dynamicBtn.setVisible(false);
                setAvailable(this.available);
            }
        }
    }
    
    private Label initLabel(){
        Label nameLabel = new Label();
        nameLabel.setLayoutX(10);
        nameLabel.setLayoutY(5);
        nameLabel.setText(DateUtil.Hour2String(hour));
        return nameLabel;
    }
    
    private Separator initSeparator(){
        Separator stateSep=new Separator();
        stateSep.setPrefSize(7,30);
        stateSep.setMaxSize(7,30);
        stateSep.setMinSize(7,30);
        stateSep.setTranslateX(-7);
        return stateSep;
    }
    
    public void initButton1(String text, EventHandler<MouseEvent> btnAction){
        JFXButton btn = new JFXButton();
        btn.setPrefSize(85, 30);
        btn.setText(text);
        btn.setLayoutY(5);
        btn.setLayoutX(335);
        btn.setOnMouseClicked(btnAction);
        this.getChildren().add(btn);
    }
    
    public void initButton2(String text, EventHandler<MouseEvent> btnAction){
        JFXButton btn = new JFXButton();
        btn.setPrefSize(85, 30);
        btn.setText(text);
        btn.setLayoutY(5);
        btn.setLayoutX(240);
        btn.setOnMouseClicked(btnAction);
        this.getChildren().add(btn);
    }
    
    public void initButton3(String text, EventHandler<MouseEvent> btnAction){
        this.dynamicBtn = new JFXButton();
        this.dynamicBtn.setPrefSize(85, 30);
        this.dynamicBtn.setText(text);
        this.dynamicBtn.setLayoutY(5);
        this.dynamicBtn.setLayoutX(145);
        this.dynamicBtn.setOnMouseClicked(btnAction);
        this.dynamicBtn.setVisible(false);
        this.getChildren().add(this.dynamicBtn);
    }
    
    @Override
    public void selectedStatus(boolean status){
        //Elimino la propiedad de seleccionado
    }
    
    @Override
    public void toggleSelected(){
        //Elimino la propiedad de seleccionado
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public boolean isAvailable() {
        return available;
    }

    public MatchDto getMatch() {
        return match;
    }

    public void setMatch(MatchDto match) {
        this.match = match;
    }

    public CanchaDto getField() {
        return field;
    }

    public void setField(CanchaDto field) {
        this.field = field;
    }
    
}
