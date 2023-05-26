package BE.DBEnteties.Interfaces;

import java.time.LocalDateTime;

public interface ICustomerTask {
    int getId();

    LocalDateTime getDate();

    String GetDateString();

    String getDescription();

    String getRemarks();

    int getStatus();

    int getCustomerID();
}
