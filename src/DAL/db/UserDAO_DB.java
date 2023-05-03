/**
 * Gruppe bagrækken
 * Klavs, Alexander og Jesper
 **/
package DAL.db;

import BE.Exptions.UserValidationExeption;
import BE.User;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO_DB {

    public static String getUserHash(String loginName) throws Exception {
        try (Connection conn = DatabaseConnector.getInstance().getConnection()){
            String query = "SELECT Hash FROM Users WHERE LoginName = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1,loginName);

            var rs = statement.executeQuery();

            if (rs.next()){
                return rs.getString("Hash");
            }
            else {
                throw new UserValidationExeption("Invalid Username or Password");
            }
        }
    }

    public static User getUser(String LoginName) throws Exception {
        User output = null;
        try(Connection conn = DatabaseConnector.getInstance().getConnection()){
            String query = "SELECT Id, FirstName, LastName, Email, Role FROM Users WHERE LoginName = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1,LoginName);

            var rs = statement.executeQuery();
            if (rs.next()){
                return new User(
                        rs.getInt("Id"),
                        LoginName,
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Email"),
                        rs.getInt("Role")
                );
            }
            else {
                throw new UserValidationExeption("Invalid Username or Password");
            }
        }
    }

    public static User getUser(int Id) throws Exception {
        User output = null;
        try(Connection conn = DatabaseConnector.getInstance().getConnection()){
            String query = "SELECT LoginName, FirstName, LastName, Email, Role FROM Users WHERE Id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1,Id);

            var rs = statement.executeQuery();
            if (rs.next()){
                return new User(
                        Id,
                        rs.getString("LoginName"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Email"),
                        rs.getInt("Role")
                );
            }
            else {
                throw new UserValidationExeption("Invalid Username or Password");
            }
        }
    }

    public static User createUser(User user, String hash) throws  Exception{
        int ID;
        try(Connection conn = DatabaseConnector.getInstance().getConnection()){
            String query = "INSERT INTO Users (LoginName, FirstName, LastName, EMail, Hash, Role) Values (?,?,?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, user.getLoginName());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getEMail());
            statement.setString(5,hash);
            statement.setInt(6, user.getRole());

            var rs = statement.executeQuery();
            if (rs.next()){
                ID = rs.getInt(1);
            }
            else throw new SQLDataException("Something went wrong");
        }
        return getUser(ID);
    }

    public static boolean loginNameAvailible(String LoginName) throws Exception{
        try(Connection conn = DatabaseConnector.getInstance().getConnection()){
            String query = "SELECT COUNT(ID) FROM Users WHERE LoginName = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1,LoginName);

            var rs = statement.executeQuery();
            rs.next();

            if( rs.getInt(1) == 0){
                return true;
            }
            else throw new UserValidationExeption("LoginName not availeble");
        }
    }

    public static void deleteUser(int Id) throws Exception{
        try(Connection conn = DatabaseConnector.getInstance().getConnection()){
            try {
                conn.setAutoCommit(false);
                String query = "DELETE Users where Id = ?";
                PreparedStatement statement = conn.prepareStatement(query);
                statement.setInt(1, Id);

                // Delete relations

                conn.commit();
            }
            catch (Exception ex){
                conn.rollback();
                throw ex;
            }
        }
    }

    public static void resetPassword(int id, String hash) throws Exception{
        try(Connection conn = DatabaseConnector.getInstance().getConnection()){
            String query = "UPDATE Users SET Hash = ? WHERE Id = ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            statement.setString(2, hash);

            if (statement.executeUpdate() != 1){
                throw new RuntimeException("Nothing is updated");
            }
        }
    }

    public static ArrayList<User> getAllUsers() throws Exception{
        ArrayList<User> output = new ArrayList<>();

        try(Connection conn = DatabaseConnector.getInstance().getConnection()){
            String query = "SELECT Id, LoginName, FirstName, LastName, Email, Role FROM Users";
            PreparedStatement statement = conn.prepareStatement(query);

            var rs = statement.executeQuery();
            while (rs.next()){
                output.add(new User(
                        rs.getInt("Id"),
                        rs.getString("LoginName"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Email"),
                        rs.getInt("Role")
                ));
            }
        }
        return output;
    }

}
