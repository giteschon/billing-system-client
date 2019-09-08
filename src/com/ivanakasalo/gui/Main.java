/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivanakasalo.gui;

import com.ivanakasalo.model.User;
import com.ivanakasalo.threading.Client;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.paint.Color;

/**
 * Main class start the application and starts Login stage
 *
 * @author Ivana
 */
public class Main extends Application {

    /**
     * User connected to the database.
     */
    public static User currentUser;

    //close the connection when application is closed (exited), fxml close ==hide   
    /**
     * When application is terminated client socket is closed If server wasn't
     * started application prints error message and terminates the Client
     * application
     */
    @Override
    public void stop() {
        try {
            Client.closeConnection();
            System.out.println("Connection is closed.");
        } catch (Exception e) {
            System.out.println("Connection wasn't opened.");
        }

    }

    /**
     * Starts Login scene
     *
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));

        // Parent root = FXMLLoader.load(Master.class.getResource("Master.fxml"));
//                  double xOffset = 0; 
// double yOffset = 0;
//        
//         //grab your root here
//             root.setOnMousePressed(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                xOffset = event.getSceneX();
//                yOffset = event.getSceneY();
//            }
//        });
//             
//             root.setOnMouseMoved(value);
//             
//           
//        
//        //move around here
//        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                stage.setX(event.getScreenX() - xOffset);
//                stage.setY(event.getScreenY() - yOffset);
//            }
//        });
        stage.initStyle(StageStyle.TRANSPARENT);
        Scene scene = new Scene(root);
        //scena ne smije imat boju jer ce se inace vidjet ruboci od pravokuntika kad stavimo zaobljeni rub
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);

        stage.show();
    }

    /**
     * Main method that launches client application and prints application name.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Application name: " + args[0]);
//        ClientConnection client= new ClientConnection();
//        client.startClient();
//clientConnection= new Client();

        launch(args);

    }

}
