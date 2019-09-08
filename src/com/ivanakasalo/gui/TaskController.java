/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivanakasalo.gui;

import com.ivanakasalo.model.Category;
import com.ivanakasalo.model.Task;
import com.ivanakasalo.utility.MyInitializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Ivana
 */
public class TaskController implements MyInitializable {

    @FXML
    private ComboBox<Category> cbCategory;

    @FXML
    private TextField txtTask;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnEdit;

    @FXML
    private TableView<Task> tblTasks;

    @FXML
    private TableColumn<Task, String> colCategory;

    @FXML
    private TableColumn<Task, String> colTask;

    @FXML
    void btnAddMouseCliciked(MouseEvent event) {
        Task t = getTask();

        manager.addTask(t);
        fillTableTasks();
        resetFields();
    }

    private int idTask = 0;

    @FXML
    void btnEditMouseClicked(MouseEvent event) {

        manager.editTask(idTask, getTask());
        btnEdit.setDisable(true);
        fillTableTasks();
        resetFields();
    }

    @FXML
    void getSelectedRow(MouseEvent event) {
        try {
            Task t = getSelectedTask();

            idTask = t.getIdTask();
            btnEdit.setDisable(false);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void cbCategoryMouseClicked(MouseEvent event) {
        fillCategory();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillTableTasks();
    }

    private void fillCategory() {
        ObservableList<Category> list = FXCollections.observableArrayList(manager.getCategories());
        cbCategory.setItems(list);
    }

    private void fillTableTasks() {

        ObservableList<Task> list = FXCollections.observableArrayList(manager.getTasks());
        colTask.setCellValueFactory(new PropertyValueFactory<Task, String>("name"));
        colCategory.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Task, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Task, String> category) {
                return new SimpleStringProperty(category.getValue().getCategory().getName());
            }
        });

        tblTasks.setItems(list);

    }

    private Task getTask() {
        return new Task(
                txtTask.getText(),
                cbCategory.getValue()
        );

    }

    private Task getSelectedTask() {
        Task t = tblTasks.getSelectionModel().getSelectedItem();
        txtTask.setText(t.getName());
        cbCategory.setValue(t.getCategory());

        return t;

    }

    private void resetFields() {
        cbCategory.setValue(null);
        txtTask.setText("");
    }

}
