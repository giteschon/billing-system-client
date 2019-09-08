/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivanakasalo.gui;

import com.ivanakasalo.model.Client;
import com.ivanakasalo.model.Invoice;
import com.ivanakasalo.model.Item;
import com.ivanakasalo.model.Service;
import com.ivanakasalo.model.Specification;
import com.ivanakasalo.threading.ClientHandler;
import com.ivanakasalo.utility.MyInitializable;
import com.ivanakasalo.utility.ReaderWriter;
import com.ivanakasalo.xml.XmlInvoice;
import static com.sun.corba.se.impl.util.Utility.printStackTrace;
import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * FXML Controller class
 *
 * @author Ivana
 */
public class InvoiceController implements MyInitializable {

         private InvoiceTemplateController template;
    private InvoiceTemplateSpecificationController specification;

    
    @FXML
    private ImageView btnPrint;

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
    private ComboBox<Client> cbClient;

    @FXML
    private DatePicker dpStart;

    @FXML
    private DatePicker dpEnd;
    

    @FXML
    void btnPdfMouseClicked(MouseEvent event) {

    }

    @FXML
    void btnPrintMouseClicked(MouseEvent event) {

    }

    @FXML
    void btnXmlEportMouseClicked(MouseEvent event) {
  XmlInvoice xml= new XmlInvoice();
        
        FileChooser saveDialog= new FileChooser();
        saveDialog.setInitialDirectory(new File(System.getProperty("user.home")));
        saveDialog.setTitle("Save invoice as xml");
       // saveDialog.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("Xml file", "*.xml"));
       saveDialog.getExtensionFilters().addAll(new ExtensionFilter("XML Files", "*.xml"),
         new ExtensionFilter("All Files", "*.*"));
        
        File file=saveDialog.showSaveDialog(null);
        
        String path= file.getPath();
        //dodavanje ekstenzije
        if (!path.endsWith(".xml")) {
            path += ".xml";
        }
        
        // System.out.println("Ovo je path: " + path);
        xml.writeToXml(path, invoice, list, listSpecifications);
    }

    @FXML
    void btnXmlImportMouseClicked(MouseEvent event) {
FileChooser openDialog= new FileChooser();
openDialog.setTitle("Open xml invoice");
openDialog.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("Xml file", "*.xml"));

File file=openDialog.showOpenDialog(null);
        String path= file.getPath();
         XmlInvoice xml= new XmlInvoice();
         
       loadInvoiceTemplate("InvoiceTemplate.fxml", invoiceContainer);
        loadSpecificationTemplate("InvoiceTemplateSpecification.fxml", specificationContainer);
         xml.readFromXml(path, template,specification);
    }
    ClientHandler con;

    
    
    @FXML
    void btnGenerateInvoiceMouseClicked(MouseEvent event) throws ClassNotFoundException {
        fillInvoiceData();
        fillSpecificationData();
        fillInvoiceItems();
        
        //notify all users sbout new invoice
        String message= "Invoice " + invoice.getInvoiceNo() + " was created by user " + Main.currentUser.toString();
        
        LoginController.handler= new ClientHandler(message, true);
        
        btnXmlExport.setDisable(false);
        
       
        
        
        //serialization
//        Invoice i= generateNewInvoice();
//        //ArrayList<Invoice> list=Serialization.invoiceList.add(i);
//        List<Invoice> list= new ArrayList<>();
//        list.add(i);
//        ReaderWriter.writeSerialization(list);
    }

   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
 btnXmlExport.setDisable(true);
        fillClients();

    }

    private List<Specification> listSpecifications = null;

    private void loadInvoiceTemplate(String fxml, AnchorPane container) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader();
            BorderPane p = fxmlLoader.load(getClass().getResource(fxml).openStream());
