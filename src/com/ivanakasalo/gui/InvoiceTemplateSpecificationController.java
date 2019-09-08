package com.ivanakasalo.gui;

import com.ivanakasalo.model.Specification;
import com.ivanakasalo.utility.MyInitializable;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class InvoiceTemplateSpecificationController implements MyInitializable {

    @FXML
    private TableView<Specification> tblSpecification;

    @FXML
    private TableColumn<Specification, String> coltask;

    @FXML
    private TableColumn<Specification, Integer> colTime;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void fillTableSpecification(int idClient, Date dateStart, Date dateEnd) {
        try {
            ObservableList<Specification> list = FXCollections.observableArrayList(manager.generateSpecification(idClient, dateStart, dateEnd));

            coltask.setCellValueFactory(new PropertyValueFactory<Specification, String>("task"));
            colTime.setCellValueFactory(new PropertyValueFactory<Specification, Integer>("time"));
            tblSpecification.setItems(list);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

     public void fillTableSpecification(int idClient, List<Specification> specs ) {
        try {
            ObservableList<Specification> list = FXCollections.observableArrayList(specs);

            coltask.setCellValueFactory(new PropertyValueFactory<Specification, String>("task"));
            colTime.setCellValueFactory(new PropertyValueFactory<Specification, Integer>("time"));
            tblSpecification.setItems(list);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}
