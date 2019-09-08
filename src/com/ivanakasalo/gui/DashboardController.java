/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivanakasalo.gui;

import com.ivanakasalo.reflection.Reflection;
import com.ivanakasalo.threading.UserActionHistory;
import com.ivanakasalo.model.Item;
import com.ivanakasalo.utility.MyInitializable;
import com.ivanakasalo.utility.ReaderWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author Ivana
 */
public class DashboardController implements MyInitializable {
    
      public static ArrayList<ArrayList<Item>> list= new ArrayList<>();
@FXML
    private ListView<String> lwHistory;
      
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Thread t= new Thread(new UserActionHistory(lwHistory));
        t.start();
    }    
    
    
    
     @FXML
    void btnGenerateHtmlOnAction(ActionEvent event) throws IOException {
 Reflection reflection= new Reflection();
 String html= reflection.generateHtml("Billing System Reflection Documentation");
 
         ReaderWriter.writeHtml(html);
    }
    
    @FXML
    void btnSerialisedDataOnAction(ActionEvent event) throws ClassNotFoundException {
list= ReaderWriter.readSerialization();
        for (ArrayList<Item> items : list) {
//            items.forEach(i-> manager.addInvoice(i.getInvoice()));
//            items.forEach(i-> manager.addClient(i.getInvoice().getClient()));
//manager.addClient(items.get(0).getInvoice().getClient());
manager.addInvoice2(items.get(0).getInvoice());
            System.out.println(items.get(0).getInvoice());
            
            //serialization -> invoice controller -> open invoice controller
        }
    }
    
}
