/**
 * Gruppe bagrækken
 * Klavs, Alexander og Jesper
 **/
package BLL.Managers;

import BE.Exptions.UserValidationExeption;
import BE.DBEnteties.User;
import BLL.PasswordHash;
import DAL.DB.UserDAO_DB;

import java.util.ArrayList;

public class UserManager {

    public static User getUser(String LoginName, String Password) throws Exception {
        //Hardcoded Admin
        if(LoginName == "SYS" && Password == "King_Mrgl"){
            return new User(0,"SYS","Admin", "Bruger", "", 0);
        }

        if (validateUser(LoginName, Password)){
            return UserDAO_DB.getUser(LoginName);
        }
        else {
            throw new UserValidationExeption("Invalid Username or Password");
        }
    }

    private static boolean validateUser(String LoginName, String Password) throws Exception {
        String hash = UserDAO_DB.getUserHash(LoginName);
        return PasswordHash.chkPassword(Password, hash);
    }

    public static User createUser(User user, String Password, String PasswordRetype) throws Exception{
        String hash = PasswordHash.encryptPassword(Password);
        if (UserDAO_DB.loginNameAvailible(user.getLoginName())){
            return UserDAO_DB.createUser(user, hash);
        }
        else throw new RuntimeException("User not created");
    }

    public static void resetPassword(User user, String Password) throws Exception{
        String hash = PasswordHash.encryptPassword(Password);
        UserDAO_DB.resetPassword(user.getId(), hash);
    }

    public static ArrayList<User> getAllUsers() throws Exception{
        return UserDAO_DB.getAllUsers();
    }



}
