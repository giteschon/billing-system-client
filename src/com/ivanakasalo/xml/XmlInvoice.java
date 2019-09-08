/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template xmlFile, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivanakasalo.xml;

import com.ivanakasalo.gui.InvoiceTemplateController;
import com.ivanakasalo.gui.InvoiceTemplateSpecificationController;
import com.ivanakasalo.model.City;
import com.ivanakasalo.model.Client;
import com.ivanakasalo.model.Invoice;
import com.ivanakasalo.model.Item;
import com.ivanakasalo.model.Service;
import com.ivanakasalo.model.Specification;
import com.ivanakasalo.model.Task;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 * Class used for writing invoices to XML and reading them from XML
 *
 * @author Ivana Kasalo
 */
public class XmlInvoice {

    private Document doc;
    private static final String VAT = "25%";

    //Item is invoice data, it contains details and invoice (invoice has client details)
    /**
     * Method writes invoice data to XML
     *
     * @param path Location where invoices will be saved and XML document name
     * @param invoice Invoice which will be saved in .xml
     * @param list List of all items in invoice (cro. Stavke)
     * @param specification Time log, represent how much time was spent on
     * specific task
     */
    public void writeToXml(String path, Invoice invoice, ArrayList<Item> list, List<Specification> specification) {
//RADI, ALI TREBA NAMJESTIT KOD SPREMANJA EXTENZIJU I TREBA REFAKTORIRAT KOD TJ. RJESIT ITEM-E
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            //pazi koji je import
            doc = docBuilder.newDocument();
            Element root = doc.createElement("invoice");
            doc.appendChild(root);

            //invoice data
            // Invoice i = invoice.getInvoice();
            createElement("invoiceNo", root, invoice.getInvoiceNo());
            createElement("invoiceDate", root, String.valueOf(invoice.getInvoiceDate()));

            createElement("invoiceSum", root, String.valueOf(invoice.getSum()));
            createElement("currency", root, EUR);
            createElement("vat", root, VAT);

            //client node
            Element client = doc.createElement("client");
            root.appendChild(client);

            //Client c = invoice.getInvoice().getClient();
            Client c = invoice.getClient();
            createElement("clientName", client, c.getName());
            createElement("address", client, c.getAddress());
            createElement("postCode", client, c.getCity().getPostalCode());
            createElement("city", client, c.getCity().getName());
            createElement("oib", client, c.getOib());

            //items node
            Element items = doc.createElement("items");
            root.appendChild(items);

            createElement("accounting", items, String.valueOf(list.get(0).getPrice()));
            createElement("disbursements", items, String.valueOf(list.get(1).getPrice()));

            //time log nodes
            Element timeLog = doc.createElement("specification");
            root.appendChild(timeLog);

            //treba mi task i time, znaci atributi
            for (int i = 0; i < specification.size(); i++) {

                Element log = doc.createElement("log" + i);
                timeLog.appendChild(log);
                createElement("task", log, specification.get(i).getTask());
                createElement("time", log, String.valueOf(specification.get(i).getTime()));

            }

            saveToXml(path);

        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XmlInvoice.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    private static final String EUR = "EUR";

    private void createElement(String nodeName, Element parentNode, String nodeValue) {
        Element node = doc.createElement(nodeName);
        node.appendChild(doc.createTextNode(nodeValue));
        parentNode.appendChild(node);

    }

    private void saveToXml(String path) {

        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
//dodavanje encodinga
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            //dodavanje entera(line break) nakon cvora
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(path));

            transformer.transform(source, result);

            showDialog("XML file was saved successfully", Alert.AlertType.INFORMATION);

        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(XmlInvoice.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            showDialog("XML file was NOT saved successfully", Alert.AlertType.ERROR);
        } catch (TransformerException ex) {
            Logger.getLogger(XmlInvoice.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            showDialog("XML file was NOT saved successfully", Alert.AlertType.ERROR);
        }
    }

    private void showDialog(String message, Alert.AlertType alertType) {
        Alert window = new Alert(alertType);
        if (alertType == Alert.AlertType.INFORMATION) {
            window.setTitle("Success!");
        } else {
            window.setTitle("Uuups! Something has gone wrong...");
        }
        window.setContentText(message);
        window.showAndWait();
    }

    private Document docSave;

    /**
     * Method read invoice from xml and shows it on InvoiceTemplate scene
     *
     * @param path Invoice location and name
     * @param template Scene on which invoices are shown
     * @param specification
     */
    public void readFromXml(String path, InvoiceTemplateController template, InvoiceTemplateSpecificationController specification) {
        try {
            File xmlFile = new File(path);

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            docSave = docBuilder.parse(xmlFile);

            //mice sve komentare i prazne elemente, tj. cita samo cvoorove
            docSave.getDocumentElement().normalize();

            Invoice invoice = new Invoice();
            String invoiceNo = getElementByTagName("invoiceNo");
            invoice.setInvoiceNo(invoiceNo);

            String invoiceDate = getElementByTagName("invoiceDate");
            invoice.setInvoiceDate(Date.valueOf(invoiceDate));

            Double invoiceSum = Double.valueOf(getElementByTagName("invoiceSum"));
            invoice.setSum(invoiceSum);

            Client client = new Client();

            String clientName = getElementByTagName("clientName");
            System.out.println(clientName);
            client.setName(clientName);

            String address = getElementByTagName("address");
            client.setAddress(address);

            City city = new City();
            String cityName = getElementByTagName("city");
            city.setName(cityName);

            String postCode = getElementByTagName("postCode");
            city.setPostalCode(postCode);

            String oib = getElementByTagName("oib");
            client.setOib(oib);

            client.setCity(city);

            //items
            ArrayList<Item> items = new ArrayList<>();
            Double accPrice = Double.valueOf(getElementByTagName("accounting"));
            items.add(new Item(1, "Accounting services", accPrice, invoice, new Service(1, "Accounting services")));

            Double disbPrice = Double.valueOf(getElementByTagName("disbursements"));
            items.add(new Item(1, "Disbursements", disbPrice, invoice, new Service(1, "Disbursements")));

//count how many logs..
            NodeList nodeList = docSave.getElementsByTagName("specification");

//time log
            List<Specification> listSpecifications = new ArrayList<>();

            for (int i = 0; i < nodeList.getLength(); i++) {

                String task = docSave.getElementsByTagName("task").item(i).getTextContent();
                int time = Integer.valueOf(docSave.getElementsByTagName("time").item(i).getTextContent());

                listSpecifications.add(new Specification(task, time));
            }

            template.setClient(client);
            template.setInvoice(invoice);
            template.setItemsAndSum(items);

            specification.fillTableSpecification(client.getIdClient(), listSpecifications);

        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(XmlInvoice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String getElementByTagName(String tagName) {
        return docSave.getElementsByTagName(tagName).item(0).getTextContent();
    }

}
