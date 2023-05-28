package BLL;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordHashTest {
    @Test
    void chkGoodPassword() {
        System.out.println("Good Password");
        String password = "Abcd1234Q@";
        String hashedPw = PasswordHash.encryptPassword(password);

        boolean result = PasswordHash.chkPassword(password,hashedPw);
        assertTrue(result);
        System.out.println();
    }

    @Test
    void chkBadPassword() {
        System.out.println("Bad Password");
        String password = "Abcd1234Q@";
        String hashedPw = PasswordHash.encryptPassword(password);

        boolean result = PasswordHash.chkPassword("badpassword",hashedPw);
        assertFalse(result);
        System.out.println();
    }
}