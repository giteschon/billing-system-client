/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivanakasalo.bl;

import com.ivanakasalo.model.*;
import java.sql.Date;

import java.util.List;

/**
 *Interface for Business Logic Layer
 *@author Ivana Kasalo
 */
public interface IManager {

    void addCategory(String category);

    void editCategory(String category, int idCategory);

    void addTask(Task task);

    void editTask(int idTask, Task task);

    void addClient(Client client);

    void editClient(int idClient, Client client);

    void addUser(User user);

    void editUser(int idUser, User user);

    void addTimeLog(TimeLog timeLog);

    void editTimeLog(int idTimeLog, TimeLog timeLog);

    Fee getFee(int idFee);

    List<Fee> getFees();

    Client getClient(int idClient);

    List<Client> getClients();

    User getUser(int idUser);

    List<User> getUsers();

    Task getTask(int idTask);

    List<Task> getTasks();

    Category getCategory(int idCategory);

    List<Category> getCategories();

    City getCity(int idCity);

    List<City> getCities();

    Invoice getInvoice(int idInvoice);

    List<Invoice> getInvoices();

    TimeLog getTimeLog(int idTimeLog);

    List<TimeLog> getTimeLogs();
//Item getItem(int idItem);
//List<Item> getItems();
//List<Record> getRecords();
//Service getService(int idService);
//List<Service> getServices();

    List<Task> getTasksForCategory(int idCategory);

    void addInvoice(Invoice invoice);

    int addInvoice2(Invoice invoice);

    void addInvoiceSum(Invoice invoice);

    void addDefaultItem(Item item);

    void addOtherItem(Item item);

    List<Specification> generateSpecification(int clientId, Date dateStart, Date dateEnd);

//login
    User getCredentials(String username, String password);

    boolean checkCredentials(String username, String password);

}
