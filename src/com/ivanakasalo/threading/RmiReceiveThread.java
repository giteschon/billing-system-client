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
public class RmiReceiveThread extends Thread{
    
     private final TextArea txtChatArea;
     
     private RmiSendThread thread;

    public RmiReceiveThread(TextArea txtChatArea) {
        this.txtChatArea = txtChatArea;
    }

//    public RmiReceiveThread(TextArea txtChatArea, RmiSendThread thread) {
//        this.txtChatArea = txtChatArea;
//        this.thread = thread;
//    }
    
     
    
    @Override
    public void run() {
        while (true) {     
            
            synchronized(this){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                      LoginController.rmiChat.receiveMessageFromServer(txtChatArea);
               
                }
            
            }
            );
                                  
                    try {
                    System.out.println("waiting . . . .");
                    wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(RmiReceiveThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
            }
   
    
//      @Override
//    public void run() {
//        while (true) {     
//            if (thread != null) {
//                
//            
//            synchronized(thread){
//            Platform.runLater(new Runnable() {
//                @Override
//                public void run() {
//                      LoginController.rmiChat.receiveMessageFromServer(txtChatArea);
//               
//                }
//            
//            }
//            );
//                                  
//                    try {
//                    System.out.println("waiting . . . .");
//                    wait();
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(RmiReceiveThread.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                
//            }
//            }
    
            
            
            
// synchronized(this){
//                                  LoginController.rmiChat.receiveMessageFromServer(txtChatArea);
//               
//                try {
//                    System.out.println("waiting . . . .");
//                    wait();
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(RmiReceiveThread.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }


//           
//            Platform.runLater(new Runnable() {
//                @Override
//                public void run() {
//                     synchronized(RmiReceiveThread.class){
//                      LoginController.rmiChat.receiveMessageFromServer(txtChatArea);
//              
//                try {
//                    System.out.println("waiting . . . .");
//                    wait();
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(RmiReceiveThread.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                
//            }
//                     
//                     
//                }
//                
//            
//            });
//            
            
        }
           
    }
    
//    public synchronized void sendToServerFromThread(){
   // LoginController.rmiChat.
    
//    }
//     @Override
//    public void run() {
//    
//            Platform.runLater(new Runnable() {
//                @Override
//                public void run() {
//                      LoginController.rmiChat.receiveMessageFromServer(txtChatArea);
//               
//                }
//            
//            }
//            );
            
              
          
            
        
//           
//    }
    
}
