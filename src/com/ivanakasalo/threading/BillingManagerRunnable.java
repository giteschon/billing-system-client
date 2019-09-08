/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivanakasalo.threading;

import com.ivanakasalo.bl.BillingManager;
import com.ivanakasalo.utility.MyInitializable;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @author Ivy
 */
public class BillingManagerRunnable  implements Runnable{

   
BillingManager manager= new BillingManager();
    
    public enum Procedure{
    ADD_CATEGORY,EDIT_CATEGORY
    }
  
private Procedure proc;

    public BillingManagerRunnable(Procedure proc) {
        this.proc = proc;
    }


    
    
     @Override
    public void run() {
        synchronized(this){
         if (proc== Procedure.ADD_CATEGORY) {
         // manager.addCategory(category);
         }
        } 
        
       
    }
    
}
