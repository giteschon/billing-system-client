//package com.ivanakasalo.threading;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivanakasalo.threading;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author Ivy
 */
public class Client {
    private static final int SERVERPORT=1234;
    private static final String LOCALHOST = "localhost";
    
    private DataInputStream dis;
    private DataOutputStream dos;
    
  private String message;
  private String name;
  
  

    public DataInputStream getDis() {
        return dis;
    }

   

    public DataOutputStream getDos() {
        return dos;
    }

   
    public static Socket socket;
    
 

//    public ClientHandler(TextField message, TextArea chatWindow) {
//        this.message = message;
//        this.chatWindow = chatWindow;
//    }

    //private ChatThread chatThread;

    public Client(String name) {
        this.name = name;
    }
    
    

  
       //get input and output streams from socket
//           dis= new DataInputStream(socket.getInputStream());
//           dos= new DataOutputStream(socket.getOutputStream());
    public void start(){
    try {
            //ctrl space otvori sve kaj prima
            //connect to socket
           socket= new Socket(LOCALHOST, SERVERPORT);
           dis=new DataInputStream(socket.getInputStream());
            dos= new DataOutputStream(socket.getOutputStream());
            
           
           
          // System.out.println("Client " + name + " is connected to " + String.valueOf(SERVERPORT) + " port.");
           System.out.println("Client " + name + " is connected to " + socket);
     
        } catch (IOException ex) {
           Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                       System.out.println("Connect to server before running this application.");
            Platform.exit();
            
        }
    }
    
    private boolean msgRead=false;
    
    public void readFromServer(){
//    ClientHandler handler= new ClientHandler();
//    handler.readFromServer();
//    msgRead=true;

       readMessage();
    }

    public boolean isMsgRead() {
        return msgRead;
    }

    public void setMsgRead(boolean msgRead) {
        this.msgRead = msgRead;
    }
    
    
    
   
    
    public static void closeConnection(){
        try {
           
                socket.close();
            
            
        } catch (IOException ex) {
          Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    private synchronized void readMessage() {
        Thread thread= new Thread(() -> {
            while (true) {
                try {
                    dis=new DataInputStream(socket.getInputStream());
            dos= new DataOutputStream(socket.getOutputStream());
                    //read sent message
                    String msg= dis.readUTF();
                    System.out.println("Client received: " + msg);
                    //ClientHandler.messageSent=false;
                } catch (IOException ex) {
                    Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
                
            //}
        });
        
        //start the thread
        thread.start();
    }
   
    
    
}


//import java.io.*; 
//import java.net.*; 
//import java.util.Scanner; 
//  
//public final class Client  
//{ 
//
//    public Client() throws IOException {
//        start();
//    }
//    
//    
//    
//    final int ServerPort = 1234; 
//  
//    public void start() throws UnknownHostException, IOException  
//    { 
//        Scanner scn = new Scanner(System.in); 
//          
//        // getting localhost ip 
//        InetAddress ip = InetAddress.getByName("localhost"); 
//          
//        // establish the connection 
//        Socket s = new Socket(ip, ServerPort); 
//          
//        // obtaining input and out streams 
//        DataInputStream dis = new DataInputStream(s.getInputStream()); 
//        DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
//  
//        // sendMessage thread 
//        Thread sendMessage = new Thread(new Runnable()  
//        { 
//            @Override
//            public void run() { 
//                while (true) { 
//  
//                    // read the message to deliver. 
//                    String msg = scn.nextLine(); 
//                      
//                    try { 
//                        // write on the output stream 
//                        dos.writeUTF(msg); 
//                    } catch (IOException e) { 
//                        e.printStackTrace(); 
//                    } 
//                } 
//            } 
//        }); 
//          
//        // readMessage thread 
//        Thread readMessage = new Thread(new Runnable()  
//        { 
//            @Override
//            public void run() { 
//  
//                while (true) { 
//                    try { 
//                        // read the message sent to this client 
//                        String msg = dis.readUTF(); 
//                        System.out.println(msg); 
//                    } catch (IOException e) { 
//  
//                        e.printStackTrace(); 
//                    } 
//                } 
//            } 
//        }); 
//  
//        sendMessage.start(); 
//        readMessage.start(); 
//  
//    } 
//} 