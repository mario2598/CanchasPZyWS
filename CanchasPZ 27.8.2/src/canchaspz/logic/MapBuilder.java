/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canchaspz.logic;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import javafx.beans.property.StringProperty;
import javafx.scene.input.MouseButton;

/**
 *
 * @author Chris
 */
public class MapBuilder implements MapComponentInitializedListener{
    private GoogleMapView gMapView;
    private GoogleMap map;
    private Marker mark = null;
    private double lat, lon;
    private boolean haveLatLon = false;
    private boolean needInitialMaker = false;
    
    public MapBuilder(GoogleMapView mapView){
        this.gMapView = mapView;
    }
    
    public void setLatLon(double lat, double lon){
        this.lat = lat;
        this.lon = lon;
        haveLatLon = true;
    }
    
    public GoogleMap build(){
        if(haveLatLon){
            this.gMapView.addMapInializedListener(this);
            return map;
        } else {
            System.out.println("primero setea las coordenadas del mapa");
            return null;
        }
    } 
    
    public void addMarker(){
        MarkerOptions mo = new MarkerOptions();
        mo.position(map.getCenter());
        mark = new Marker(mo);
        map.addMarker(mark);
    }
    
    public void initMapListener(StringProperty latProp, StringProperty lonProp){
       this.gMapView.setOnMouseClicked(event -> {
            if(event.getButton().equals(MouseButton.SECONDARY)){
                markListener(latProp, lonProp);
            }
        });
    }
    
    private void markListener(StringProperty latProp, StringProperty lonProp){
        map.clearMarkers();
        MarkerOptions mo = new MarkerOptions();
        mo.position(map.getCenter());
        latProp.setValue(String.valueOf(map.getCenter().getLatitude()));
        lonProp.setValue(String.valueOf(map.getCenter().getLongitude()));
        mark = new Marker(mo);
        map.addMarker(mark);
    }
    
    public void setCenter(Double lat, Double lon){
        //todo
    }
    
    public void setInitWithMarker(boolean bool){
        this.needInitialMaker = bool;
    }
    
    @Override
    public void mapInitialized() {
        MapOptions options = new MapOptions();
        options.center(new LatLong(lat, lon))
                .zoomControl(true)
                .zoom(16)
                .overviewMapControl(false)
                .mapType(MapTypeIdEnum.ROADMAP);
        map = gMapView.createMap(options);
        if(this.needInitialMaker)
            addMarker();
    }
    
}
