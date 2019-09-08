/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivanakasalo.gui;

import com.ivanakasalo.jndi.JndiResource;
import com.ivanakasalo.rmi.RmiChatClient;
import com.ivanakasalo.threading.Client;
import com.ivanakasalo.threading.ClientHandler;
import com.ivanakasalo.utility.MyInitializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;

/**
 *
 * @author Ivana
 */
public class LoginController implements MyInitializable {
//client is used for DataStreams communication
    public static Client client=null;
    
    /**
     * Static filed which for RMI implementation for chatting.
     */
    public static RmiChatClient rmiChat=null;
    /**
     * Static variable which indicates whether client is connected to the server
     */
     public static boolean clientStarted=false;
    /**
     * Like in the server application ClientHandler handler is a Runnable interface for Server-Client communication.
     */
    public static ClientHandler handler;
    
    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button btnLogin;

    @FXML
    private ImageView btnClose;

    @FXML
    private Label lblMessage;

    @FXML
    void btnCloseAction(MouseEvent event) {
//close application
     //   Platform.exit();
     stage.close();

    }
    
   

    @FXML
    void btnLoginAction(MouseEvent event) {
        // startApplication();   

        if (manager.checkCredentials(txtUsername.getText(), txtPassword.getText())) {
            Main.currentUser = manager.getCredentials(txtUsername.getText(), txtPassword.getText());
            startApplication();
            
            //start connection to server
             client = new Client(Main.currentUser.toString());
             client.start();
             clientStarted=true;
             
             handler=new ClientHandler();
             
             //connect to rmi
             rmiChat= new RmiChatClient(Main.currentUser.toString());
             
        } else if (txtUsername.getText().isEmpty() || txtPassword.getText().isEmpty()) {
            lblMessage.setText("Please enter your credentials.");
        } else {
            lblMessage.setText("Username/ password is incorrect.");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private  Stage stage;
    
    private void startApplication() {
        try {

            //open new window -> master layout           
            FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("Master.fxml"));
            Parent root = (Parent) fXMLLoader.load();
            
//            JndiResource jndi= new JndiResource(root);
//            jndi.changeAttributesWithJndi();
            
            stage = new Stage();
            stage.setScene((new Scene(root)));
            stage.show();
            
            

            // close login stage
            Stage loginStage = (Stage) btnLogin.getScene().getWindow();
            loginStage.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
