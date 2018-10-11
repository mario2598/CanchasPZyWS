/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canchaspz.logic;

import canchaspz.model.EquipoDto;
import canchaspz.model.RetoDto;
import canchaspz.util.AppContext;
import canchaspz.util.FlowController;
import com.jfoenix.controls.JFXRippler;
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
public class RetoCard extends Card{
    //Attributes
    private EquipoDto equipo;
    private RetoDto reto;
    
    //Constructors
    public RetoCard() {
        super();
    }

    public RetoDto getReto() {
        return reto;
    }

    public void setReto(RetoDto reto) {
        this.reto = reto;
    }
    
    public RetoCard(EquipoDto equipo){
        super();
        this.equipo = equipo;
    }
    
    //Methods
    @Override
    public void initCard() {
        if(!this.isInitialized()){
            this.setPrefSize(200, 240);
            this.getChildren().add(initPoster());
            this.getChildren().add(initLabel());
            this.getChildren().add(initRipplerEffect());
            this.setInitialized(true);
        }

    }
    
    private ImageView initPoster(){
        ImageView IV;
        try{
            IV = new ImageView(new Image(this.reto.getEquipo1Id().getEquUrl()));
        }catch(NullPointerException | IllegalArgumentException ex){
            //url de la imagen invalido
            IV = new ImageView("canchaspz/resources/DefaulTeamLogo.png");
        }
        IV.setPreserveRatio(false);
        IV.setFitWidth(200);
        IV.setFitHeight(200);
        return IV;
    }
    
    private Label initLabel(){
        Label nameLabel = new Label();
        //Styling se quita cuando haya css
        nameLabel.setStyle("-fx-text-fill: #bcbcbc; -font-size: 20px;");
        nameLabel.setPrefSize(200, 40);
        nameLabel.setLayoutY(200);
        nameLabel.setTextAlignment(TextAlignment.CENTER);
        nameLabel.setAlignment(Pos.CENTER);
        nameLabel.setText(equipo.getEquNombre());
        return nameLabel;
    }
    
    private JFXRippler initRipplerEffect(){
        Label ripLbl = new Label();
        ripLbl.setPrefSize(200, 200);
        ripLbl.setOnMouseClicked((t) -> {
            if(t.getButton().equals(MouseButton.PRIMARY)){
                //Evento para mostrar informacion del equipo
                AppContext.getInstance().setRetoActual(getReto());
                FlowController.getInstance().goViewOnDialog("challengesInfo", FlowController.getInstance().getDialogsPane(),this);
                
            } else {
                //Evento para seleccionar el equipo
//                this.toggleSelected();
            }
            t.consume();
        });
        JFXRippler rip = new JFXRippler(ripLbl);
        return rip;
    }
    
    //Setters and Getters
    public EquipoDto getEquipo() {
        return equipo;
    }

    public void setEquipo(EquipoDto equipo) {
        this.equipo = equipo;
    }
    
}
