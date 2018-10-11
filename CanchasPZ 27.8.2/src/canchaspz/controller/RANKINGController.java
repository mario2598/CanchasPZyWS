/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canchaspz.controller;

import canchaspz.logic.RankingCard;
import canchaspz.model.Equipo;
import canchaspz.model.EquipoDto;
import canchaspz.util.AppContext;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.TilePane;

/**
 * FXML Controller class
 *
 * @author Chris
 */
public class RANKINGController extends Controller implements Initializable {

    @FXML
    private TilePane rankingTilePane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void initialize() {
        this.rankingTilePane.getChildren().clear();
        int position = 0;
        ArrayList<Equipo> sortedList = new ArrayList<>(AppContext.getInstance().equipoList);
        Comparator<Equipo> sorter = (team1, team2) -> {
            if(team1.getEquPts().compareTo(team2.getEquPts())!=0){
                return team1.getEquPts().compareTo(team2.getEquPts());
            } else {
                //TODO Comparar promedios de rendimiento en caso de que los puntos sean iguales
                return 0;
            }
        };
        sortedList.sort(sorter);
        for(Equipo team : sortedList){
            position++;
            RankingCard rc = new RankingCard();
            rc.setPosition(position);
            rc.setTeam(new EquipoDto(team));
            rc.initCard();
            rc.initInfoBtn(event -> {
                //Evento para ver la informacion del equipo
                event.consume();
            });
            this.rankingTilePane.getChildren().add(rc);
        }
    }
    
}
