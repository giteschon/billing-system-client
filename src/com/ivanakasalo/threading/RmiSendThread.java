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
public class RmiSendThread extends Thread {
    
     private final TextArea txtChatArea;
     
     private final RmiReceiveThread threadReceive;
     
     private final String message;

    public RmiSendThread(TextArea txtChatArea, String message, RmiReceiveThread thread) {
        this.txtChatArea = txtChatArea;
        this.message = message;
        this.threadReceive=thread;
        
    }

//    public RmiSendThread(TextArea txtChatArea, String message) {
//        this.txtChatArea = txtChatArea;
//        this.message = message;
//    }
     
     
     
     
    
     @Override
    public void run() {
      
            synchronized(threadReceive){
                try {
                    LoginController.rmiChat.sendMessageToServer(message, txtChatArea);
            threadReceive.notifyAll();
                } catch (Exception e) {
                    e.printStackTrace();
                }
           
               
//                try {
//                    join(5000);
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(RmiSendThread.class.getName()).log(Level.SEVERE, null, ex);
//                }
            
        }
           
    }
    
    
//     @Override
//    public void run() {
//      
//            synchronized(this){
//                try {
//                    LoginController.rmiChat.sendMessageToServer(message, txtChatArea);
//            this.notifyAll();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//           
//               
////                try {
////                    join(5000);
////                } catch (InterruptedException ex) {
////                    Logger.getLogger(RmiSendThread.class.getName()).log(Level.SEVERE, null, ex);
////                }
//            
//        }
//           
//    }
    
    public synchronized String getMessage(){
    notifyAll();
        return  LoginController.rmiChat.getReceivedMessage();
    
    
    }
    
}
