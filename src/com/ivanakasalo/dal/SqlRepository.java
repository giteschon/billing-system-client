package com.ivanakasalo.dal;

import com.ivanakasalo.model.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author office10
 */
public class SqlRepository implements IRepository {

    private static final String ADD_CATEGORY = "{CALL addCategory (?)}";
    private static final String ADD_TASK = "{CALL addTask (?,?)}";
    private static final String EDIT_TASK = "{CALL editTask (?,?,?)}";
    private static final String ADD_USER = "{CALL addUser (?,?,?,?,?)}";
    private static final String EDIT_USER = "{CALL addUser (?,?,?,?,?,?)}";
    private static final String ADD_CLIENT = "{CALL addClient (?,?,?,?,?,?,?,?)}";
    private static final String EDIT_CLIENT = "{CALL editClient (?,?,?,?,?,?,?,?,?)}";
    private static final String EDIT_CATEGORY = "{CALL editCategory (?,?)}";
    private static final String ADD_TIMELOG = "{CALL addTimeLog (?,?,?,?,?)}";
    private static final String EDIT_TIMELOG = "{CALL editTimeLog (?,?,?,?,?,?)}";

    private static final String ADD_INVOICE = "{CALL addIncvoice (?,?,?,?)}";
    private static final String ADD_INVOICE_SUM = "{CALL addInvoiceSum (?,?)}";
    private static final String ADD_DEFAULT_ITEM = "{CALL addDefaultItem (?,?)}";
    private static final String ADD_OTHER_ITEM = "{CALL addOtherItems (?,?,?)}";

    private static final String ADD_INVOICE2 = "{CALL addIncvoice (?,?,?,?,?)}";
    private static final String GENERATE_SPECIFICATION = "{CALL generateSpecification (?,?,?)}";

    private static final String GET_FEE = "{CALL getFee (?)}";
    private static final String GET_FEES = "{CALL getFees}";
    private static final String GET_CLIENT = "{CALL getClient (?)}";
    private static final String GET_CLIENTS = "{CALL getClients}";
    private static final String GET_USER = "{CALL getUser (?)}";
    private static final String GET_USERS = "{CALL getUsers}";
    private static final String GET_TASK = "{CALL getTask (?)}";
    private static final String GET_TASKS = "{CALL getTasks}";
    private static final String GET_TASKS_FOR_CATEGORY = "{CALL getTasksForCategory (?)}";
    private static final String GET_CATEGORY = "{CALL getCategory (?)}";
    private static final String GET_CATEGORIES = "{CALL getCategories}";
    private static final String GET_CITY = "{CALL getCity (?)}";
    private static final String GET_CITIES = "{CALL getCities}";
    private static final String GET_INVOICE = "{CALL getInvoice (?)}";
    private static final String GET_INVOICES = "{CALL getInvoices}";
    private static final String GET_TIMELOG = "{CALL getTimeLog (?)}";
    private static final String GET_TIMELOGS = "{CALL getTimeLogs}";
    private static final String GET_ITEM = "{CALL getItem (?)}";
    private static final String GET_ITEMS = "{CALL getItems}";

    private static final String GET_RECORDS = "{CALL getRecords}";
    private static final String GET_SERVICE = "{CALL getService (?)}";
    private static final String GET_SERVICES = "{CALL getServices}";

    private static final String GET_CREDENTIALS = "{CALL getCredentials (?,?)}";

    private DataSource dataSource;

