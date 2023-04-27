/**
 * Gruppe bagrækken
 * Klavs, Alexander og Jesper
 **/

package DAL.db;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnector {

        private SQLServerDataSource dataSource;

        public DatabaseConnector() {

            dataSource = new SQLServerDataSource();
            dataSource.setServerName("10.176.111.34");
            dataSource.setDatabaseName("MrglCRM");
            dataSource.setUser("CSe2022A_e_12");
            dataSource.setPassword("CSe2022AE12#");
            dataSource.setTrustServerCertificate(true);
        }
        public Connection getConnection() throws SQLServerException
        {
            return dataSource.getConnection();
        }

//Test if there is an open connection.

        public static void main(String[] args) throws SQLException
        {
            DatabaseConnector databaseConnector = new DatabaseConnector();

            try (Connection connection = databaseConnector.getConnection())
            {
                System.out.println("Is it open? " + !connection.isClosed());
            }
        }
}

