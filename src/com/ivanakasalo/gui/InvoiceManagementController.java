package com.ivanakasalo.gui;

import com.ivanakasalo.model.Invoice;
import com.ivanakasalo.utility.MyInitializable;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

public class InvoiceManagementController implements MyInitializable {

    @FXML
    private TableView<Invoice> tblCategory;

    @FXML
    private TableColumn<Invoice, String> colinvoiceNo;

    @FXML
    private TableColumn<Invoice, String> colClient;

    @FXML
    private TableColumn<Invoice, String> colUser;

    @FXML
    private TableColumn<Invoice, String> colInvoiceDate;

    @FXML
    private TableColumn<Invoice, String> colSum;

    @FXML
    private TableColumn<Invoice, String> colFrom;

    @FXML
    private TableColumn<Invoice, String> colTo;

    public static Invoice selectedInvoice = null;

    @FXML
    void getSelectedInvoice(MouseEvent event) throws IOException {
        if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
            selectedInvoice = setInvoice();
            openInvoice();
        }

    }

    public Invoice setInvoice() {
        return tblCategory.getSelectionModel().getSelectedItem();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillTableInvoices();
    }

    private void fillTableInvoices() {
        ObservableList<Invoice> list = FXCollections.observableArrayList(manager.getInvoices());

        colClient.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Invoice, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Invoice, String> i) {
                return new SimpleStringProperty(i.getValue().getClient().getName());
            }
        });

        colinvoiceNo.setCellValueFactory(new PropertyValueFactory("invoiceNo"));
        colInvoiceDate.setCellValueFactory(new PropertyValueFactory("invoiceDate"));
        colSum.setCellValueFactory(new PropertyValueFactory("sum"));
        colFrom.setCellValueFactory(new PropertyValueFactory("invoiceDateStart"));
        colTo.setCellValueFactory(new PropertyValueFactory("invoiceDateEnd"));

        colUser.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Invoice, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Invoice, String> u) {
                return new SimpleStringProperty(u.getValue().getCreatedByUser().getOperatorCode());
            }
        });

        tblCategory.setItems(list);
    }

    private void openInvoice() throws IOException {
        FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("OpenInvoice.fxml"));
        Parent root = (Parent) fXMLLoader.load();
        Stage stage = new Stage();
        stage.setScene((new Scene(root)));
        stage.show();
    }

}
