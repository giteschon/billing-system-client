/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivanakasalo.gui;

import com.ivanakasalo.jndi.JndiResource;
import com.ivanakasalo.threading.Clock;
import com.ivanakasalo.threading.RmiReceiveThread;
import com.ivanakasalo.threading.RmiSendThread;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Ivana
 */
public class MasterController implements Initializable {

    @FXML
    private Pane pnlSide6;

    @FXML
    private BorderPane bpaneMaster;
    
    @FXML
    private Pane btnInvoiceManagement;

    @FXML
    private Pane btnCategory;

    @FXML
    private AnchorPane contentContainer;

    @FXML
    private Pane btnClient;

    @FXML
    private Pane btnTask;

    @FXML
    private Pane btnUser;

    @FXML
    private Pane pnlSide7;

    @FXML
    private Pane pnlSide5;

    @FXML
    private Pane pnlSide4;

    @FXML
    private Pane pnlSide3;

    @FXML
    private Pane pnlSide2;

    @FXML
    private Pane pnlSide1;

    @FXML
    private Pane btnTimeLog;

    @FXML
    private VBox navContainer;

    @FXML
    private Pane pnlSide0;

    @FXML
    private Pane btnDashboard;

    @FXML
    private Pane btnInvoice;

    @FXML
    private Label lblUser;
    
    @FXML
    private Label lblTime;

    
    
    //nemam ovo . . .
    @FXML
    void btnCloseAction(MouseEvent event) {
        Platform.exit();

    }
    
    /**
     * Initializes the controller class.
     */
    
     public static RmiReceiveThread t;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadScene("Dashboard.fxml");
        lblUser.setText(Main.currentUser.toString());
//        Timer timer= new Timer();
        Clock clock= new Clock(lblTime);
//        clock.run();
       // timer.scheduleAtFixedRate(clock, 1000, 1000);
        //startThreadedClock(lblTime);
        //startClockTask(lblTime);
        Thread clockThread = new Thread(clock);
        clockThread.start();
        
       
//        Thread rmiThread= new Thread( new RmiThreading(RmiThreading.ThreadName.RECEIVE, txtChatArea));
//        rmiThread.start();
//        
//        synchronized(rmiThread){
//            try {
//                if (LoginController.rmiChat.getReceivedMessage()!= null) {
//                System.out.println("wait for another message");
//                rmiThread.wait();    
//                }
//                
//                
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        
//        }
        

       // RmiReceiveThread t= new RmiReceiveThread(txtChatArea);
t= new RmiReceiveThread(txtChatArea);
t.start();

//do           {
//            t= new RmiReceiveThread(txtChatArea, thread);
//      
//        }while(thread != null);
//           t.start();
            
       

        
//        synchronized(t){
//            while (true) {                
//                try {
//                    
//                    t.wait();
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(MasterController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        
//        }


    

        
       
    }

    


    
    
    @FXML
    void btnDashboardMouseClicked(MouseEvent event) throws IOException {
        loadScene("Dashboard.fxml");
        activeSceneDecorator(pnlSide0);
    }

    @FXML
    void btnUserMouseClicked(MouseEvent event) throws IOException {
        loadScene("User.fxml");
        activeSceneDecorator(pnlSide1);
    }

    @FXML
    void btnClientMouseClicked(MouseEvent event) {
        loadScene("Client.fxml");
        activeSceneDecorator(pnlSide2);
    }

    @FXML
    void btnTimeLogMouseClicked(MouseEvent event) {
        loadScene("TimeLog.fxml");
        activeSceneDecorator(pnlSide3);
    }

    @FXML
    void btnInvoiceMouseClicked(MouseEvent event) {
        loadScene("Invoice.fxml");
        activeSceneDecorator(pnlSide4);
    }

    @FXML
    void btnInvoiceManagementMouseClicked(MouseEvent event) {
        loadScene("InvoiceManagement.fxml");
        activeSceneDecorator(pnlSide5);
    }

    @FXML
    void btnCategoryMouseClicked(MouseEvent event) {
        loadScene("Category.fxml");
        activeSceneDecorator(pnlSide6);
    }

    @FXML
    void btnTaskMouseClicked(MouseEvent event) {
        loadScene("Task.fxml");
        activeSceneDecorator(pnlSide7);
    }

//    Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(5), new EventHandler<ActionEvent>() {
//
//    @Override
//    public void handle(ActionEvent event) {
//        System.out.println("this is called every 5 seconds on UI thread");
//        waitForNotifications();
//    }
//}));
    
  private void notifyUsersAboutNewInvoice() {
      LoginController.handler.readMessage();
     
     //cijelo vrijeme mi dogvaca get message, a trebo bi ustvari ovo kaj procita, jer kad posalje onda posalje jednom, a message je sad konstatno namjesten za citanje,,,
     //tako da bi ustvari readMessage trebo vracat string.
      if (!LoginController.handler.getMessage().equals("")) {
          
//          if (!ClientHandler.messageSent) {
               Alert alert = new Alert(AlertType.INFORMATION);
alert.setTitle("New invoice was added");
alert.setHeaderText(null);
alert.setContentText(LoginController.handler.getMessage());

alert.showAndWait();
//s ovim messageSent alert se pojavljuje samo jednom, ali nece postat false dok god ovaj klijent ne doda novi racun ...
//ClientHandler.messageSent=true;
//          }
             
//
//LoginController.handler.setMessage("");
//          System.out.println("mSg in alert: " + LoginController.handler.getMessage());
      }

  
      
         
        }
    
    
    private void loadScene(String fxml) {
        try {
            BorderPane pane = FXMLLoader.load(getClass().getResource(fxml));
            
            JndiResource jndi= new JndiResource(bpaneMaster);
            jndi.changeAttributesWithJndi();

            contentContainer.getChildren().setAll(pane);
            
          notifyUsersAboutNewInvoice();
            
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            //ovo sve kaj je tak treba zamijenit alert . . .
        }
    }
   
    
     @FXML
    private TextArea txtChatArea;
     
