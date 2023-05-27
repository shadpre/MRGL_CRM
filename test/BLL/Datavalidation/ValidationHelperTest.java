package BLL.Datavalidation;

import BE.DBEnteties.Customer;
import BLL.Interfaces.IValidationHelper;
import BLL.Interfaces.IValidationResult;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.*;

class ValidationHelperTest {


    IValidationHelper ValidationHelper = new ValidationHelper();

    @Test
    void isValidIPv4() {
        System.out.println("IPV4");
        String ip = "192.168.1.4";
        System.out.println(ip);
        boolean result = ValidationHelper.isValidIPv4(ip);
        assertEquals(true, result);
        System.out.println();
    }

    @Test
    void isValidIPv4_1(){
        System.out.println("IPV4");
        String ip = "192.558.1.4";
        System.out.println(ip);
        boolean result = ValidationHelper.isValidIPv4(ip);
        assertEquals(false, result);
        System.out.println();
    }
    @Test
    void isValidIPv4_2(){
        System.out.println("IPV4");
        String ip = "192.168.1.a";
        System.out.println(ip);
        boolean result = ValidationHelper.isValidIPv4(ip);
        assertEquals(false, result);
        System.out.println();
    }
    @Test
    void isValidSubnetMask() {
        System.out.println("Subnet Mask");
        String subnetMask  = "255.255.128.0";
        System.out.println(subnetMask);
        boolean result = ValidationHelper.isValidSubnetMask(subnetMask);
        assertEquals(true,result);
        System.out.println();
    }

    @Test
    void isValidSubnetMask_1(){
        System.out.println("Subnet Mask");
        String subnetMask  = "255.255.128.254";
        System.out.println(subnetMask);
        boolean result = ValidationHelper.isValidSubnetMask(subnetMask);
        assertEquals(false,result);
        System.out.println();
    }

    @Test
    void isValidSubnetMask_2() {
        System.out.println("Subnet Mask");
        String subnetMask  = "Not a Subnet Mask";
        System.out.println(subnetMask);
        boolean result = ValidationHelper.isValidSubnetMask(subnetMask);
        assertEquals(false,result);
        System.out.println();
    }

    @Test
    void isValidEmail() {
        System.out.println("Email");
        String GoodEmail = "Test@test.dk";
        System.out.println(GoodEmail);
        boolean result = ValidationHelper.isEmailValidFormat(GoodEmail);
        assertEquals(true,result);
        System.out.println();
    }

    @Test
    void isValidEmail_1() {
        System.out.println("Email");
        String BadEmail = "Not at mail";
        System.out.println(BadEmail);
        boolean result = ValidationHelper.isEmailValidFormat(BadEmail);
        assertEquals(false,result);
        System.out.println();
    }

    @Test
    void customerValidation() {
        System.out.println("Customer");
        Customer customer = new Customer(
                1,
                "Sørensens Sengebutik",
                "Sovegade 777",
                "Sengeløse",
                "",
                "2630",
                "Taastrup",
                "DK",
                "33473344",
                "B2B",
                "12345678");
        IValidationResult vr = ValidationHelper.validate(customer);

        assertEquals(true, vr.hasNoError());
        System.out.println();
    }

    @Test
    void customerValidation_1(){
        System.out.println("Customer");
        Customer customer = new Customer(
                1,
                "Sørensens Sengebutik",
                "Sovegade 777",
                "Sengeløse",
                "",
                "2630",
                "Taastrup",
                "DK",
                "12345678901234567890123",
                "B2B",
                "12345678");

        IValidationResult vr = ValidationHelper.validate(customer);

        assertEquals(false, vr.hasNoError());
        assertEquals("Phone", vr.getErrors().get(0));

    }
}