/**
 * Gruppe bagrækken
 * Klavs, Alexander og Jesper
 **/
package DAL.db;

import BE.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO_DB {

    private DatabaseConnector databaseConnector;

    public UserDAO_DB(){
        databaseConnector = new DatabaseConnector();
    }

    public List<User> getAllUsers() throws Exception {

        ArrayList<User> allUsers = new ArrayList<>();


        try (Connection conn = databaseConnector.getConnection();
             Statement stmt = conn.createStatement()) {

            String sql = "SELECT * FROM dbo.Users;";


            ResultSet rs = stmt.executeQuery(sql);

            // Loop through rows from the database result set
            while (rs.next()) {

                //Map DB row to User
                int Id = rs.getInt("Id");
                String LoginName = rs.getString("LoginName");
                String FirstName = rs.getString("FirstName");
                String LastName = rs.getString("LastName");
                String EMail = rs.getString("E-mail");
                String Hash = rs.getString("Password hash");
                int Role = rs.getInt("isSpecial");

                User users = new User(Id, LoginName, FirstName, LastName, EMail, Hash, Role);

                allUsers.add(users);
            }

            return allUsers;

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Could not get Userlist from database", ex);
        }

    }
    public void deleteUser(User user) throws Exception {

        try(Connection connection = databaseConnector.getConnection()){

            String sql = "DELETE FROM dbo.Users WHERE id = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, user.getId());

            statement.executeUpdate();
        }
        catch (SQLException exc){
            exc.printStackTrace();
            throw new Exception("Could not delete User", exc);
        }

    }

}
