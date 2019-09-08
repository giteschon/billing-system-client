/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivanakasalo.gui;

import com.ivanakasalo.model.Category;
import com.ivanakasalo.model.Client;
import com.ivanakasalo.model.Task;
import com.ivanakasalo.model.TimeLog;
import com.ivanakasalo.model.User;
import com.ivanakasalo.utility.MyInitializable;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import static com.ivanakasalo.utility.MyInitializable.manager;

/**
 * FXML Controller class
 *
 * @author Ivana
 */
public class TimeLogController implements MyInitializable {

    @FXML
    private ComboBox<Category> cbCategory;

    @FXML
    private TableColumn<TimeLog, String> colUser;

    @FXML
    private TableColumn<TimeLog, String> colCategory;

    @FXML
    private TableColumn<TimeLog, String> colTime;

    @FXML
    private TableColumn<TimeLog, String> colDate;

    @FXML
    private TableColumn<TimeLog, String> colModified;

    @FXML
    private TableColumn<TimeLog, String> colClient;

    @FXML
    private TableView<TimeLog> tblTimeLog;

    @FXML
    private ComboBox<Task> cbTask;

    @FXML
    private ComboBox<User> cbUser;

    @FXML
    private ComboBox<Client> cbClient;

    @FXML
    private TableColumn<TimeLog, String> colTask;

    private int idCategory = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillTableTimeLog();
        dpTaskDate.setValue(LocalDate.now());
        cbUser.setValue(Main.currentUser);

        fillUsers();
        fillClients();

    }

    @FXML
    private TextField txtTime;

    @FXML
    private DatePicker dpTaskDate;

    @FXML
    private Button btnEdit;

    private int idTimeLog = 0;

    @FXML
    void btnAddMouseClicked(MouseEvent event) {

        TimeLog t = getTimeLog();

        manager.addTimeLog(t);
        fillTableTimeLog();
        resetFields();
    }

    @FXML
    void btnEditMouseClicked(MouseEvent event) {
        manager.editTimeLog(idTimeLog, getTimeLog());
        System.out.println(getTimeLog().toString());
        btnEdit.setDisable(true);
        fillTableTimeLog();
        resetFields();
    }

    @FXML
    void getSelectedRow(MouseEvent event) {
        try {
            TimeLog t = getSelectedTimeLog();

            idTimeLog = t.getIdTimeLog();
            System.out.println(idTimeLog);
            btnEdit.setDisable(false);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void cbCategoryMouseClicked(MouseEvent event) {
        fillCategories();
    }

    @FXML
    void cbTaskMouseClicked(MouseEvent event) {
        idCategory = cbCategory.getValue().getIdCategory();
        System.out.println(idCategory);
        fillTasks(idCategory);
    }

    private void fillTableTimeLog() {

        try {
            ObservableList<TimeLog> list = FXCollections.observableArrayList(manager.getTimeLogs());
            colUser.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TimeLog, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<TimeLog, String> t) {
                    return new SimpleStringProperty(t.getValue().getUser().getOperatorCode());
                }

            });

            colCategory.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TimeLog, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<TimeLog, String> t) {
                    return new SimpleStringProperty(t.getValue().getTask().getCategory().getName());
                }
            });
            colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
            colTime.setCellValueFactory(new PropertyValueFactory<>("timeSpentOnTask"));
            colModified.setCellValueFactory(new PropertyValueFactory<>("dateModified"));
            colTask.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TimeLog, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<TimeLog, String> t) {
                    return new SimpleStringProperty(t.getValue().getTask().getName());
                }
            });

            colClient.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TimeLog, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<TimeLog, String> t) {
                    return new SimpleStringProperty(t.getValue().getClient().getName());
                }
            });

            tblTimeLog.setItems(list);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void fillCategories() {
        ObservableList<Category> list = FXCollections.observableArrayList(manager.getCategories());
        cbCategory.setItems(list);
    }

    private void fillTasks(int idCat) {
        ObservableList<Task> list = FXCollections.observableArrayList(manager.getTasksForCategory(idCat));
        cbTask.setItems(list);
    }

    private void fillUsers() {
        ObservableList<User> list = FXCollections.observableArrayList(manager.getUsers());
        cbUser.setItems(list);
    }

    private TimeLog getTimeLog() {
        //java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(myDatePicker.getValue());

        //number formart exception, null exception
        try {
            return new TimeLog(
                    cbTask.getValue(),
                    java.sql.Date.valueOf(dpTaskDate.getValue()),
                    Integer.parseInt(txtTime.getText()),
                    cbUser.getValue(),
                    cbClient.getValue());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;

    }

    private void resetFields() {
        cbCategory.setValue(null);
        cbUser.setValue(Main.currentUser);
        cbTask.setValue(null);
        cbClient.setValue(null);
        dpTaskDate.setValue(LocalDate.now());
        txtTime.setText(null);
    }

    private TimeLog getSelectedTimeLog() {
        TimeLog t = tblTimeLog.getSelectionModel().getSelectedItem();
        cbCategory.setValue(t.getTask().getCategory());
        cbUser.setValue(t.getUser());
        cbTask.setValue(t.getTask());
        txtTime.setText(String.valueOf(t.getTimeSpentOnTask()));
        cbClient.setValue(t.getClient());

        return t;
    }

    private void fillClients() {
        ObservableList<Client> list = FXCollections.observableArrayList(manager.getClients());
        cbClient.setItems(list);
    }

}