      @FXML
    private TextField txtMessage;
    
    @FXML
    void btnChatOnMouseClicked(MouseEvent event) {
        if (txtChatArea.isVisible()) {
        txtChatArea.setVisible(false);    
        }else{
        txtChatArea.setVisible(true);    
        }
        
    }

    
    private RmiSendThread thread=null;
    
    public static String message="";
      @FXML
    void sendMessageOnKeyPressed(KeyEvent event) {
        
          if (event.getCode().equals(KeyCode.ENTER)) {
      //OVO BI SE TREBALO OSBVJEZAVAT S THREADOM, TJ. U INITALIZE SE POKRECE THREAD I ONDA BI TREBO IMAT WAIT DOK GA OVAJ NE OBAVIJESTI, ALI ONDA SE SKROZ ZAKOCI
      //ILI JE STALNO U WHILE PETLJI I KONSTANTO SE OVJEZAVA NA EKRANU -> TO JE OVO GORE
           //   LoginController.rmiChat.receiveMessageFromServer(txtChatArea);
           
           
              if (!txtMessage.getText().equals("")) {
                  message=txtMessage.getText();
             
////              TextFlow flow= new TextFlow();
////              Text user= new Text(Main.currentUser.toString());
////              user.setStyle("-fx-font-weight: bold");
//
//
//sb.append(Main.currentUser.toString());
//sb.append(": ");
//sb.append(message);
//sb.append("\n");
//
//              }
//              
//              
//              
//              txtChatArea.setText(sb.toString());

 //new Thread(new ClientConnection(txtMessage,txtChatArea)).start();
            // ClientConnection cc= new ClientConnection(txtMessage, txtChatArea);
//            ChatThread thread= new ChatThread(Main.clientConnection);
//            thread.start();
//            thread.sendMsgToServer(message);
//            
//            txtChatArea.setText(thread.getReply());
//            
//            //Caused by: java.lang.NullPointerException
////	at com.ivanakasalo.threading.ChatThread.sendMsgToServer(ChatThread.java:53)
////	at com.ivanakasalo.gui.MasterController.sendMessageOnKeyPressed(MasterController.java:263)
////	... 39 more
//            
             
//                  RmiChatClient rmi= new RmiChatClient(Main.currentUser.toString(),message,txtChatArea);
//                  rmi.start();

//LoginController.rmiChat.sendMessageToServer(message, txtChatArea);

//Thread sendThread= new Thread(new RmiThreading(RmiThreading.ThreadName.SEND, message, txtChatArea));
//sendThread.start();
//
//Thread t= new Thread(new Runnable() {
//                      @Override
//                      public void run() {
//                        
//  LoginController.rmiChat.sendMessageToServer(message, txtChatArea);
//  notifyAll();
//                      }
//                  });
//t.start();
//synchronized(t){
//notifyAll();
//}


//DOBIVA PORUKU, ALI SAMO ONU KOJU JE ON POSLAO JER JE TO USTVARI ZADNJA...
//MISLIM DA BI IH TREBALO OBRNUT
//TJ. NE OVAJ PROVO ODRADI SVOJE I ONDA PUSTI LOCK I ONDA DOBI PORUKU, ALI OPET ONAJ U DRUGO APP NE DOBI OBAVIJEST
//RmiSendThread thread= new RmiSendThread(txtChatArea, message,t);
thread= new RmiSendThread(txtChatArea, message,t);
thread.start();


//jos gore
//sad se tek nis ne dobiva
// t= new RmiReceiveThread(txtChatArea, thread);
//           t.start();
//Thread t2= new Thread(new Runnable() {
//                      @Override
//                      public void run() {
//                          while (true) {                              
//                              txtChatArea.setText(thread.getMessage());
//                          }
//                      }
//                  });
//
//t2.start();

              txtMessage.setText("");
              
//                  RmiSendThread t= new RmiSendThread(txtChatArea, message);
//                  t.start();

//Thread t2= new Thread(new Runnable() {
//                      @Override
//                      public void run() {
//                          
//                          
//                          
//                          
//                      }
//                  });
//              t2.start();
            //  sb.append(message);
          //    txtChatArea.setText(sb.toString());
              
          }
    }
          

    }

    private void activeSceneDecorator(Pane sidePane) {
//  navContainer.setStyle("-fx-background-color:#2c0f48");
//navContainer.getChildren().forEach(p -> p.setStyle("-fx-background-color:#2c0f48"));   
        for (Node p : navContainer.getChildren()) {

            //casting node to pane nad foreach
            if (p instanceof Pane) {
                ((Pane) p).getChildren().forEach(c -> c.setStyle("-fx-background-color:#2c0f48"));
            }

        }

        sidePane.setStyle("-fx-background-color:#5a3972");

    }

  

  

   
}
