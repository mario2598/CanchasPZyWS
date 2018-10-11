/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canchaspz.controller;

import canchaspz.logic.MapBuilder;
import canchaspz.model.CanchaDto;
import canchaspz.util.AppContext;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Chris
 */
public class MapController extends DialogController implements Initializable {

    @FXML
    private StackPane mapViewSP;

    private MapBuilder map;
    private GoogleMapView GMapView;
    CanchaDto fieldDto = null;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void initialize() {
        CanchaDto field = (CanchaDto) AppContext.getInstance().get("showingField");
        if(field!=null){
            fieldDto = field;
            mapViewSP.getChildren().clear();
            GMapView = new GoogleMapView();
            mapViewSP.getChildren().add(GMapView);
            map = new MapBuilder(this.GMapView);
            double lat = field.getCanLatitud();
            double lon = field.getCanLongitud();
            map.setLatLon(lat, lon);
            map.setInitWithMarker(true);
            map.build();
        } else {
            System.out.println("showingField vacio en el appContext");
        }
    }
    
}
