package com.ivanakasalo.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import javax.sql.DataSource;

/**
 *
 * @author office10
 */
public class DataSourceSingleton {

    private static final String SERVER_NAME = "localhost";
    private static final String DB_NAME = "BillingSystem";
    private static final String USER = "sa";
    private static final String PASSWORD = "SQL";

    // eager approach this <- Lazy approach
    //maven skidanje preko interneta
    private DataSourceSingleton() {
    }

    public static DataSource getInstance() {
        if (instance == null) {
            instance = createInstance();
        }
        return instance;

    }

    private static DataSource instance;

    private static DataSource createInstance() {
        try {
            SQLServerDataSource dataSource = new SQLServerDataSource();
            dataSource.setServerName(SERVER_NAME);
            dataSource.setDatabaseName(DB_NAME);
            dataSource.setUser(USER);
            dataSource.setPassword(PASSWORD);

            return dataSource;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;

    }
}
