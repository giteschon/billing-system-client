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
public class User implements  Serializable {

    private int idUser;
    private String name;
    private String surname;
    private String username;
    private String password;
    private String operatorCode;

    public User() {
    }

    
    
    public User(String name, String surname, String username, String password, String operatorCode) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.operatorCode = operatorCode;
    }

    public User(int idUser, String name, String surname, String username, String password, String operatorCode) {
        this.idUser = idUser;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.operatorCode = operatorCode;
    }

    public User(String name, String surname, String username, String password) {

        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }

}
