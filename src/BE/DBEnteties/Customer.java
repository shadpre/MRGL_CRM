package BE.DBEnteties;


public class Customer implements BE.DBEnteties.Interfaces.ICustomer {
    private final int Id;
    private final String Name;
    private final String Address1;
    private final String Address2;
    private final String Address3;
    private final String Zipcode;
    private final String City;
    private final String Country;
    private final String Phone;
    private final String Category;
    private final String TaxNo;

    public Customer(int id, String name, String address1, String address2, String address3, String zipcode, String city, String country, String phone, String category, String taxNo) {
        Id = id;
        Name = name;
        Address1 = address1;
        Address2 = address2;
        Address3 = address3;
        Zipcode = zipcode;
        City = city;
        Country = country;
        Phone = phone;
        Category = category;
        TaxNo = taxNo;
    }

    @Override
    public int getId() {
        return Id;
    }

    @Override
    public String getName() {
        return Name;
    }

    @Override
    public String getAddress1() {
        return Address1;
    }

    @Override
    public String getAddress2() {
        return Address2;
    }

    @Override
    public String getAddress3() {
        return Address3;
    }

    @Override
    public String getZipcode() {
        return Zipcode;
    }

    @Override
    public String getCity() {
        return City;
    }

    @Override
    public String getCountry() {
        return Country;
    }

    @Override
    public String getPhone() {
        return Phone;
    }

    @Override
    public String getCategory() {
        return Category;
    }

    @Override
    public String getTaxNo() {
        return TaxNo;
    }
}
