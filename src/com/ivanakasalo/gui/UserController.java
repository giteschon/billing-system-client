/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivanakasalo.gui;

import com.ivanakasalo.model.User;
import com.ivanakasalo.utility.MyInitializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import static com.ivanakasalo.utility.MyInitializable.manager;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author Ivana
 */
public class UserController implements MyInitializable {

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSurname;

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private PasswordField txtRepeatPassword;

    @FXML
    private Label lblOpearatorCode;

    @FXML
    private TableView<User> tblUsers;

    @FXML
    private TableColumn<User, String> colName;

    @FXML
    private TableColumn<User, String> colSurname;

    @FXML
    private TableColumn<User, String> colUsername;

    @FXML
    private TableColumn<User, String> colUserCode;

    @FXML
    private Button btnEdit;

    
    private static User staticUser;
    
    @FXML
    void btnAddMouseClicked(MouseEvent event) {
        //User u = getUser();
        staticUser=getUser();
Thread thread= new Thread();
thread.start();

//    if (thread.isInterrupted()) {
System.out.println("thread started" + Main.currentUser);
        
        
        try {
            synchronized(thread){
            openDialog(Main.currentUser + "is currently working...");
            //ne dolazi do iduceg koraka......................................................................
            this.wait(5000);
            openDialog("YOu can use it now");
            
              this.notifyAll();
              } 
        } catch (InterruptedException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       
        
//    }
//     else{
//                this.notifyAll();
//                }


        manager.addUser(staticUser);
        fillTableUsers();
        resetFields();
    }

    private int idUser = 0;

    @FXML
    void btnEditMouseClicked(MouseEvent event) {
        if (txtPassword.getText().equals(txtRepeatPassword.getText())) {
            manager.editUser(idUser, getUser());
        } else {
            System.out.println("pass doesn't match");
        }
        btnEdit.setDisable(true);
        fillTableUsers();
        resetFields();
    }

    @FXML
    void rowSelected(MouseEvent event) {
        try {
            User u = getSelectedUser();

            idUser = u.getIdUser();
            btnEdit.setDisable(false);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                synchronized(manager.getUsers()){
//                }
//            }
//        });
        

Thread thread= new Thread();
//synchronized(this){
//    if (thread.isInterrupted()) {
//        openDialog(Main.currentUser + "is currently working...");
//        try {
//            this.wait();
//            openDialog("YOu can use it now");
//            
//              this.notifyAll();
//        } catch (InterruptedException ex) {
//            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//       
//        
//        
//    }
//     else{
//                this.notifyAll();
//                }
//}

        fillTableUsers();
    }

    private void fillTableUsers() {
        try {
            ObservableList<User> list = FXCollections.observableArrayList(manager.getUsers());

            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
            colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
            colUserCode.setCellValueFactory(new PropertyValueFactory<>("operatorCode"));
            tblUsers.setItems(list);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private User getSelectedUser() {
        User u = tblUsers.getSelectionModel().getSelectedItem();
        txtName.setText(u.getName());
        txtUsername.setText(u.getUsername());
        txtPassword.setText(u.getPassword());
        txtSurname.setText(u.getUsername());
        lblOpearatorCode.setText(u.getOperatorCode());

        return u;
    }

    private void resetFields() {
        txtName.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
        txtSurname.setText("");
        lblOpearatorCode.setText("");
        txtRepeatPassword.setText("");
    }

    private User getUser() {
        if (txtPassword.getText().equals(txtRepeatPassword.getText())) {
            String opCode = generateOperatorCode();
            return new User(
                    txtName.getText(), txtUsername.getText(), txtUsername.getText(), txtPassword.getText(), opCode
            );
        }
        System.out.println("Password doesn't match");
        return null;

    }

    private String generateOperatorCode() {
        String first = txtName.getText().substring(0, 1).toUpperCase();
        String second = txtSurname.getText().substring(0, 1).toUpperCase();

        return first + second;

    }
    
    private void openDialog(String text){
    Alert dialog= new Alert(Alert.AlertType.WARNING);
    dialog.setTitle("Thread interrupted");
    dialog.setContentText(text);
    dialog.showAndWait();
    }

}
