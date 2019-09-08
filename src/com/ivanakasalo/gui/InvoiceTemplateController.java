/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivanakasalo.gui;

import com.ivanakasalo.model.Client;
import com.ivanakasalo.model.Invoice;
import com.ivanakasalo.model.Item;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Ivana
 */
public class InvoiceTemplateController implements Initializable {

    @FXML
    private Label txtClientName;

    @FXML
    private Label lblAddress;

    @FXML
    private Label lblCity;

    @FXML
    private Label lblOib;

    @FXML
    private Label lblInvoiceNo;

    @FXML
    private Label lblInvoiceDate;

    @FXML
    private Label lblDueDate;
    
    
    @FXML
    private GridPane gridItems;
    
    private final double VAT= 25.0;
private double exchangeRate=7.5;

public double invoiceSum=0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setClient(Client c) {
        txtClientName.setText(c.getName());
        lblAddress.setText(c.getAddress());
        lblCity.setText(c.getCity().getPostalCode() + " " + c.getCity().getName());
        lblOib.setText(c.getOib());
    }

    public void setInvoice(Invoice invoice) {
        try {
            lblInvoiceNo.setText(invoice.getInvoiceNo());
            lblInvoiceDate.setText(String.valueOf(invoice.getInvoiceDate()));
            lblDueDate.setText(String.valueOf(addDays(invoice.getInvoiceDate(), 30)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            
        }

    }
    
    public void setItemsAndSum(ArrayList<Item> items){
        gridItems.setPadding(new Insets(5,0,0,15));
        double priceHrk=0;
        double priceEur=0;
        for (int i = 0; i < items.size(); i++) {
            double price=items.get(i).getPrice();
            gridItems.add(new Label(items.get(i).getItem()), 0, i+1);
            gridItems.add(new Label(String.valueOf(Math.round(price))), 1, i+1);
            gridItems.add(new Label(String.valueOf(Math.round(price) * exchangeRate)), 2, i+1);
            
           priceHrk += (price * exchangeRate);
           priceEur += price;
            
        }
        Math.round(priceHrk);
        Math.round(priceEur);
        
        int counter=items.size() + 1;
        //total
        gridItems.add(new Label("TOTAL"), 0, counter);
        gridItems.add(new Label(String.valueOf(priceEur)), 1, counter);
        gridItems.add(new Label(String.valueOf(priceHrk)), 2, counter);
        
        counter++;
        //vat
        double vatHrk= Math.round(priceHrk) * (VAT /100);
        double vatEur= Math.round(priceEur) * (VAT /100);
        
        gridItems.add(new Label(("VAT " + String.valueOf(VAT) + "%")),0,counter);
        gridItems.add(new Label(String.valueOf(vatEur)),1,counter);
        gridItems.add(new Label(String.valueOf(vatHrk)),2,counter);
        //FUNKCIJA ZA ZBRAJANJE
        counter++;
        //total + vat -> sum
        gridItems.add(new Label("SUM"), 0, counter);
        invoiceSum=vatEur + Math.round(priceEur);
        gridItems.add(new Label(String.valueOf(invoiceSum)), 1, counter);
        gridItems.add(new Label(String.valueOf(vatHrk + Math.round(priceHrk))), 2, counter);
        
        for (Node node : gridItems.getChildren()) {
            if (node instanceof Label) {
                ((Label) node).setAlignment(Pos.CENTER_LEFT);
            }
        }
    }

    private Date addDays(Date date, int days) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return new java.sql.Date(cal.getTimeInMillis());

    }

}
