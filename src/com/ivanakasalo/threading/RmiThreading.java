/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivanakasalo.threading;

import com.ivanakasalo.gui.LoginController;


import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.TextArea;

/**
 *
 * @author Ivy
 */



public class RmiThreading implements Runnable{

    public enum ThreadName{
    SEND,RECEIVE
    }
    
    private final TextArea txtChatArea;
    
    private final ThreadName name;

    private String message;
    
    public RmiThreading(ThreadName name, TextArea txtChatArea) {
        this.txtChatArea = txtChatArea;
        this.name=name;
    }

//    public RmiThreading(ThreadName name, String message,TextArea txtChatArea ) {
//        this.txtChatArea = txtChatArea;
//        this.name = name;
//        this.message = message;
//    }

 
    
    
    
    @Override
    public void run() {
        while (true) {     
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
//                    if (name.equals(ThreadName.SEND)) {
//                        LoginController.rmiChat.sendMessageToServer(message, txtChatArea);
//                    }
//                    
//                    if (name.equals(ThreadName.RECEIVE)) {
//                          LoginController.rmiChat.receiveMessageFromServer(txtChatArea);
//                    }
                      LoginController.rmiChat.receiveMessageFromServer(txtChatArea);
               
                }
            });
            
            try {
              Thread.sleep(1000);
            //    wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(RmiThreading.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
           
    }
    
}
