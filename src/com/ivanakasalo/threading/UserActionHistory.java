/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivanakasalo.threading;

import com.ivanakasalo.gui.Main;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

/**
 *
 * @author Ivy
 */
public class UserActionHistory implements Runnable{
    
    private ListView<String> lvHistory;
    private static String message="";
    private ObservableList<String> refreshingObservableList= FXCollections.observableArrayList();
    
    private static List<String> listMessages= new ArrayList<>();

    public UserActionHistory(ListView<String> lvHistory) {
        this.lvHistory = lvHistory;
    }
    
    //private static Class c;
    
//action: insert=true, edit=false
    private boolean inserted;
    
    public static void refreshHistory(boolean  inserted, Class className, String actionPerformed){
        
        //c=className.getClass();
        String text= className.getName();
        int lastIndex=text.lastIndexOf(".");
        String getClass=text.substring(lastIndex +1);
        if (inserted) {
            message=Main.currentUser.toString() + " has added a new " + getClass + ": " + actionPerformed;
        }
        else{
            message=Main.currentUser.toString() + " has edited a " + getClass + ", new name is: " + actionPerformed;
        }
           listMessages.add(message);
           
        Collections.reverse(listMessages);
        
    
    }
    
    @Override
    public void run() {
        //znaci ako stavim gore onda se zakljuca
        //sad se svi odjednom pojavljuju sa zakasnjenjem, kako napravit da se jedan po 1 
        try {
            for (String msg : listMessages) {
                //Thread.sleep(10000);
                refreshingObservableList.add(msg);
               
                //radi ali baca exception: ConcurrentModificationException
            
             lvHistory.setItems(refreshingObservableList);   
            }
             
        } catch (Exception e) {
            e.printStackTrace();
        }
        //lvHistory.setItems(FXCollections.observableArrayList(listMessages));
        
        
    }
    
}
