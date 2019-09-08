/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivanakasalo.threading;

import com.ivanakasalo.bl.BillingManager;
import com.ivanakasalo.model.Category;

/**
 *
 * @author Ivy
 */
public class CategoryThread extends Thread{
    
     public enum Procedure{
    ADD_CATEGORY,EDIT_CATEGORY
    }
    
     private Procedure proc;
     private BillingManager manager;
     private Category category;

  
    public CategoryThread(Procedure proc, BillingManager manager, Category category) {
        this.proc = proc;
        this.manager = manager;
        this.category = category;
    }
     
    
     
     
     
      @Override
    public void run() {
        synchronized(this){
         if (proc== Procedure.ADD_CATEGORY) {
         manager.addCategory(category.getName());
         }
         
         else{
         manager.editCategory(category.getName(),category.getIdCategory());
         }
             
         
        } 
    
}
    
    
}
