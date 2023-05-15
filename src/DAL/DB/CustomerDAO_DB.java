package DAL.DB;

import BE.DBEnteties.Customer;
import BE.Exptions.NotFoundExeptions.CustomerNotFoundExeption;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAO_DB {
    public static Customer createCustomer(Customer customer) throws SQLException, CustomerNotFoundExeption {
        int ID;
        try (Connection conn = DatabaseConnector.getInstance().getConnection()){

            String query = "INSERT INTO CUSTOMERS (Name, Address1, Address2, Address3, Zipcode, City, Country, Phone, Category,TaxNo) VALUES(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, customer.getName());
            statement.setString(2, customer.getAddress1());
            statement.setString(3, customer.getAddress2());
            statement.setString(4, customer.getAddress3());
            statement.setString(5, customer.getZipcode());
            statement.setString(6, customer.getCity());
            statement.setString(7, customer.getCountry());
            statement.setString(8, customer.getPhone());
            statement.setString(9, customer.getCategory());
            statement.setString(10, customer.getTaxNo());

            var rs = statement.executeQuery();
            if (rs.next()){
                ID = rs.getInt(1);
            }
            else throw new SQLDataException("User not saved");
        }
        return getCustomerByID(ID);
    }

    public static Customer getCustomerByID(int ID) throws SQLException, CustomerNotFoundExeption{
        try (Connection conn = DatabaseConnector.getInstance().getConnection()){

            String query = "SELECT Name, Address1, Address2, Address3, Zipcode, City, Country, Phone, Category, TaxNo FROM Customers WHERE ID = ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, ID);

            var rs = statement.executeQuery();
            if (rs.next()){
                return new Customer(
                        ID,
                        rs.getString("Name"),
                        rs.getString("Address1"),
                        rs.getString("Address2"),
                        rs.getString("Address3"),
                        rs.getString("Zipcode"),
                        rs.getString("City"),
                        rs.getString("Country"),
                        rs.getString("Phone"),
                        rs.getString("Category"),
                        rs.getString("TaxNo")
                );
            }
            else throw new CustomerNotFoundExeption("Customer Not Found");
        }
    }

    public static ArrayList<Customer> getAllCustomers() throws SQLException, CustomerNotFoundExeption{
        ArrayList<Customer> out = new ArrayList<>();
        try( Connection conn = DatabaseConnector.getInstance().getConnection()){

            String query = "SELECT Id, Name, Address1, Address2, Address3, Zipcode, City, Country, Phone, Category, TaxNo FROM Customers";

            PreparedStatement statement = conn.prepareStatement(query);

            var rs = statement.executeQuery();

            while(rs.next()){
                out.add(new Customer(
                        rs.getInt("Id"),
                        rs.getString("Name"),
                        rs.getString("Address1"),
                        rs.getString("Address2"),
                        rs.getString("Address3"),
                        rs.getString("Zipcode"),
                        rs.getString("City"),
                        rs.getString("Country"),
                        rs.getString("Phone"),
                        rs.getString("Category"),
                        rs.getString("TaxNo")
                ));
            }
            if (out.size() == 0){
                throw new CustomerNotFoundExeption("No Customers Found");
            }
            return out;
        }
    }

    public static Customer updateCustomer(Customer customer) throws CustomerNotFoundExeption, SQLException{
        try (Connection conn = DatabaseConnector.getInstance().getConnection()){
            String query =
                    "UPDATE Customers " +
                    "SET Name = ?, Address1 = ?, Address2 = ?, Address3 = ?, Zipcode = ?, City = ?, Country = ?, Phone = ?, Category = ?, TaxNo = ? " +
                    "WHERE Id = ?";

            PreparedStatement statement = conn.prepareStatement(query);

            statement.setString(1, customer.getName());
            statement.setString(2, customer.getAddress1());
            statement.setString(3, customer.getAddress2());
            statement.setString(4, customer.getAddress3());
            statement.setString(5, customer.getZipcode());
            statement.setString(6, customer.getCity());
            statement.setString(7, customer.getCountry());
            statement.setString(8, customer.getPhone());
            statement.setString(9, customer.getCategory());
            statement.setString(10, customer.getTaxNo());
            statement.setInt(11, customer.getId());

            var rs = statement.executeUpdate();
            if(rs == 0) throw new CustomerNotFoundExeption("CustomerNotFound");
        }

        return getCustomerByID(customer.getId());
    }

    public static void anonymizeCustomer(int ID){
        throw new RuntimeException("Not Implemented");
    }

    public static void deleteCustomer(int ID){
        throw new RuntimeException("Not implemented");
    }
}
