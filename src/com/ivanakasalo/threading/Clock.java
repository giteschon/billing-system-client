/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivanakasalo.threading;

import com.ivanakasalo.gui.LoginController;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Label;

/**
 *
 * @author Ivy
 */

//timertask ->java 7 u sebi ima runnable
public class Clock implements Runnable{
    
    private Label lblTime;


    public Clock(Label lblTime) {
        this.lblTime = lblTime;
    }   
  
    @Override
    public void run() {
       
            while (true) { 
                //postavi vrijeme na sadasnje
            LocalTime time= LocalTime.now();
            //format sata
            DateTimeFormatter format= DateTimeFormatter.ofPattern("HH:mm:ss");
             //pokreni se u neodredenom trenutku              
                Platform.runLater(new Runnable() {
                @Override
                public void run() {
                   
                        //postavi tekst
                lblTime.setText(format.format(time));
               
              }
            });
                
                                   
               
//lblTime.setText(time.toString());
//pricekaj 1 sek ikrenu ispocetka
try {                   
    //osvjezavanje svakiu sec   
 Thread.sleep(1000);
            }   catch (InterruptedException ex) {
                    Logger.getLogger(Clock.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
     
                }
            
        

//while (running) {
//                Calendar kalendar = Calendar.getInstance();
//                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//                Platform.runLater(() -> lblTime.setText(sdf.format(kalendar.getTime())));
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException ex) {
//                    //System.out.println(getName() + ": interrupted, will check if need to be terminated");
//                    ex.printStackTrace();
//                }
            //}
//            Platform.runLater(() -> {
//            LocalTime trenutnoVrijeme = LocalTime.now();
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
//            String vrijemeTekst = formatter.format(trenutnoVrijeme);
//            lblTime.setText(vrijemeTekst);
       // });
    }
    
    }
    
   
       
    
}
