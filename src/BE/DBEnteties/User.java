/**
 * Gruppe bagrækken
 * Klavs, Alexander og Jesper
 **/
package BE.DBEnteties;

import BE.DBEnteties.Interfaces.IUser;


public class User implements IUser {

    private int Id;
    private final String LoginName;
    private final String FirstName;
    private final String LastName;
    private final String EMail;
    private final int Role;

    public User(int id, String loginName, String firstName, String lastName, String email, int role) {
        Id = id;
        LoginName = loginName;
        FirstName = firstName;
        LastName = lastName;
        EMail = email;
        Role = role;
    }

    @Override
    public int getId() {
        return Id;
    }

    @Override
    public void setId(int id) {
        this.Id = id;
    }

    @Override
    public String getLoginName() {
        return LoginName;
    }

    @Override
    public String getFirstName() {
        return FirstName;
    }

    @Override
    public String getLastName() {
        return LastName;
    }

    @Override
    public String getEMail() {
        return EMail;
    }

    @Override
    public int getRole() {
        return Role;
    }

    @Override
    public String toString() {
        return FirstName + " " + LastName;
    }
}
