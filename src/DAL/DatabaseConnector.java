/**
 * Gruppe bagrækken
 * Klavs, Alexander og Jesper
 */
package DAL;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;

/**
 * The DatabaseConnector class is responsible for establishing and managing connections to the database.
 */
public class DatabaseConnector {

    private static DatabaseConnector instance;
    private final SQLServerDataSource dataSource;

    /**
     * Constructs a new instance of the DatabaseConnector class with the provided database login information.
     *
     * @param dbLogin The login information for the database.
     */
    private DatabaseConnector(DBLogin dbLogin) {
        dataSource = new SQLServerDataSource();
        dataSource.setServerName(dbLogin.getServer());
        dataSource.setDatabaseName(dbLogin.getDBName());
        dataSource.setPortNumber(dbLogin.getPort());
        dataSource.setUser(dbLogin.getUser());
        dataSource.setPassword(dbLogin.getPassword());
        dataSource.setTrustServerCertificate(true);
    }

    /**
     * Initializes the DatabaseConnector with the provided database login information.
     * This method should be called before using the DatabaseConnector.
     *
     * @param dbLogin The login information for the database.
     */
    public static void init(DBLogin dbLogin) {
        if (instance != null) return;
        instance = new DatabaseConnector(dbLogin);
    }

    /**
     * Retrieves the singleton instance of the DatabaseConnector.
     *
     * @return The DatabaseConnector instance.
     */
    public static DatabaseConnector getInstance() {
        return instance;
    }

    /**
     * Retrieves a connection to the database.
     *
     * @return A Connection object representing the database connection.
     * @throws SQLServerException If a database access error occurs.
     */
    public Connection getConnection() throws SQLServerException {
        return dataSource.getConnection();
    }
}
