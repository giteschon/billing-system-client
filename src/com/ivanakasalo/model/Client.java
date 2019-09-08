/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivanakasalo.model;

import java.io.Serializable;

/**
 *
 * @author Ivana
 */
public class Client implements  Serializable{

    private int idClient;
    private String name;
    private String address;
    private City city;

    private String oib;
    private String mbs;
    private String mb;
    private Fee fee;
    private double price;

    public Client() {
    }

    public Client(String name, String address, City city, String oib, String mbs, String mb, Fee fee, double price) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.oib = oib;
        this.mbs = mbs;
        this.mb = mb;
        this.fee = fee;
        this.price = price;
    }

    public Client(int idClient, String name, String address, City city, String oib, String mbs, String mb, Fee fee, double price) {
        this.idClient = idClient;
        this.name = name;
        this.address = address;
        this.city = city;
        this.oib = oib;
        this.mbs = mbs;
        this.mb = mb;
        this.fee = fee;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getOib() {
        return oib;
    }

    public void setOib(String oib) {
        this.oib = oib;
    }

    public String getMbs() {
        return mbs;
    }

    public void setMbs(String mbs) {
        this.mbs = mbs;
    }

    public String getMb() {
        return mb;
    }

    public void setMb(String mb) {
        this.mb = mb;
    }

    public Fee getFee() {
        return fee;
    }

    public void setFee(Fee fee) {
        this.fee = fee;
    }

    @Override
    public String toString() {
        return name;
    }

}