//dohvati controller tako da se mogu stavljat podaci iz ove scene u template 
            template = (InvoiceTemplateController) fxmlLoader.getController();

            container.getChildren().setAll(p);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            
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

            //ovo sve kaj je tak treba zamijenit alert . . .
        }
    }

    private void fillClients() {

        ObservableList<Client> list = FXCollections.observableArrayList(manager.getClients());
        cbClient.setItems(list);

    }
    private Client client;
    private Invoice invoice;

    private void fillInvoiceData() {

        try {
            //client
            client = cbClient.getValue();

            //invoice
            invoice = generateNewInvoice();

            loadInvoiceTemplate("InvoiceTemplate.fxml", invoiceContainer);

            template.setClient(client);
            template.setInvoice(invoice);

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

    }

    private Invoice generateNewInvoice() {

        try {
            Invoice invoice = new Invoice();
            invoice.setCreatedByUser(Main.currentUser);
            //invoice.setCreatedByUser(manager.getUser(2));
            invoice.setClient(cbClient.getValue());
            invoice.setInvoiceDateStart(java.sql.Date.valueOf(dpStart.getValue()));
            invoice.setInvoiceDateEnd(java.sql.Date.valueOf(dpEnd.getValue()));
            ;

            // System.out.println(java.sql.Date.valueOf(dpStart.getValue()));
            //mangager.addInvoice(invoice);
//            System.out.println(invoice.toString());
//            //System.out.println(manager.getInvoice(manager.addInvoice2(invoice)).toString());
//            int id=manager.addInvoice2(invoice);
//            System.out.println(id);
//            Invoice i2= new Invoice();
//            //get invoice mi ne readi ....
//            i2=manager.getInvoice(id);
//            System.out.println(i2.toString());
            return manager.getInvoice(manager.addInvoice2(invoice));
            // return i2;
        } catch (Exception e) {
            printStackTrace();
            System.out.println("nooo");
            return null;
        }

    }

    private void fillSpecificationData() {
        try {
            loadSpecificationTemplate("InvoiceTemplateSpecification.fxml", specificationContainer);
            int idClient = cbClient.getValue().getIdClient();
            Date dateStart = java.sql.Date.valueOf(dpStart.getValue());
            Date dateEnd = java.sql.Date.valueOf(dpEnd.getValue());
            specification.fillTableSpecification(idClient, dateStart, dateEnd);

            listSpecifications = manager.generateSpecification(idClient, dateStart, dateEnd);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            
        }

    }

    //list for serialization
    public static ArrayList<ArrayList<Item>> listofAllItems= new ArrayList<>();
    
    private ArrayList<Item> list;
    //funckije za racunske operacije, za if je monthly invoice ostalo
    private void fillInvoiceItems() throws ClassNotFoundException {
       list = new ArrayList<>();
        System.out.println(client.getFee().getName());

        if ("Monthly".equals(client.getFee().getName())) {
            list.add(new Item(1, "acounting", client.getPrice(), invoice, new Service(1, "accounting serivices")));
        } else {
            double sumMinutes = 0.0;
            if (!listSpecifications.isEmpty()) {
                for (int i = 0; i < listSpecifications.size(); i++) {
                    sumMinutes += listSpecifications.get(i).getTime();
                    System.out.println(sumMinutes);

                }
            } else {
                sumMinutes = 120;
            }

            double hours = sumMinutes / 60;
            System.out.println(hours);
            double pp = client.getPrice();
            System.out.println(pp);
            double pr = hours * pp;
            System.out.println(pr);
            double price = (double) (sumMinutes / 60) * client.getPrice();
            System.out.println(price);
            list.add(new Item(1, "Accounting services", price, invoice, new Service(1, "Accounting services")));
        }

        list.add(new Item(1, "Disbursements", 3, invoice, new Service(1, "Disbursements")));

        
//serialization

listofAllItems = DashboardController.list;
//listofAllItems= ReaderWriter.readSerialization();
listofAllItems.add(list);
        
        ReaderWriter.writeSerialization(listofAllItems);
        template.setItemsAndSum(list);

        //zove funkciju za update invoice-a
        invoice.setSum(template.invoiceSum);
        manager.addInvoiceSum(invoice);
    }

}
