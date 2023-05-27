package BLL;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHash {

    public static String encryptPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean chkPassword(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }
}
