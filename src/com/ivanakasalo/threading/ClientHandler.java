/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivanakasalo.threading;

import com.ivanakasalo.gui.LoginController;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivy
 */
public class ClientHandler{
//    private static final int SERVERPORT=1234;
//    private static final String LOCALHOST = "localhost";
//    
//    private TextField messageField;
//    private TextArea chatWindow;
//
////    public ClientHandler(TextField message, TextArea chatWindow) {
////        this.message = message;
////        this.chatWindow = chatWindow;
////    }
//
//    private ChatThread chatThread;
//    
//    public ClientHandler(TextField messageField,TextArea chatWindow){
//        this.chatWindow=chatWindow;
//        this.messageField=messageField;
//        start();
//    }
//      
//    private void start(){
//    try {
//            //ctrl space otvori sve kaj prima
//           Socket socket= new Socket(LOCALHOST, SERVERPORT);
//          //  chatThread= new ChatThread(socket, chatWindow);
//            chatThread.start();
//            
//            listenForInput();
//            
//        } catch (IOException ex) {
//            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
//            
//        }
//    }
//    
//    private void listenForInput() {
//              while (true) {            
////            while (!messageField.getText().equals("")) {                
//////                try {
//////                    //just so cpu doesnt work all the time
//////                    Thread.sleep(1);
//////                } catch (InterruptedException ex) {
//////                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
//////                }
////            }
//            
//            String msg=messageField.getText();
//                              
//                        chatThread.sendMsgToServer(msg);
//            
//            
//           
//            
//            
//        }
//        //chatThread.close();       
//               
//        
//    }
    
    
//    public void startClient(){
//        Socket clientSocket;
//        try {
//            clientSocket= new Socket(LOCALHOST, SERVERPORT);
//            System.out.println("Client connected");
//            
//            DataInputStream dis= new DataInputStream(clientSocket.getInputStream());
//            DataOutputStream dos= new DataOutputStream(clientSocket.getOutputStream());
//            
//            ObjectOutputStream oos= new ObjectOutputStream(clientSocket.getOutputStream());
//            
//            
//        } catch (IOException ex) {
//            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    
//    }
    
//    StringBuilder sb = new StringBuilder();
//
//    @Override
//    public void run() {
//        Socket clientSocket;
//        try {
//            clientSocket= new Socket(LOCALHOST, SERVERPORT);
//            System.out.println("Client connected");
//            
//            DataInputStream dis= new DataInputStream(clientSocket.getInputStream());
//            DataOutputStream dos= new DataOutputStream(clientSocket.getOutputStream());
//            
//            //ObjectOutputStream oos= new ObjectOutputStream(clientSocket.getOutputStream());
//            
//            dos.writeUTF(message.getText());
//            
//            
//            sb.append(Main.currentUser.toString());
//            sb.append(": ");
//            
//
//
//            sb.append(dis.readUTF());
//            sb.append("\n");
//            chatWindow.setText(sb.toString());
//            
//            
//        } catch (IOException ex) {
//            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
   // private Socket socket;
    private String message;

    public String getMessage() {
      return  message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
     private boolean saveInProgress=false;

    public boolean isSaveInProgress() {
        return saveInProgress;
    }

    public void setSaveInProgress(boolean saveInProgress) {
        this.saveInProgress = saveInProgress;
    }
     
    
    
     
    
    public static boolean messageSent=false;
    
    public boolean sent=false;

    private DataInputStream dis;
    private DataOutputStream dos;
    
    private Client client= LoginController.client;
    
    public ClientHandler() {
       
    }
    
    public ClientHandler(String message, boolean  sent) {
        this.message=message;
      //  this.socket=Client.server;
        this.sent=sent;
        //System.out.println("Client con:" + socket);
        //sendToServer();
        sendMessage(message);
        //setSaveInProgress(false);
        
        this.saveInProgress=false;
        
        
    }

    
    
    
    
//    private void sendToServer(){
////        try {
//            //ovo je sad vjerojatno redundatno
////            dis=new DataInputStream(socket.getInputStream());
////            dos= new DataOutputStream(socket.getOutputStream());
//            //System.out.println("sent: " + message);
//            
//            if (sent) {
//                messageSent=true;
//                sendMessage(message);
//            }
            
            
           
            
//            dis.close();
//            dos.close();
            
//        } catch (IOException ex) {
//            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        finally{
//       // close();
//        }
    
   // }
    
//    public void readFromServer(){
//        if (messageSent) {
//            try {
//                dis=new DataInputStream(socket.getInputStream());
//                dos= new DataOutputStream(socket.getOutputStream());
//                readMessage();
//            } catch (IOException ex) {
//                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    
//    }

    private synchronized void sendMessage(String message) {
        
          if (sent) {
                messageSent=true;
               
          
      //new thread for sending
      Thread thread= new Thread(new Runnable(){
          @Override
          public void run() {
//              while (true) {                  
                  try {
                      //send to server
                      
                      client.getDos().writeUTF(message);
                      messageSent=false;
                      //dos.writeUTF(message);
                      System.out.println("Sent message: " + message);
                  } catch (IOException ex) {
                      Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                  }
              //}
              //}
          }
      
      });
      //start the thread
      thread.start();
          }
    }

    public synchronized void readMessage() {
//        if (messageSent) {
             Thread thread= new Thread(() -> {
            //while (true) {
                try {
                    //read sent message
                    String msg= client.getDis().readUTF();
                    System.out.println("Client received: " + msg);
                    
                    setMessage(msg);
                    
                    messageSent=true;
                    
                } catch (IOException ex) {
                    Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            //}
                
            //}
        });
        
        //start the thread
        thread.start();
       
//        messageSent=false;
//        }
      
    }

    public boolean sendSaveInProgress(boolean  saveInProgress){
        try {
            saveInProgress= dis.readBoolean();
            return  saveInProgress;
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    return  false;
    }
    
    
    private void close() {
        try {
            dis.close();
            dos.close();
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
  

    
    
    
} 

