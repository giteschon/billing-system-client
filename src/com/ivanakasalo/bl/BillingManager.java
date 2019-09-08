/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivanakasalo.bl;

import com.ivanakasalo.dal.IRepository;
import com.ivanakasalo.dal.RepositoryFactory;
import com.ivanakasalo.gui.LoginController;
import com.ivanakasalo.model.Category;
import com.ivanakasalo.model.City;
import com.ivanakasalo.model.Client;
import com.ivanakasalo.model.Fee;
import com.ivanakasalo.model.Invoice;
import com.ivanakasalo.model.Item;
import com.ivanakasalo.model.Specification;
import com.ivanakasalo.model.Task;
import com.ivanakasalo.model.TimeLog;
import com.ivanakasalo.model.User;
import com.ivanakasalo.threading.ClientHandler;
import com.ivanakasalo.threading.SavingData;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *This class is a Businss Logic Layer which is used as a bridge between Data Logic Layer and Presentation Layer
 * @author Ivana Kasalo
 */
public class BillingManager implements IManager {

    IRepository repo = RepositoryFactory.getSqlRepo();

    ClientHandler ch = LoginController.handler;
    
    public static boolean saveInProgress=false;
    
  

//    @Override
//    public synchronized void addCategory(String category) {
//
////        SavingData saving= new SavingData(saveInProgress);
////        saving.sendToServer();
////        saveInProgress= saving.readFromServer();
////        try {
////            Thread.sleep(1000);
////        } catch (InterruptedException ex) {
////            Logger.getLogger(BillingManager.class.getName()).log(Level.SEVERE, null, ex);
////        }
//////        while (saveInProgress) {            
//////        while (ch.isSaveInProgress()) {
//////            try {
//////                System.out.println("waiting to save ...");
//////                Thread.sleep(1000);
//////                wait();
//////            } catch (InterruptedException ex) {
//////                Logger.getLogger(BillingManager.class.getName()).log(Level.SEVERE, null, ex);
//////            }
//////        }
//////        
//////        //when button is clicked, saving is in progress
//////        ch.setSaveInProgress(true);
//////
//////        Thread t = new Thread(new Runnable() {
//////            @Override
//////            public void run() {
//////                Category c = new Category(category);
//////                repo.addCategory(c);
//////            }
//////        });
//////
//////        t.start();
//////
//////        System.out.println("Category saved");
//////
//////        try {
//////            t.sleep(5000);
//////            System.out.println("Other things can be continued");
//////        } catch (InterruptedException ex) {
//////            Logger.getLogger(BillingManager.class.getName()).log(Level.SEVERE, null, ex);
//////        }
//////
//////        ch.setSaveInProgress(false);
//////        notifyAll();
//
//
//while(saveInProgress){
//try {
//                System.out.println("waiting to save ...");
//                Thread.sleep(1000);
//                wait();
//            } catch (InterruptedException ex) {
//                Logger.getLogger(BillingManager.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        
//}
//saveInProgress=true;
//Thread t = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                
//                Category c = new Category(category);
//                repo.addCategory(c);
//                
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(BillingManager.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        });
//
//t.start();
//
////nepotrebno -> ceka maksimalno tolko ...
////synchronized(t){
////   t.start();
////        try {
////            //die after 5sec
////            t.join(5000);
////            
////          
////            
////          
////        } catch (InterruptedException ex) {
////            Logger.getLogger(BillingManager.class.getName()).log(Level.SEVERE, null, ex);
////        }
////}
//      
//        
//
//        
//
////Category c = new Category(category);
////                repo.addCategory(c);
//                System.out.println("Category saved");
//                
////                  while (true) {                
////            if (!t.isAlive()) {
////                  saveInProgress=false; 
////                  break;
////            }    
////            }
//              
//saveInProgress=false;
//notifyAll();
//    
//              
//            
//    }

    
      @Override
    public synchronized void addCategory(String category) {

while(saveInProgress){
try {
                System.out.println("waiting to save ...");
              
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(BillingManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        
}

saveInProgress=true;

//tu je bio problem kaj je ceko 5 sek da ju pretvori u true iz nekog razloga
                Category c = new Category(category);
                repo.addCategory(c);
                
                System.out.println("Category saved");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(BillingManager.class.getName()).log(Level.SEVERE, null, ex);
                }
          
                
                 
saveInProgress=false;
notifyAll();
 
    }
    
     @Override
    public synchronized void editCategory(String category, int idCategory) {
      while(saveInProgress){
try {
                System.out.println("waiting to save ...");
              
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(BillingManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        
}

saveInProgress=true;


                Category c = new Category(category);
        repo.editCategory(c, idCategory);
        
           System.out.println("Category edited");
             try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(BillingManager.class.getName()).log(Level.SEVERE, null, ex);
                }   
                
          
             
                 
saveInProgress=false;
notifyAll();
        
//        Category c = new Category(category);
//        repo.editCategory(c, idCategory);
    }
    
    
     @Override
    public Category getCategory(int idCategory) {
        return repo.getCategory(idCategory);
    }
    
    
      @Override
    public List<Category> getCategories() {
        return repo.getCategories();
    }
    
    @Override
    public Fee getFee(int idFee) {
        return repo.getFee(idFee);
    }

    @Override
    public List<Fee> getFees() {
        List<Fee> list = repo.getFees();

        return list;

    }

    @Override
    public List<Client> getClients() {
        return repo.getClients();
    }

    @Override
    public Client getClient(int idClient) {
        return repo.getClient(idClient);
    }

    @Override
    public User getUser(int idUser) {
        return repo.getUser(idUser);
    }

    @Override
    public List<User> getUsers() {
        return repo.getUsers();
    }

    @Override
    public Task getTask(int idTask) {
        return repo.getTask(idTask);
    }

    @Override
    public List<Task> getTasks() {
        return repo.getTasks();
    }

   

  

    @Override
    public City getCity(int idCity) {
        return repo.getCity(idCity);
    }

    @Override
    public List<City> getCities() {
        return repo.getCities();
    }

    @Override
    public TimeLog getTimeLog(int idTimeLog) {
        return repo.getTimeLog(idTimeLog);
    }

    @Override
    public List<TimeLog> getTimeLogs() {
        return repo.getTimeLogs();
    }

    @Override
    public User getCredentials(String username, String password) {
        return repo.getCredentials(username, password);
    }

    @Override
    public boolean checkCredentials(String username, String password) {
        List<User> users = repo.getUsers();
        boolean loginSuccess = true;
        for (User u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                loginSuccess = true;
                return loginSuccess;
            } else {
                loginSuccess = false;
            }
        }
        return loginSuccess;

    }

   

    @Override
    public void addClient(Client client) {
        repo.addClient(client);
    }

    @Override
    public void editClient(int idClient, Client client) {
        repo.editClient(idClient, client);
    }

    @Override
    public synchronized void addUser(User user) {
//        Thread thread= new Thread(new Runnable(){
//        
//            @Override
//            public void run() {
//                repo.addUser(user); 
//                
//            }
//        
//        
//        });
//        user.notifyAll();

        repo.addUser(user);

    }

    @Override
    public void editUser(int idUser, User user) {
        repo.editUser(idUser, user);
    }

    @Override
    public List<Task> getTasksForCategory(int idCategory) {
        return repo.getTasksForCategory(idCategory);
    }

    @Override
    public void addTask(Task task) {
        repo.addTask(task);
    }

    @Override
    public void editTask(int idTask, Task task) {
        repo.editTask(idTask, task);
    }

    @Override
    public void addTimeLog(TimeLog timeLog) {
        repo.addTimeLog(timeLog);
    }

    @Override
    public void editTimeLog(int idTimeLog, TimeLog timeLog) {
        repo.editTimeLog(idTimeLog, timeLog);
    }

    @Override
    public void addInvoice(Invoice invoice) {
        //repo.addInvoice(invoice);
    }

    @Override
    public void addInvoiceSum(Invoice invoice) {
        repo.addInvoiceSum(invoice);
    }

    @Override
    public void addDefaultItem(Item item) {
        repo.addDefaultItem(item);
    }

    @Override
    public void addOtherItem(Item item) {
        repo.addOtherItem(item);
    }

    @Override
    public Invoice getInvoice(int idInvoice) {
        return repo.getInvoice(idInvoice);
    }

    @Override
    public int addInvoice2(Invoice invoice) {
        return repo.addInvoice2(invoice);
    }

    @Override
    public List<Specification> generateSpecification(int clientId, Date dateStart, Date dateEnd) {
        return repo.generateSpecification(clientId, dateStart, dateEnd);
    }

    @Override
    public List<Invoice> getInvoices() {
        return repo.getInvoices();
    }

   
}
