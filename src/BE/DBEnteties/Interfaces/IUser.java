package BE.DBEnteties.Interfaces;

public interface IUser {
    int getId();

    void setId(int id);

    String getLoginName();

    String getFirstName();

    String getLastName();

    String getEMail();

    int getRole();

    @Override
    String toString();
}
