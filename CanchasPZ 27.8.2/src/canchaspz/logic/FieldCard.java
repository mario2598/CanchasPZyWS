/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canchaspz.logic;

import canchaspz.model.CanchaDto;
import canchaspz.util.AppContext;
import canchaspz.util.FlowController;
import com.jfoenix.controls.JFXRippler;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author Chris
 */
public class FieldCard extends Card {
    //Attributes
    private CanchaDto field;
    private SimpleStringProperty posterUrlProp;
    
    //Constructors
    public FieldCard() {
        super();
    }
    
    public FieldCard(CanchaDto field){
        super();
        this.field = field;
    }
    
    //Methods
    @Override
    public void initCard() {
        if(!this.isInitialized()){
            this.setPrefSize(300, 200);
            this.getChildren().add(initPoster());
            this.getChildren().add(initLabel());
            this.getChildren().add(initRipplerEffect());
            this.setInitialized(true);
        }
    }
    
    private ImageView initPoster(){
        posterUrlProp = field.posterUrl;
        ImageView IV = new ImageView(new Image(field.getCanUrl()));
        IV.setPreserveRatio(false);
        IV.setFitWidth(300);
        IV.setFitHeight(160);
        posterUrlProp.addListener(event -> {
            System.out.println("se cambio la imagen");
            refreshPoster(IV);
        });
        return IV;
    }
    
    private void refreshPoster(ImageView IV){
        IV.setImage(new Image(posterUrlProp.getValue()));
    }
    
    private Label initLabel(){
        Label nameLabel = new Label();
        //Styling se quita cuando haya css
        nameLabel.setStyle("-fx-text-fill: #bcbcbc; -font-size: 20px;");
        nameLabel.setPrefSize(300, 40);
        nameLabel.setLayoutY(160);
        nameLabel.setTextAlignment(TextAlignment.CENTER);
        nameLabel.setAlignment(Pos.CENTER);
        nameLabel.textProperty().bind(field.nombre);
        return nameLabel;
    }
    
    private JFXRippler initRipplerEffect(){
        Label ripLbl = new Label();
        ripLbl.setPrefSize(300, 160);
        ripLbl.setOnMouseClicked((t) -> {
            if(t.getButton().equals(MouseButton.PRIMARY)){
                //Evento para mostrar informacion de la cancha
                AppContext.getInstance().set("showingField", this.field);
                FlowController.getInstance().goViewOnDialog("INFOCANCHA", FlowController.getInstance().getDialogsPane());
            } else {
                //Evento para seleccionar la cancha
                this.toggleSelected();
            }
            t.consume();
        });
        JFXRippler rip = new JFXRippler(ripLbl);
        return rip;
    }
    
    //Setters and Getters
    public CanchaDto getField() {
        return field;
    }

    public void setField(CanchaDto field) {
        this.field = field;
    }
    
}
