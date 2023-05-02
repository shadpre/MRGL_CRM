/**
 * Gruppe bagrækken
 * Klavs, Alexander og Jesper
 **/
package BE;

public class User {

    private int Id;
    private String LoginName;
    private String FirstName;
    private String LastName;
    private String EMail;
    private int Role;

    public User(int id, String loginName, String firstName, String lastName, String email, int role) {
        Id = id;
        LoginName = loginName;
        FirstName = firstName;
        LastName = lastName;
        EMail = email;
        Role = role;
    }

    public int getId() {
        return Id;
    }

    public String getLoginName() {
        return LoginName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getEMail() {
        return EMail;
    }

    public int getRole() {
        return Role;
    }

    @Override
    public String toString() {
        return FirstName + " " + LastName;
    }
}
