/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canchaspz;

import canchaspz.util.FlowController;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author robri
 */
public class CanchasPZ extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
       FlowController.getInstance().InitializeFlow(stage, null);
        stage.setTitle("CanchasPZ");
        FlowController.getInstance().goLogin();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
