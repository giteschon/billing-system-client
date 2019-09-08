/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivanakasalo.gui;

import com.ivanakasalo.model.Client;
import com.ivanakasalo.model.Invoice;
import com.ivanakasalo.model.Item;
import com.ivanakasalo.utility.MyInitializable;
import com.ivanakasalo.utility.ReaderWriter;
import com.ivanakasalo.xml.XmlInvoice;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Ivana
 */
public class OpenInvoiceController implements MyInitializable {
  
   
//    @FXML;
//    private ImageView btnPrint;

    @FXML
    private ImageView btnPdf;

    @FXML
    private ImageView btnXmlExport;

    @FXML
    private ImageView btnXmlImport;

    @FXML
    private Tab btnInvoice;

    @FXML
    private AnchorPane invoiceContainer;

    @FXML
    private Tab btnSpecification;

    @FXML
    private AnchorPane specificationContainer;

    @FXML
    void btnPdfMouseClicked(MouseEvent event) {

    }

    @FXML
    void btnPrintMouseClicked(MouseEvent event) {

    }

    @FXML
    void btnXmlEportMouseClicked(MouseEvent event) {
      
    }

    @FXML
    void btnXmlImportMouseClicked(MouseEvent event) {

    }


    private InvoiceTemplateController template;
    private InvoiceTemplateSpecificationController specification;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillInvoiceData();
        fillSpecificationData();
        fillInvoiceDetails();
       
    }

    private void loadInvoiceTemplate(String fxml, AnchorPane container) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader();
            BorderPane p = fxmlLoader.load(getClass().getResource(fxml).openStream());

//dohvati controller tako da se mogu stavljat podaci iz ove scene u template 
            template = (InvoiceTemplateController) fxmlLoader.getController();

            container.getChildren().setAll(p);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("lele");
            //ovo sve kaj je tak treba zamijenit alert . . .
        }
    }

    private void loadSpecificationTemplate(String fxml, AnchorPane container) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader();
            BorderPane p = fxmlLoader.load(getClass().getResource(fxml).openStream());
//dohvati controller tako da se mogu stavljat podaci iz ove scene u template 
            specification = (InvoiceTemplateSpecificationController) fxmlLoader.getController();

            container.getChildren().setAll(p);

        } catch (Exception e) {
            System.out.println(e.getMessage());

           
        }
    }
private Invoice i;
private Client c;
    private void fillInvoiceData() {

        try {
             i = InvoiceManagementController.selectedInvoice;
c=i.getClient();
            loadInvoiceTemplate("InvoiceTemplate.fxml", invoiceContainer);
template.setClient(c);
            //template.setClient(i.getClient());
            template.setInvoice(i);
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

    }

    private void fillSpecificationData() {
        try {
            loadSpecificationTemplate("InvoiceTemplateSpecification.fxml", specificationContainer);
            i = InvoiceManagementController.selectedInvoice;
            specification.fillTableSpecification(i.getClient().getIdClient(), i.getInvoiceDateStart(), i.getInvoiceDateEnd());

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

    }
    
//      private void fillInvoiceItems() {
//        ArrayList<Item> list = new ArrayList<>();
//        System.out.println(c.getFee().getName());
//
//        if (c.getFee().getName() == "Monthly") {
//            list.add(new Item(1, "acounting", c.getPrice(), i, new Service(1, "accounting serivices")));
//        } else {
//            double sumMinutes = 0.0;
//            if (!listSpecifications.isEmpty()) {
//                for (int i = 0; i < listSpecifications.size(); i++) {
//                    sumMinutes += listSpecifications.get(i).getTime();
//                    System.out.println(sumMinutes);
//
//                }
//            } else {
//                sumMinutes = 120;
//            }
//
//            double hours = sumMinutes / 60;
//            System.out.println(hours);
//            double pp = c.getPrice();
//            System.out.println(pp);
//            double pr = hours * pp;
//            System.out.println(pr);
//            double price = (double) (sumMinutes / 60) * c.getPrice();
//            System.out.println(price);
//            list.add(new Item(1, "Accounting services", price, c, new Service(1, "Accounting services")));
//        }
//
//        list.add(new Item(1, "Disbursements", 3, c, new Service(1, "Disbursements")));
//
//        template.setItemsAndSum(list);
//
//        //zove funkciju
//        i.setSum(template.invoiceSum);
//        manager.addInvoiceSum(i);
//    }

    private void fillInvoiceDetails() {
        //read serialization
        try {
            i = InvoiceManagementController.selectedInvoice;
            ArrayList<ArrayList<Item>> list=ReaderWriter.readSerialization();
            
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).get(0).getInvoice().getIdInvoice() == i.getIdInvoice()) {
                    template.setItemsAndSum(list.get(j));
                    break;
                }
                if (!(list.get(j).get(0).getInvoice().equals(i))) {
                    System.out.println(i.toString());
                    //dodati racun i dodati ga na template
                    //total treba zaokruzit!!!
                }
                
            }
            
        } catch (Exception e) {
        }
    }


}
