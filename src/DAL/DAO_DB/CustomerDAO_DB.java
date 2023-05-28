package DAL.DAO_DB;

import BE.DBEnteties.Customer;
import BE.DBEnteties.Interfaces.ICustomer;
import BE.Exptions.NotFoundExeptions.CustomerNotFoundExeption;
import DAL.DatabaseConnector;
import DAL.Iterfaces.ICustomerDAO;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAO_DB implements ICustomerDAO {
    public ICustomer createCustomer(ICustomer customer) throws SQLException, CustomerNotFoundExeption {
        int ID;
        try (Connection conn = DatabaseConnector.getInstance().getConnection()) {

            String query = "INSERT INTO CUSTOMERS (Name, Address1, Address2, Address3, Zipcode, City, Country, Phone, Category, TaxNo) VALUES(?,?,?,?,?,?,?,?,?,?)";
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
            if (rs.next()) {
                ID = rs.getInt(1);
            } else throw new SQLDataException("User not saved");
        }
        return getCustomerByID(ID);
    }

    public ICustomer getCustomerByID(int ID) throws SQLException, CustomerNotFoundExeption {
        try (Connection conn = DatabaseConnector.getInstance().getConnection()) {

            String query = "SELECT Name, Address1, Address2, Address3, Zipcode, City, Country, Phone, Category, TaxNo FROM Customers WHERE ID = ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, ID);

            var rs = statement.executeQuery();
            if (rs.next()) {
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
            } else throw new CustomerNotFoundExeption("Customer Not Found");
        }
    }

    public ArrayList<ICustomer> getAllCustomers() throws SQLException, CustomerNotFoundExeption {
        ArrayList<ICustomer> out = new ArrayList<>();
        try (Connection conn = DatabaseConnector.getInstance().getConnection()) {

            String query = "SELECT Id, Name, Address1, Address2, Address3, Zipcode, City, Country, Phone, Category, TaxNo FROM Customers";

            PreparedStatement statement = conn.prepareStatement(query);

            var rs = statement.executeQuery();

            while (rs.next()) {
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
            if (out.size() == 0) {
                throw new CustomerNotFoundExeption("No Customers Found");
            }
            return out;
        }
    }

    public ICustomer updateCustomer(ICustomer customer) throws CustomerNotFoundExeption, SQLException {
        try (Connection conn = DatabaseConnector.getInstance().getConnection()) {
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
            if (rs == 0) throw new CustomerNotFoundExeption("CustomerNotFound");
        }
        return getCustomerByID(customer.getId());
    }

    public void anonymizeCustomer(int id) throws SQLException, CustomerNotFoundExeption {
        throw new RuntimeException("Not Implemented");
    }

    public void deleteCustomer(int id) throws SQLException, CustomerNotFoundExeption {
        Connection conn = DatabaseConnector.getInstance().getConnection();
        String query = "";
        PreparedStatement statement;
        try {
            conn.setAutoCommit(false);
            ArrayList<Integer> customerTaskIds = new ArrayList<>();
            ArrayList<Integer> installationsIds = new ArrayList<>();

            //Find CustomerTaskId's related to Customer
            query = "SELECT Id FROM CustomerTasks WHERE CustomerId = ?";
            statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet customerTaskIdsResults = statement.executeQuery();
            while (customerTaskIdsResults.next()) {
                customerTaskIds.add(customerTaskIdsResults.getInt("Id"));
            }

            //Get installations
            if (customerTaskIds.size() > 0) {
                query = "SELECT Id FROM Installations WHERE CustomerTaskId = ?";
                statement = conn.prepareStatement(query);
                for (int cutomerTaskId : customerTaskIds) {
                    statement.setInt(1, cutomerTaskId);
                    ResultSet resultSet = statement.executeQuery();
                    while (resultSet.next()) {
                        installationsIds.add(resultSet.getInt("Id"));
                    }
                }
            }

            //Start delete
            if (installationsIds.size() > 0) {
                for (int installationId : installationsIds) {
                    query = "DELETE Images WHERE InstallationID = ?";
                    statement = conn.prepareStatement(query);
                    statement.setInt(1, installationId);
                    statement.executeUpdate();

                    query = "DELETE Devices WHERE InstallationID = ?";
                    statement = conn.prepareStatement(query);
                    statement.setInt(1, installationId);
                    statement.executeUpdate();

                    query = "DELETE Networks WHERE InstallationID = ?";
                    statement = conn.prepareStatement(query);
                    statement.setInt(1, installationId);
                    statement.executeUpdate();

                    query = "DELETE WiFis WHERE InstallationID = ?";
                    statement = conn.prepareStatement(query);
                    statement.setInt(1, installationId);
                    statement.executeUpdate();
                }
            }

            if (customerTaskIds.size() > 0) {
                for (int customerTaskId : customerTaskIds) {
                    query = "DELETE Installations WHERE CustomerTaskId = ?";
                    statement = conn.prepareStatement(query);
                    statement.setInt(1, customerTaskId);
                    statement.executeUpdate();

                    query = "DELETE CustomerTasksRel WHERE CustomerTaskId = ?";
                    statement = conn.prepareStatement(query);
                    statement.setInt(1, customerTaskId);
                    statement.executeUpdate();

                    query = "DELETE CustomerTasks WHERE Id = ?";
                    statement = conn.prepareStatement(query);
                    statement.setInt(1, customerTaskId);
                    statement.executeUpdate();
                }
            }

            query = "DELETE Customers WHERE Id = ?";

            statement = conn.prepareStatement(query);
            statement.setInt(1, id);

            statement.executeUpdate();

            //Commit the transaction
            conn.commit();
        } catch (Exception exception) {
            conn.rollback();
            //rethrow exception
            throw exception;
        } finally {
            conn.close();
        }
    }
}
