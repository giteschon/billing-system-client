/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivanakasalo.gui;

import com.ivanakasalo.model.City;
import com.ivanakasalo.model.Client;
import com.ivanakasalo.model.Fee;
import com.ivanakasalo.utility.MyInitializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Ivana
 */
public class ClientController implements MyInitializable {

    @FXML
    private ComboBox<Fee> cbFee;
    @FXML
    private TableView<Client> tblClients;

    @FXML
    private TableColumn<Client, String> colName;

    @FXML
    private Button btnEdit;

    @FXML
    private TableColumn<Client, String> colAddress;

    @FXML
    private TableColumn<Client, String> colPostalCode;

    @FXML
    private TableColumn<Client, String> colCity;

    @FXML
    private TableColumn<Client, String> colOib;

    @FXML
    private TableColumn<Client, String> colMbs;

    @FXML
    private TableColumn<Client, String> colMb;

    @FXML
    private TableColumn<Client, String> colFee;

    @FXML
    private TableColumn<Client, String> colPrice;

    @FXML
    private ComboBox<City> cbCity;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtOib;

    @FXML
    private TextField txtMbs;

    @FXML
    private TextField txtMb;

    @FXML
    private TextField txtPrice;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        fillFees();
        fillTableClients();
        fillCities();

    }

    @FXML
    void btnAddMouseClicked(MouseEvent event) {

        Client c = getClient();

        manager.addClient(c);
        fillTableClients();
    }

    private int idClient = 0;

    @FXML
    void btnEditMouseClicked(MouseEvent event) {
        manager.editClient(idClient, getClient());
        btnEdit.setDisable(true);
        fillTableClients();
        resetFields();
    }

    @FXML
    void getSelectedRow(MouseEvent event) {
        try {
            Client c = getSelectedClient();

            idClient = c.getIdClient();
            btnEdit.setDisable(false);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private Client getSelectedClient() {
        Client c = tblClients.getSelectionModel().getSelectedItem();
        txtName.setText(c.getName());
        txtAddress.setText(c.getAddress());
        cbCity.setValue(c.getCity());
        txtOib.setText(c.getOib());
        txtMbs.setText(c.getMbs());
        txtMb.setText(c.getMb());
        cbFee.setValue(c.getFee());

        return c;
    }

    private void fillFees() {
        ObservableList<Fee> list = FXCollections.observableArrayList(manager.getFees());
        cbFee.setItems(list);
    }

    private void fillTableClients() {
        ObservableList<Client> categoryList = FXCollections.observableArrayList(manager.getClients());
        colName.setCellValueFactory(new PropertyValueFactory<Client, String>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<Client, String>("address"));

        colPostalCode.setCellValueFactory(new Callback<CellDataFeatures<Client, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Client, String> c) {
                return new SimpleStringProperty(c.getValue().getCity().getPostalCode());
            }
        });

        colCity.setCellValueFactory(new Callback<CellDataFeatures<Client, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Client, String> c) {
                return new SimpleStringProperty(c.getValue().getCity().getName());
            }
        });
        colOib.setCellValueFactory(new PropertyValueFactory<Client, String>("oib"));
        colMbs.setCellValueFactory(new PropertyValueFactory<Client, String>("mbs"));
        colMb.setCellValueFactory(new PropertyValueFactory<Client, String>("mb"));

        colPrice.setCellValueFactory(new PropertyValueFactory<Client, String>("price"));

        colFee.setCellValueFactory(new Callback<CellDataFeatures<Client, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Client, String> f) {
                return new SimpleStringProperty(f.getValue().getFee().getName());
            }
        });

        tblClients.setItems(categoryList);
    }

    private void fillCities() {
        ObservableList<City> list = FXCollections.observableArrayList(manager.getCities());
        cbCity.setItems(list);
    }

    private Client getClient() {
        City city = new City();
//city.setIdCity(cbCity.getSelectionModel().getSelectedIndex());
        city.setIdCity(cbCity.getValue().getIdCity());
        Fee f = new Fee();
        f.setIdFee(cbFee.getValue().getIdFee());
        

        Client c = new Client(
                txtName.getText(),
                txtAddress.getText(),
                city,
                txtOib.getText(),
                txtMbs.getText(),
                txtMb.getText(),
                f,
                Double.valueOf(txtPrice.getText())
        );
        System.out.println(txtPrice.getText());
        System.out.println(String.valueOf(c.getPrice()));
        return c;
    }

    private void resetFields() {
        txtName.setText("");
        txtAddress.setText("");
        cbCity.setValue(null);
        txtOib.setText("");
        txtMbs.setText("");
        txtMb.setText("");
        cbFee.setValue(null);
    }

}
