/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivanakasalo.rmi;

import com.ivanakasalo.gui.Main;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextArea;
import rmi.*;

/**
 *
 * @author Ivy
 */
public class RmiChatClient {
    private static final int RMIPORT=5000;
     private static final String MY_RMI_SERVER = "MyRmiServer";
     
     private StringBuilder sb= new StringBuilder();
     
     
     private String message;
     private TextArea txtArea;
     private String user;

    public RmiChatClient(String user) {
        this.user = user;
        start();
    }

     private IRmiChat chat;
     
    public RmiChatClient(String user,String message, TextArea txtArea) {
        this.message = message;
        this.txtArea = txtArea;
        this.user = user;
    }
    //mislim da bi bilo najbolje napravitic ctor samo s userom i da se odma pokrece i onda da imamo send message, da se ne konekta konstantno 
     
    public void start(){
        try {
            Registry registy= LocateRegistry.getRegistry();
            
            chat=(IRmiChat) registy.lookup(MY_RMI_SERVER);
            System.out.println("Client connected to rmiregistry.");
            
           
            
           
        } catch (RemoteException ex) {
            Logger.getLogger(RmiChatClient.class.getName()).log(Level.SEVERE, null, ex);
          
        } catch (NotBoundException ex) {
            Logger.getLogger(RmiChatClient.class.getName()).log(Level.SEVERE, null, ex);
          
        }
    
     
    }
    
    String rmiMessage="";
    public synchronized void sendMessageToServer(String message, TextArea txtArea){
//          this.message = message;
//            this.txtArea = txtArea;
        try {
           //chat.broadcastMessage(Main.currentUser.toString(), message);
            chat.sendMessage(user, message);
            
            sb.append(user).append(": ").append(message);
            sb.append("\n");
            txtArea.setText(sb.toString());
            
            //bilo bi dobro stavit i getmessage u rmi implementaciju . . .
            rmiMessage=user + ": " + message;
            System.out.println("Sent rmi message: " + rmiMessage);
            
            notifyAll();
        } catch (RemoteException ex) {
            Logger.getLogger(RmiChatClient.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
    
    String receivedMessage=null;

//    public String getReceivedMessage() {
//        return receivedMessage;
//    }

    public synchronized String getReceivedMessage(){
      try {
            receivedMessage=chat.broadcastMessage();
          
            System.out.println("Received message " + receivedMessage);
            return receivedMessage;
               }
              
         catch (RemoteException ex) {
            Logger.getLogger(RmiChatClient.class.getName()).log(Level.SEVERE, null, ex);
        }
      return null;
    }
    
    public synchronized void receiveMessageFromServer(TextArea txtArea){
        
        try {
            receivedMessage=chat.broadcastMessage();
            if (receivedMessage == null) {
                txtArea.appendText("");
            }
//            else if(receivedMessage.equals(rmiMessage)){
//            
//            }
            else{
             sb.append(receivedMessage);
            sb.append("\n");
            txtArea.setText(sb.toString());
            System.out.println("Received message " + receivedMessage);
            
//                try    {
//                    wait();
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(RmiChatClient.class.getName()).log(Level.SEVERE, null, ex);
//                }

            }
              
        } catch (RemoteException ex) {
            Logger.getLogger(RmiChatClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
}
