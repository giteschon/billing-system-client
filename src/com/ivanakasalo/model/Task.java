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
public class Task implements  Serializable{

    private int idTask;
    private String name;
    private Category category;

    public Task() {
    }

    
    
    public Task(int idTask, String name, Category category) {
        this.idTask = idTask;
        this.name = name;
        this.category = category;
    }

    public Task(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public int getIdTask() {
        return idTask;
    }

    public void setIdTask(int idTask) {
        this.idTask = idTask;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return name;
    }

}
