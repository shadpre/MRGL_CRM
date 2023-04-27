/**
 * Gruppe bagrækken
 * Klavs, Alexander og Jesper
 **/
package BLL;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import BE.User;
import DAL.db.UserDAO_DB;

public class UserManager {
    private UserDAO_DB userDAO_db;

    public UserManager() throws SQLException, IOException {
        userDAO_db = new UserDAO_DB();

    }
    public List<User> getAllUsers() throws Exception {
        return userDAO_db.getAllUsers();
    }

    public void deleteUser(User deletedUser) throws Exception{
        userDAO_db.deleteUser(deletedUser);
    }
}
