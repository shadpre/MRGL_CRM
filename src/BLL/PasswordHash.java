package BLL;

import org.mindrot.jbcrypt.BCrypt;
/**
 * Utility class for password hashing and verification using BCrypt.
 */
public class PasswordHash {

    /**
     * Encrypts a password using BCrypt.
     *
     * @param password The password to encrypt.
     * @return The encrypted password.
     */
    public static String encryptPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     * Verifies if a password matches a BCrypt hash.
     *
     * @param password The password to verify.
     * @param hash     The BCrypt hash to compare against.
     * @return True if the password matches the hash, false otherwise.
     */
    public static boolean chkPassword(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }
}