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
public class TimeLog implements  Serializable{

    private int idTimeLog;
    private Task task;
    private Date dateModified;
    private Date date;
    private int timeSpentOnTask;
    private User user;
    private Client client;

    public TimeLog(int idTimeLog, Task task, Date dateModified, Date date, int timeSpentOnTask, User user, Client client) {
        this.idTimeLog = idTimeLog;
        this.task = task;
        this.dateModified = dateModified;
        this.date = date;
        this.timeSpentOnTask = timeSpentOnTask;
        this.user = user;
        this.client = client;
    }

    public TimeLog(int idTimeLog, Task task, Date date, int timeSpentOnTask, User user, Client client) {
        this.idTimeLog = idTimeLog;
        this.task = task;
        this.date = date;
        this.timeSpentOnTask = timeSpentOnTask;
        this.user = user;
        this.client = client;
    }

    public TimeLog() {

    }

    public TimeLog(Task task, int timeSpentOnTask, User user, Client client) {
        this.task = task;
        this.timeSpentOnTask = timeSpentOnTask;
        this.user = user;
        this.client = client;
    }

    public TimeLog(Task task, Date date, int timeSpentOnTask, User user, Client client) {
        this.task = task;
        this.date = date;
        this.timeSpentOnTask = timeSpentOnTask;
        this.user = user;
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getIdTimeLog() {
        return idTimeLog;
    }

    public void setIdTimeLog(int idTimeLog) {
        this.idTimeLog = idTimeLog;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTimeSpentOnTask() {
        return timeSpentOnTask;
    }

    public void setTimeSpentOnTask(int timeSpentOnTask) {
        this.timeSpentOnTask = timeSpentOnTask;
    }

    @Override
    public String toString() {
        return "TimeLog{" + "idTimeLog=" + idTimeLog + ", task=" + task + ", dateModified=" + dateModified + ", date=" + date + ", timeSpentOnTask=" + timeSpentOnTask + ", user=" + user + '}';
    }

}
