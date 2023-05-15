package BE.DBEnteties;

import java.time.LocalDateTime;

public class CustomerTask {
    private int Id;
    private LocalDateTime Date;
    private String Description;
    private String Remarks;
    private int Status;
    private int CustomerID;

    public CustomerTask(int id, LocalDateTime date, String description, String remarks, int status, int customerID) {
        Id = id;
        Date = date;
        Description = description;
        Remarks = remarks;
        Status = status;
        CustomerID = customerID;
    }

    public int getId() {
        return Id;
    }

    public LocalDateTime getDate() {
        return Date;
    }

    public String GetDateString(){
        int y = Date.getYear();
        int m = Date.getMonthValue();
        int d = Date.getDayOfMonth();
        StringBuilder sb = new StringBuilder();

        sb.append(y + "-");

        if (m<10){
            sb.append(0);
        }
        sb.append(m +"-");

        if (d< 10){
            sb.append(0);
        }
        sb.append(d);

        return sb.toString();
    }

    public String getDescription() {
        return Description;
    }

    public String getRemarks() {
        return Remarks;
    }

    public int getStatus() {
        return Status;
    }

    public int getCustomerID() {
        return CustomerID;
    }
}
