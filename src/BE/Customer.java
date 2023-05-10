package BE;

public class Customer {
    private int Id;
    private String Name;
    private String Address1;
    private String Address2;
    private String Address3;
    private String Zipcode;
    private String City;
    private String Country;
    private String Phone;
    private String Category;
    private String TaxNo;

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

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getAddress1() {
        return Address1;
    }

    public String getAddress2() {
        return Address2;
    }

    public String getAddress3() {
        return Address3;
    }

    public String getZipcode() {
        return Zipcode;
    }

    public String getCity() {
        return City;
    }

    public String getCountry() {
        return Country;
    }

    public String getPhone() {
        return Phone;
    }

    public String getCategory() {
        return Category;
    }

    public String getTaxNo() {
        return TaxNo;
    }
}