    @Override
    public void addCategory(Category category) {
        DataSource ds = DataSourceSingleton.getInstance();
        try (
                Connection con = ds.getConnection();
                CallableStatement stmt = con.prepareCall(ADD_CATEGORY);) {
            stmt.setString(1, category.getName());

            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //return -1;
    }

    @Override
    public Fee getFee(int idFee) {
        dataSource = DataSourceSingleton.getInstance();

        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_FEE);) {
            stmt.setInt(1, idFee);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Fee(
                            rs.getInt(1),
                            rs.getString(2)
                    );
                }
            }

        } catch (Exception e) {
            
        }
        return null;
    }

    @Override
    public List<Fee> getFees() {
        List<Fee> list = new ArrayList<Fee>();

        dataSource = DataSourceSingleton.getInstance();
        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_FEES);
                ResultSet re = stmt.executeQuery();) {
            while (re.next()) {
                list.add(
                        new Fee(
                                re.getInt("IDFee"),
                                re.getString("Name")
                        ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public Client getClient(int idClient) {
        dataSource = DataSourceSingleton.getInstance();

        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_CLIENT);) {
            stmt.setInt(1, idClient);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Client(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            new City(
                                    rs.getInt(4),
                                    rs.getString(6),
                                    rs.getString(5)
                            ),
                            rs.getString(7),
                            rs.getString(8),
                            rs.getString(9),
                            new Fee(
                                    rs.getInt(10),
                                    rs.getString(11)
                            ),
                            rs.getFloat(12)
                    );
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Client> getClients() {
        List<Client> list = new ArrayList<>();

        dataSource = DataSourceSingleton.getInstance();
        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_CLIENTS);
                ResultSet rs = stmt.executeQuery();) {
            while (rs.next()) {
//                //Client c= new Client();
//                int idC=         rs.getInt(1);
//                String name=    rs.getString(2);
//                String address=rs.getString(3);
//                City city= new City();
//                int cityId=rs.getInt(4);
//                String cityName=rs.getString(5);
//                String post=    rs.getString(6);
//                String oib= rs.getString(7);
//                String m=       rs.getString(8);
//                String l=       rs.getString(9);
//                int fId=        rs.getInt(10);
//                String namef=            rs.getString(11);

                list.add(new Client(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        new City(
                                rs.getInt(4),
                                rs.getString(6),
                                rs.getString(5)
                        ),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        new Fee(
                                rs.getInt(10),
                                rs.getString(11)
                        ),
                        rs.getFloat(12)
                ));

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();

        }

        return list;
    }

    @Override
    public User getUser(int idUser) {
        dataSource = DataSourceSingleton.getInstance();

        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_USER);) {
            stmt.setInt(1, idUser);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("IDUser"),
                            rs.getString("Name"),
                            rs.getString("Surname"),
                            rs.getString("Username"),
                            rs.getString("Password"),
                            rs.getString("OperatorCode")
                    );
                }
            }

        } catch (Exception e) {

        }
        return null;
    }

    @Override
    public List<User> getUsers() {
        List<User> list = new ArrayList<>();

        dataSource = DataSourceSingleton.getInstance();
        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_USERS);
                ResultSet rs = stmt.executeQuery();) {
            while (rs.next()) {
                list.add(
                        new User(
                                rs.getInt("IDUser"),
                                rs.getString("Name"),
                                rs.getString("Surname"),
                                rs.getString("Username"),
                                rs.getString("Password"),
                                rs.getString("OperatorCode")
                        ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }

    @Override
    public Task getTask(int idTask) {
        dataSource = DataSourceSingleton.getInstance();

        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_TASK);) {
            stmt.setInt(1, idTask);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Task(
                            rs.getInt("IDTask"),
                            rs.getString("Name"),
                            new Category(
                                    rs.getInt("IDCategory"),
                                    rs.getString(5))
                    );
                }
            }

        } catch (Exception e) {

        }
        return null;

    }

    @Override
    public List<Task> getTasks() {
        List<Task> list = new ArrayList<>();

        dataSource = DataSourceSingleton.getInstance();
        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_TASKS);
                ResultSet rs = stmt.executeQuery();) {
            while (rs.next()) {
                list.add(
                        new Task(
                                rs.getInt("IDTask"),
                                rs.getString("Name"),
                                new Category(
                                        rs.getInt("IDCategory"),
                                        rs.getString(5))
                        //                                new User(
                        //                                rs.getInt("IDUser"),
                        //                             rs.getString("Name"),
                        //                            rs.getString("Surname"),
                        //                        rs.getString("Username"),
                        //                            rs.getString("Password"),
                        //                            rs.getString("OperatorCode"))
                        ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }

    @Override
    public Category getCategory(int idCategory) {
        dataSource = DataSourceSingleton.getInstance();

        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_CATEGORY);) {
            stmt.setInt(1, idCategory);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Category(
                            rs.getInt("IDCategory"),
                            rs.getString("Name")
                    );
                }
            }

        } catch (Exception e) {

        }
        return null;

    }

    @Override
    public List<Category> getCategories() {
        List<Category> list = new ArrayList<>();

        dataSource = DataSourceSingleton.getInstance();
        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_CATEGORIES);
                ResultSet rs = stmt.executeQuery();) {
            while (rs.next()) {
                list.add(new Category(
                        rs.getInt("IDCategory"),
                        rs.getString("Name")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }

    @Override
    public City getCity(int idCity) {
        dataSource = DataSourceSingleton.getInstance();

        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_CITY);) {
            stmt.setInt(1, idCity);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new City(
                            rs.getInt("IDCity"),
                            rs.getString("PostalCode"),
                            rs.getString("Name")
                    );
                }
            }

        } catch (Exception e) {

        }
        return null;

    }

    @Override
    public List<City> getCities() {
        List<City> list = new ArrayList<>();

        dataSource = DataSourceSingleton.getInstance();
        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_CITIES);
                ResultSet rs = stmt.executeQuery();) {
            while (rs.next()) {
                list.add(
                        new City(
                                rs.getInt("IDCity"),
                                rs.getString("PostalCode"),
                                rs.getString("Name")
                        ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }

    @Override
    public Invoice getInvoice(int idInvoice) {
        dataSource = DataSourceSingleton.getInstance();

        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_INVOICE);) {

            stmt.setInt(1, idInvoice);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Invoice(
                            rs.getInt(1),
                            new User(
                                    rs.getInt("IDUser"),
                                    rs.getString(13),
                                    rs.getString("Surname"),
                                    rs.getString("Username"),
                                    rs.getString("Password"),
                                    rs.getString("OperatorCode")),
                            rs.getString(2),
                            rs.getDate(4),
                            new Client(
                                    rs.getInt(5),
                                    rs.getString(16),
                                    rs.getString(17),
                                    new City(
                                            rs.getInt(18),
                                            rs.getString(25),
                                            rs.getString(26)
                                    ),
                                    rs.getString(19),
                                    rs.getString(20),
                                    rs.getString(21),
                                    new Fee(
                                            rs.getInt(23),
                                            rs.getString(28)
                                    ),
                                    rs.getFloat(22)
                            ),
                            rs.getDouble(6),
                            rs.getDate(7),
                            rs.getDate(8)
                    );
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Invoice> getInvoices() {
        List<Invoice> list = new ArrayList<>();

        dataSource = DataSourceSingleton.getInstance();
        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_INVOICES);
                ResultSet rs = stmt.executeQuery();) {
            while (rs.next()) {
                list.add(
                        new Invoice(
                                rs.getInt(1),
                                new User(
                                        rs.getInt("IDUser"),
                                        rs.getString(13),
                                        rs.getString("Surname"),
                                        rs.getString("Username"),
                                        rs.getString("Password"),
                                        rs.getString("OperatorCode")),
                                rs.getString(2),
                                rs.getDate(4),
                                new Client(
                                        rs.getInt(5),
                                        rs.getString(16),
                                        rs.getString(17),
                                        new City(
                                                rs.getInt(18),
                                                rs.getString(25),
                                                rs.getString(26)
                                        ),
                                        rs.getString(19),
                                        rs.getString(20),
                                        rs.getString(21),
                                        new Fee(
                                                rs.getInt(23),
                                                rs.getString(28)
                                        ),
                                        rs.getDouble(22)
                                ),
                                rs.getDouble(6),
                                rs.getDate(7),
                                rs.getDate(8)
                        ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }

    @Override
    public TimeLog getTimeLog(int idTimeLog) {
        dataSource = DataSourceSingleton.getInstance();

        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_TIMELOG);) {
            stmt.setInt(1, idTimeLog);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new TimeLog(
                            rs.getInt(1),
                            new Task(
                                    rs.getInt(2),
                                    rs.getString(9),
                                    new Category(
                                            rs.getInt(10),
                                            rs.getString(12))),
                            rs.getDate(3),
                            rs.getDate(4),
                            rs.getInt(5),
                            new User(
                                    rs.getInt(6),
                                    rs.getString(17),
                                    rs.getString(18),
                                    rs.getString(14),
                                    rs.getString(15),
                                    rs.getString(16)),
                            new Client(
                                    rs.getInt(19),
                                    rs.getString(20),
                                    rs.getString(21),
                                    new City(
                                            rs.getInt(22),
                                            rs.getString(29),
                                            rs.getString(28)
                                    ),
                                    rs.getString(23),
                                    rs.getString(24),
                                    rs.getString(25),
                                    new Fee(
                                            rs.getInt(26),
                                            rs.getString(30)
                                    ),
                                    rs.getFloat(27)
                            )
                    );
                }
            }

        } catch (Exception e) {

        }
        return null;
    }

    @Override
    public List<TimeLog> getTimeLogs() {
        List<TimeLog> list = new ArrayList<>();

        dataSource = DataSourceSingleton.getInstance();
        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_TIMELOGS);
                ResultSet rs = stmt.executeQuery();) {
            while (rs.next()) {
                list.add(
                        new TimeLog(
                                rs.getInt(1),
                                new Task(
                                        rs.getInt(2),
                                        rs.getString(9),
                                        new Category(
                                                rs.getInt(10),
                                                rs.getString(12))),
                                rs.getDate(3),
                                rs.getDate(4),
                                rs.getInt(5),
                                new User(
                                        rs.getInt(6),
                                        rs.getString(17),
                                        rs.getString(18),
                                        rs.getString(14),
                                        rs.getString(15),
                                        rs.getString(16)),
                                new Client(
                                        rs.getInt(19),
                                        rs.getString(20),
                                        rs.getString(21),
                                        new City(
                                                rs.getInt(22),
                                                rs.getString(29),
                                                rs.getString(28)
                                        ),
                                        rs.getString(23),
                                        rs.getString(24),
                                        rs.getString(25),
                                        new Fee(
                                                rs.getInt(26),
                                                rs.getString(30)
                                        ),
                                        rs.getFloat(27)
                                )
                        ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }

    @Override
    public Item getItem(int idItem) {
// dataSource = DataSourceSingleton.getInstance();
//                
//        try (
//                Connection con = dataSource.getConnection();
//                CallableStatement stmt = con.prepareCall(GET_FEE);
//                               
//                ){
//            stmt.setInt(1, idFee); //prvi parametar pod upitnikom!!!!!!!
//            try (ResultSet rs = stmt.executeQuery()){
//                if (rs.next()) {
//                    return new Fee(
//                        rs.getInt(1),
//                        rs.getString(2)                       
//                    );
//                }
//            }
//                
//        } catch (Exception e) {
//            
//        }
        return null;
    }

    @Override
    public List<Item> getItems() {
// List<Fee> list = new ArrayList<Fee>();
//        
//        dataSource = DataSourceSingleton.getInstance();
//        try (
//                Connection con = dataSource.getConnection();
//                CallableStatement stmt = con.prepareCall(GET_FEES);
//                ResultSet rs = stmt.executeQuery();
//                ) {
//            while(rs.next()){
//                list.add(
//                        new Fee(
//                                 rs.getInt("IDFee"),
//                        rs.getString("Name")
//                        
////                        rs.getInt("IDDoctor"),
////                        rs.getString("Name"),
////                        rs.getString("Surname"),
////                        rs.getString("Title")
//                        ));                
//            }                  
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        
//        return list;
        return null;
    }

    @Override
    public List<Record> getRecords() {
// List<Fee> list = new ArrayList<Fee>();
//        
//        dataSource = DataSourceSingleton.getInstance();
//        try (
//                Connection con = dataSource.getConnection();
//                CallableStatement stmt = con.prepareCall(GET_FEES);
//                ResultSet rs = stmt.executeQuery();
//                ) {
//            while(rs.next()){
//                list.add(
//                        new Fee(
//                                 rs.getInt("IDFee"),
//                        rs.getString("Name")
//                        
////                        rs.getInt("IDDoctor"),
////                        rs.getString("Name"),
////                        rs.getString("Surname"),
////                        rs.getString("Title")
//                        ));                
//            }                  
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        
        // return list;
        return null;
    }

    @Override
    public User getCredentials(String username, String password) {
        dataSource = DataSourceSingleton.getInstance();

        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_CREDENTIALS);) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt(1),
                            rs.getString(5),
                            rs.getString(6),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4)
                    );
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SqlRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public void editCategory(Category dummy, int idCategory) {
        DataSource ds = DataSourceSingleton.getInstance();
        try (
                Connection con = ds.getConnection();
                CallableStatement stmt = con.prepareCall(EDIT_CATEGORY);) {
            stmt.setString(2, dummy.getName());

            stmt.setInt(1, idCategory);

            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void addClient(Client client) {
        DataSource ds = DataSourceSingleton.getInstance();
        try (
                Connection con = ds.getConnection();
                CallableStatement stmt = con.prepareCall(ADD_CLIENT);) {
            stmt.setString(1, client.getName());
            stmt.setString(2, client.getAddress());
            stmt.setInt(3, client.getCity().getIdCity());
            stmt.setString(4, client.getOib());
            stmt.setString(5, client.getMbs());
            stmt.setString(6, client.getMb());
            stmt.setInt(7, client.getFee().getIdFee());
            stmt.setDouble(8, client.getPrice());

            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void editClient(int idClient, Client dummy) {
        DataSource ds = DataSourceSingleton.getInstance();
        try (
                Connection con = ds.getConnection();
                CallableStatement stmt = con.prepareCall(EDIT_CLIENT);) {
            stmt.setInt(1, idClient);
            stmt.setString(2, dummy.getAddress());
            stmt.setInt(3, dummy.getCity().getIdCity());
            stmt.setString(4, dummy.getOib());
            stmt.setString(5, dummy.getMbs());
            stmt.setString(6, dummy.getMb());
            stmt.setInt(7, dummy.getFee().getIdFee());
            stmt.setDouble(8, dummy.getPrice());

            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void addUser(User user) {
        DataSource ds = DataSourceSingleton.getInstance();
        try (
                Connection con = ds.getConnection();
                CallableStatement stmt = con.prepareCall(ADD_USER);) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getOperatorCode());
            stmt.setString(4, user.getName());
            stmt.setString(5, user.getSurname());

            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void editUser(int idUser, User user) {
        DataSource ds = DataSourceSingleton.getInstance();
        try (
                Connection con = ds.getConnection();
                CallableStatement stmt = con.prepareCall(EDIT_USER);) {
            stmt.setInt(1, idUser);
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getOperatorCode());
            stmt.setString(5, user.getName());
            stmt.setString(6, user.getSurname());

            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void addTask(Task task) {
        DataSource ds = DataSourceSingleton.getInstance();
        try (
                Connection con = ds.getConnection();
                CallableStatement stmt = con.prepareCall(ADD_TASK);) {
            stmt.setInt(1, task.getCategory().getIdCategory());
            stmt.setString(2, task.getName());

            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    @Override
    public void editTask(int idTask, Task task) {

        DataSource ds = DataSourceSingleton.getInstance();
        try (
                Connection con = ds.getConnection();
                CallableStatement stmt = con.prepareCall(EDIT_TASK);) {
            stmt.setInt(1, task.getCategory().getIdCategory());
            stmt.setString(2, task.getName());
            stmt.setInt(3, idTask);

            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

    }

    @Override
    public List<Task> getTasksForCategory(int idCategory) {
        List<Task> list = new ArrayList<>();

        dataSource = DataSourceSingleton.getInstance();

        try (
                Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_TASKS_FOR_CATEGORY);) {
            stmt.setInt(1, idCategory);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(
                            new Task(
                                    rs.getInt("IDTask"),
                                    rs.getString("Name"),
                                    new Category(
                                            rs.getInt("IDCategory"),
                                            rs.getString(5)))
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void addTimeLog(TimeLog timeLog) {
        DataSource ds = DataSourceSingleton.getInstance();
        try (
                Connection con = ds.getConnection();
                CallableStatement stmt = con.prepareCall(ADD_TIMELOG);) {
            stmt.setInt(1, timeLog.getTask().getIdTask());
            stmt.setInt(2, timeLog.getUser().getIdUser());
            stmt.setDate(3, timeLog.getDate());
            stmt.setInt(4, timeLog.getTimeSpentOnTask());
            stmt.setInt(5, timeLog.getClient().getIdClient());

            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    @Override
    public void editTimeLog(int idTimeLog, TimeLog timeLog) {
        DataSource ds = DataSourceSingleton.getInstance();
        try (
                Connection con = ds.getConnection();
                CallableStatement stmt = con.prepareCall(EDIT_TIMELOG);) {
            stmt.setInt(1, timeLog.getTask().getIdTask());
            stmt.setInt(2, timeLog.getUser().getIdUser());
            stmt.setDate(3, timeLog.getDate());
            stmt.setInt(4, timeLog.getTimeSpentOnTask());
            stmt.setInt(5, idTimeLog);
            stmt.setInt(6, timeLog.getClient().getIdClient());

            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

//    @Override
//    public void addInvoice(Invoice invoice) {
//      DataSource ds = DataSourceSingleton.getInstance();
//        try (
//                Connection con = ds.getConnection();
//                CallableStatement stmt = con.prepareCall(ADD_INVOICE);                      
//                ){
//           // stmt.setString(1, invoice.getInvoiceNo());
//           stmt.setInt(1 , invoice.getCreatedByUser().getIdUser());
//           stmt.setInt(2, invoice.getClient().getIdClient());
//                       stmt.setDate(3 , invoice.getInvoiceDateStart());
//                       stmt.setDate(4 , invoice.getInvoiceDateEnd());
//                                              
//                                             stmt.executeUpdate();
//
//            
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            System.out.println("hello");
//            
//        }
//    }
    @Override
    public void addInvoiceSum(Invoice invoice) {
        DataSource ds = DataSourceSingleton.getInstance();
        try (
                Connection con = ds.getConnection();
                CallableStatement stmt = con.prepareCall(ADD_INVOICE_SUM);) {
            stmt.setString(1, invoice.getInvoiceNo());
            stmt.setDouble(2, invoice.getSum());

            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    @Override
    public void addDefaultItem(Item item) {

    }

    @Override
    public void addOtherItem(Item item) {

    }

    @Override
    public int addInvoice2(Invoice invoice) {
        DataSource ds = DataSourceSingleton.getInstance();
        try (
                Connection con = ds.getConnection();
                CallableStatement stmt = con.prepareCall(ADD_INVOICE2);) {

            stmt.registerOutParameter(1, java.sql.Types.INTEGER);
            // stmt.setString(1, invoice.getInvoiceNo());
            stmt.setInt(2, invoice.getCreatedByUser().getIdUser());
            stmt.setInt(3, invoice.getClient().getIdClient());
            stmt.setDate(4, invoice.getInvoiceDateStart());
            stmt.setDate(5, invoice.getInvoiceDateEnd());

            stmt.executeUpdate();

            return stmt.getInt(1);

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return -1;
    }

    @Override
    public List<Specification> generateSpecification(int clientId, Date dateStart, Date dateEnd) {
        List<Specification> list = new ArrayList<>();

        dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GENERATE_SPECIFICATION);) {
            stmt.setInt(1, clientId);
            stmt.setDate(2, dateStart);
            stmt.setDate(3, dateEnd);
            try (
                    ResultSet re = stmt.executeQuery();) {

                while (re.next()) {
                    list.add(new Specification(
                            re.getString(4),
                            re.getInt(5),
                            re.getInt(1),
                            re.getDate(2),
                            re.getDate(3)
                    ));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
        }

        return list;
    }
}
