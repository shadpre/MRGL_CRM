package DAL;

public class DBLogin {
    //Designpattern: Singleton
    private static DBLogin instance;
    private final String Server;
    private final int Port;
    private final String User;
    private final String Password;
    private final String DBName;

    private DBLogin(String server, int port, String dbName, String user, String password) {
        Server = server;
        Port = port;
        User = user;
        Password = password;
        DBName = dbName;
    }

    public static void init(String server, int port, String dbName, String user, String password) {
        if (instance != null) return;
        instance = new DBLogin(server, port, dbName, user, password);
    }

    public static DBLogin getInstance() {
        return instance;
    }

    public String getServer() {
        return Server;
    }

    public int getPort() {
        return Port;
    }

    public String getUser() {
        return User;
    }

    public String getPassword() {
        return Password;
    }

    public String getDBName() {
        return DBName;
    }
}
