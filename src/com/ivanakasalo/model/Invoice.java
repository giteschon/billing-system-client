/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivanakasalo.model;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Ivana
 */
public class Invoice implements  Serializable {

    private int idInvoice;
    private User createdByUser;
    private String invoiceNo;
    private Date invoiceDate;
    private Client client;
    private double sum;
    private Date invoiceDateStart;
    private Date invoiceDateEnd;

    public Invoice(int idInvoice, User createdByUser, String invoiceNo, Date invoiceDate, Client client, double sum, Date invoiceDateStart, Date invoiceDateEnd) {
        this.idInvoice = idInvoice;
        this.createdByUser = createdByUser;
        this.invoiceNo = invoiceNo;
        this.invoiceDate = invoiceDate;
        this.client = client;
        this.sum = sum;
        this.invoiceDateStart = invoiceDateStart;
        this.invoiceDateEnd = invoiceDateEnd;
    }

    public Invoice(int idInvoice, User createdByUser, String invoiceNo, Date invoiceDate, Client client, double sum) {
        this.idInvoice = idInvoice;
        this.createdByUser = createdByUser;
        this.invoiceNo = invoiceNo;
        this.invoiceDate = invoiceDate;
        this.client = client;
        this.sum = sum;
    }

    public Date getInvoiceDateStart() {
        return invoiceDateStart;
    }

    public void setInvoiceDateStart(Date invoiceDateStart) {
        this.invoiceDateStart = invoiceDateStart;
    }

    public Date getInvoiceDateEnd() {
        return invoiceDateEnd;
    }

    public void setInvoiceDateEnd(Date invoiceDateEnd) {
        this.invoiceDateEnd = invoiceDateEnd;
    }

    public int getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(int idInvoice) {
        this.idInvoice = idInvoice;
    }

    public User getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(User createdByUser) {
        this.createdByUser = createdByUser;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public Invoice() {
    }

    @Override
    public String toString() {
        return "Invoice{" + "idInvoice=" + idInvoice + ", createdByUser=" + createdByUser + ", invoiceNo=" + invoiceNo + ", invoiceDate=" + invoiceDate + ", client=" + client + ", sum=" + sum + ", invoiceDateStart=" + invoiceDateStart + ", invoiceDateEnd=" + invoiceDateEnd + '}';
    }

   

}
