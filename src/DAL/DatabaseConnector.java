/**
 * Gruppe bagrækken
 * Klavs, Alexander og Jesper
 **/

package DAL;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;

public class DatabaseConnector {

    private static DatabaseConnector instance;
    private final SQLServerDataSource dataSource;

    private DatabaseConnector(DBLogin dbLogin) {
        dataSource = new SQLServerDataSource();
        dataSource.setServerName(dbLogin.getServer());
        dataSource.setDatabaseName(dbLogin.getDBName());
        dataSource.setPortNumber(dbLogin.getPort());
        dataSource.setUser(dbLogin.getUser());
        dataSource.setPassword(dbLogin.getPassword());
        dataSource.setTrustServerCertificate(true);
    }

    public static void init(DBLogin dbLogin) {
        if (instance != null) return;
        instance = new DatabaseConnector(dbLogin);
    }

    public static DatabaseConnector getInstance() {
        return instance;
    }

    public Connection getConnection() throws SQLServerException {
        return dataSource.getConnection();
    }
}

