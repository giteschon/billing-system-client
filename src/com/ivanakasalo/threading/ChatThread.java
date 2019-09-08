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
public class ChatThread extends Thread {

    Socket socket;
    
    boolean shouldRun = true;

    DataInputStream din;
    DataOutputStream dout;
    
    private TextArea chatWindow;
    private TextField messageField;
    
    private String reply;

    private void setReply(String reply) {
        this.reply = reply;
    }

    public String getReply() {
        return reply;
    }

    public ChatThread(Client client) {
        super("ChatThread");
        //this.socket = client.getS();
//        this.chatWindow=chatWindow;
//        this.messageField=messageField;
    }

    public void sendMsgToServer(String msg) {
        try {
            dout.writeUTF(msg);
            dout.flush();
        } catch (IOException ex) {
            Logger.getLogger(ChatThread.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
private StringBuilder sb= new StringBuilder();

    @Override
    public void run() {
      
               try {
            din = new DataInputStream(socket.getInputStream());
            dout = new DataOutputStream(socket.getOutputStream());

            while (shouldRun) {
               // String reply = getMessages(din.readUTF());
                setReply(din.readUTF());
               System.out.println(reply);
               // chatWindow.setText(reply);
                //System.out.println("rep: " + reply);
            }
        } catch (IOException ex) {
            Logger.getLogger(ChatThread.class.getName()).log(Level.SEVERE, null, ex);
            close();
        }
            }
       
        
       

    
    
    public String getMessages(String msg){
        sb.append(msg);
        sb.append("\n");
        return sb.toString();
    
    }

    private void close() {

        try {
            dout.close();
            socket.close();
            din.close();
        } catch (IOException ex) {
            Logger.getLogger(ChatThread.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
