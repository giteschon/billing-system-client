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
 * @author Ivy
 */
public class Specification implements  Serializable {

    private String task;
    private int time;
    private int categoryID;
    private Date dateStart;
    private Date dateEnd;

    public Specification() {
    }

    
    
    public Specification(String task, int time) {
        this.task = task;
        this.time = time;

    }

    public Specification(String task, int time, int categoryID, Date dateStart, Date dateEnd) {
        this.task = task;
        this.time = time;
        this.categoryID = categoryID;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public Specification(int categoryID, Date dateStart, Date dateEnd) {

        this.categoryID = categoryID;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

}
