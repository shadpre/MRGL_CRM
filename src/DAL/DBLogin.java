package DAL;

/**
 * The DBLogin class represents the login information for the database.
 * It follows the Singleton design pattern, allowing only one instance to exist.
 */
public class DBLogin {

    // Design pattern: Singleton
    private static DBLogin instance;
    private final String Server;
    private final int Port;
    private final String User;
    private final String Password;
    private final String DBName;

    /**
     * Constructs a new instance of the DBLogin class with the provided login information.
     *
     * @param server   The server name or IP address of the database.
     * @param port     The port number for the database connection.
     * @param dbName   The name of the database.
     * @param user     The username for the database login.
     * @param password The password for the database login.
     */
    private DBLogin(String server, int port, String dbName, String user, String password) {
        Server = server;
        Port = port;
        User = user;
        Password = password;
        DBName = dbName;
    }

    /**
     * Initializes the DBLogin instance with the provided login information.
     * This method should be called before using the DBLogin instance.
     *
     * @param server   The server name or IP address of the database.
     * @param port     The port number for the database connection.
     * @param dbName   The name of the database.
     * @param user     The username for the database login.
     * @param password The password for the database login.
     */
    public static void init(String server, int port, String dbName, String user, String password) {
        if (instance != null) return;
        instance = new DBLogin(server, port, dbName, user, password);
    }

    /**
     * Retrieves the singleton instance of the DBLogin.
     *
     * @return The DBLogin instance.
     */
    public static DBLogin getInstance() {
        return instance;
    }

    /**
     * Retrieves the server name or IP address of the database.
     *
     * @return The server name or IP address.
     */
    public String getServer() {
        return Server;
    }

    /**
     * Retrieves the port number for the database connection.
     *
     * @return The port number.
     */
    public int getPort() {
        return Port;
    }

    /**
     * Retrieves the username for the database login.
     *
     * @return The username.
     */
    public String getUser() {
        return User;
    }

    /**
     * Retrieves the password for the database login.
     *
     * @return The password.
     */
    public String getPassword() {
        return Password;
    }

    /**
     * Retrieves the name of the database.
     *
     * @return The database name.
     */
    public String getDBName() {
        return DBName;
    }
}
