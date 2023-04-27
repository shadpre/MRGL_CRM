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

    private String Hash;
    private int Role;

    public User(int id, String loginName, String firstName, String lastName, String email, String hash, int role) {
        this.Id = id;
        LoginName = loginName;
        FirstName = firstName;
        LastName = lastName;
        EMail = email;
        Hash = hash;
        Role = role;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getLoginName() {
        return LoginName;
    }

    public void setLoginName(String loginName) {
        LoginName = loginName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEMail() {
        return EMail;
    }

    public void setEMail(String EMail) {
        this.EMail = EMail;
    }

    public String getHash() {
        return Hash;
    }

    public void setHash(String hash) {
        Hash = hash;
    }

    public int getRole() {
        return Role;
    }

    public void setRole(int role) {
        Role = role;
    }
}
