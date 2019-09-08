package com.ivanakasalo.dal;

import com.ivanakasalo.model.*;
import java.sql.Date;
import java.util.List;

public interface IRepository {
    //CRUD

    void addCategory(Category category);

    void editCategory(Category dummy, int idCategory);

    void addTask(Task task);

    void editTask(int idTask, Task task);

    void addUser(User user);

    void editUser(int idUser, User user);

    void addClient(Client client);

    void editClient(int idClient, Client dummy);

    void addTimeLog(TimeLog timeLog);

    void editTimeLog(int idTimeLog, TimeLog timeLog);

//void addInvoice(Invoice invoice);
    void addInvoiceSum(Invoice invoice);

    int addInvoice2(Invoice invoice);

    List<Specification> generateSpecification(int clientId, Date dateStart, Date dateEnd);

    void addDefaultItem(Item item);

    void addOtherItem(Item item);

    Fee getFee(int idFee);

    List<Fee> getFees();

    Client getClient(int idClient);

    List<Client> getClients();

    User getUser(int idUser);

    List<User> getUsers();

    Task getTask(int idTask);

    List<Task> getTasks();

    List<Task> getTasksForCategory(int idCategory);

    Category getCategory(int idCategory);

    List<Category> getCategories();

    City getCity(int idCity);

    List<City> getCities();

    Invoice getInvoice(int idInvoice);

    List<Invoice> getInvoices();

    TimeLog getTimeLog(int idTimeLog);

    List<TimeLog> getTimeLogs();

    Item getItem(int idItem);

    List<Item> getItems();

    List<Record> getRecords();

    User getCredentials(String username, String password);

}
