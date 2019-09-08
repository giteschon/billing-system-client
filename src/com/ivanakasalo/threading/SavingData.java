/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivanakasalo.threading;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import static com.ivanakasalo.threading.Client.socket;

/**
 *
 * @author Ivy
 */
public class SavingData implements Serializable{
 
    private boolean saveInProgress;
    
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    
    
    private DataInputStream dis;
    private DataOutputStream dos;
    
    
    private Socket socket =Client.socket;

    public SavingData(boolean saveInProgress) {
        this.saveInProgress = saveInProgress;
    }
    
    
    
    
    public synchronized void sendToServer(){
        Thread t= new Thread(new Runnable() {
            @Override
            public void run() {
              try {
//            oos= new ObjectOutputStream(socket.getOutputStream());
//            ois= new ObjectInputStream(socket.getInputStream());
            
            dis=new DataInputStream(socket.getInputStream());
            dos= new DataOutputStream(socket.getOutputStream());
            
            dos.writeBoolean(saveInProgress);
            System.out.println("Sent; " + saveInProgress);
           
            
        } catch (IOException ex) {
            Logger.getLogger(SavingData.class.getName()).log(Level.SEVERE, null, ex);
        }
            }
        });
        
       
    
    }
    
    public synchronized boolean readFromServer(){
       
          //new thread for sending
      Thread thread= new Thread(new Runnable(){
          @Override
          public void run() {
              try {
                  //send to socket
                  dis=new DataInputStream(socket.getInputStream());
                  dos= new DataOutputStream(socket.getOutputStream());
                  System.out.println("bool: " + dis.readBoolean());
                 saveInProgress=dis.readBoolean();
              } catch (IOException ex) {
                  Logger.getLogger(SavingData.class.getName()).log(Level.SEVERE, null, ex);
              }
             
          }
      
      });
      //start the thread
      thread.start();
//        
//        try {
////            oos= new ObjectOutputStream(socket.getOutputStream());
////            ois= new ObjectInputStream(socket.getInputStream());
//            //return  ois.readBoolean();
//            
//            dis=new DataInputStream(socket.getInputStream());
//            dos= new DataOutputStream(socket.getOutputStream());
//            System.out.println("bool: " + dis.readBoolean());
//            return dis.readBoolean();
//        } catch (IOException ex) {
//            Logger.getLogger(SavingData.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return  saveInProgress;
    }
    
}
