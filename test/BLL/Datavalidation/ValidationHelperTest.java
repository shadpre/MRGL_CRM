package BLL.Datavalidation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidationHelperTest {

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
}