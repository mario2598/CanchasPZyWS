/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canchaspz.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;

/**
 * FXML Controller class
 *
 * @author Chris
 */
public class CustomDialogController extends Controller implements Initializable {
    public static enum TYPE {QUESTION, INFORMATION, ERROR};
    
    //FXML Attributes
    @FXML
    private Label titleLbl, bodyLbl;
    @FXML
    private FontAwesomeIconView iconFA;
    @FXML
    private JFXButton yesBtn, noBtn;
    @FXML
    private HBox bntBar;
    
    private TYPE type;
    private JFXDialog parent;
    
    //Initializers
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @Override
    public void initialize() {
        if(!bntBar.getChildren().stream().anyMatch(node -> node.equals(noBtn)))
            bntBar.getChildren().add(noBtn);
        switch(type){
            case QUESTION:
                setIcon(FontAwesomeIcon.QUESTION_CIRCLE, Paint.valueOf("#bcbcbc"));
                break;
            case INFORMATION:
                setIcon(FontAwesomeIcon.INFO_CIRCLE, Paint.valueOf("#bcbcbc"));
                removeNoBtn();
                break;
            case ERROR:
                setIcon(FontAwesomeIcon.EXCLAMATION_CIRCLE, Paint.valueOf("#bcbcbc"));
                removeNoBtn();
                break;
        }
    }
    
    //Methods
    public void setTitle(String titulo){
        titleLbl.setText(titulo);
    }
    
    public void setBody(String cuerpo){
        bodyLbl.setText(cuerpo);
    }
    
    private void setIcon(FontAwesomeIcon iconName, Paint Color){
        iconFA.setIcon(iconName);
        iconFA.setFill(Color);
    }
    
    public void setYesBtnAction(Consumer event){
        yesBtn.setOnMouseClicked(t -> {
            if(t.getButton().equals(MouseButton.PRIMARY)){
                event.accept(t);
                parent.close();
                t.consume();
            }
        });
    }
    
    public void setNoBtnAction(Consumer event){
        noBtn.setOnMouseClicked(t -> {
            if(t.getButton().equals(MouseButton.PRIMARY)){
                event.accept(t);
                parent.close();
                t.consume();
            }
        });
    }

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    public JFXDialog getParent() {
        return parent;
    }

    public void setParent(JFXDialog parent) {
        this.parent = parent;
    }
    
    public void removeNoBtn(){
        bntBar.getChildren().remove(noBtn);
    }

    //Setters & Getters
    
}
