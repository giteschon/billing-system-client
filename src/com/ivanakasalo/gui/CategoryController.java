/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivanakasalo.gui;

import com.ivanakasalo.threading.UserActionHistory;
import com.ivanakasalo.model.Category;
import com.ivanakasalo.threading.CategoryThread;
import com.ivanakasalo.utility.MyInitializable;
import static com.ivanakasalo.utility.MyInitializable.manager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Ivana
 */
public class CategoryController implements MyInitializable {

    @FXML
    private TextField tctCategory;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnEdit;

    @FXML
    private TableView<Category> tblCategory;

    @FXML
    private TableColumn<Category, String> colCategory;

    
    private CategoryThread thread;
    @FXML
    void btnAddMouseClicked(MouseEvent event) {
        //manager.saveInProgress=true;
        
//        Thread categoryThread= new Thread(new Runnable() {
//            @Override
//            public void run() {
//          manager.addCategory(tctCategory.getText());
//            }
//        });
//        
//        categoryThread.start();

//Category c= new Category(tctCategory.getText());
//
//thread= new CategoryThread(CategoryThread.Procedure.ADD_CATEGORY, manager,c );
       manager.addCategory(tctCategory.getText());
        fillTableCategory();
        //LoginController.handler.sendMessage(message);
        
        UserActionHistory.refreshHistory(true, Category.class, tctCategory.getText());

    }

    private int idCategory = 0;

    @FXML
    void rowClicked(MouseEvent event) {
        try {
            Category c = tblCategory.getSelectionModel().getSelectedItem();
            tctCategory.setText(c.getName());
            btnEdit.setDisable(false);
            idCategory = c.getIdCategory();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void btnEditMouseClicked(MouseEvent event) {
//         Thread categoryThread= new Thread(new Runnable() {
//            @Override
//            public void run() {
//         manager.editCategory(tctCategory.getText(), idCategory);
//            }
//        });
//        
//       categoryThread.start();
        
//Category c= new Category(idCategory,tctCategory.getText());
//
//thread= new CategoryThread(CategoryThread.Procedure.ADD_CATEGORY, manager, c);

        manager.editCategory(tctCategory.getText(), idCategory);
        UserActionHistory.refreshHistory(false, Category.class, tctCategory.getText());
        fillTableCategory();
        btnEdit.setDisable(true);
        tctCategory.setText("");
        
    }

    //public static final statickaVarijabla="telst";
    //onda idemo u drugi controller di ju zelimo koristit, npr. logincontroller.statickaVarijabla;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        fillTableCategory();
//        Thread t= new Thread(new Runnable() {
//            @Override
//            public void run() {
//            if (btnAdd.isPressed()==true) {
//                System.out.println("add");
//        }
//            else{
//                System.out.println("edit");
//            }
//            }
//        });
//        t.start();
//        
//        synchronized(t){
//            while (true) {                
//                
//            }
//        }
        
      

    }

    //za putanju new File("src\\gifhrast\\dijelenje.gif")
    private void fillTableCategory() {
        try {
            ObservableList<Category> list = FXCollections.observableArrayList(manager.getCategories());

            colCategory.setCellValueFactory(new PropertyValueFactory<Category, String>("name"));
            tblCategory.setItems(list);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
