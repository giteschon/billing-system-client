/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivanakasalo.model;

import java.io.Serializable;

/**
 *
 * @author Ivy
 */
public class Service implements  Serializable {

    private int idService;
    private String name;

    public Service() {
    }

    
    
    public Service(int idService, String name) {
        this.idService = idService;
        this.name = name;
    }

    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
