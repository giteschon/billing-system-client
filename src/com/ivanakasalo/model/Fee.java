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
public class Fee implements  Serializable {

    private int idFee;
    private String name;

    public Fee(int idFee, String name) {
        this.idFee = idFee;
        this.name = name;
    }

    public Fee() {
    }

    public Fee(int idFee) {
        this.idFee = idFee;
    }

    public int getIdFee() {
        return idFee;
    }

    public void setIdFee(int idFee) {
        this.idFee = idFee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
