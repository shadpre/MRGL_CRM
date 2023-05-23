package BLL.Datavalidation;

import BE.DBEnteties.Customer;
import BE.DBEnteties.CustomerTask;
import BE.DBEnteties.Device;
import BE.Exptions.ValidationException;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ValidationHelper {

    public static ValidationResult validate(Customer customer){
        ValidationResult vr = new ValidationResult();
        if (customer.getName().length() > 60) vr.addError("Name");
        if(customer.getAddress1().length() > 60) vr.addError("Address1");
        if(customer.getAddress2().length() > 60) vr.addError("Address2");
        if(customer.getAddress3().length() > 60) vr.addError("Address3");
        if(customer.getZipcode().length() > 20) vr.addError("Zipcode");
        if(customer.getCity().length() > 60) vr.addError("City");
        if(customer.getCountry().length() > 40) vr.addError("Country");
        if(customer.getPhone().length() > 20) vr.addError("Phone");
        if(customer.getCategory().length() > 20) vr.addError("Category");
        if(customer.getTaxNo().length() > 20) vr.addError("TaxNo");

        return vr;
    }

    public static ValidationResult validate(CustomerTask ct) {
        ValidationResult vr = new ValidationResult();
        if(ct.getDescription().length() > 100) vr.addError("Description");
        if(ct.getRemarks().length() > 255) vr.addError("Remarks");
        if(ct.getStatus() < 0 || ct.getStatus() > 255) vr.addError("Status");

        return vr;
    }

    public static ValidationResult validate(Device dev) {
        ValidationResult vr = new ValidationResult();
        if (dev.getDescription().length() > 100) vr.addError("Description");
        if (dev.getRemarks().length() > 255) vr.addError("Remarks");
        if (!isValidIPv4(dev.getIP())) vr.addError("IP");
        if (!isValidSubnetMask(dev.getSubnetMask())) vr.addError("SubnetMask");
        if (dev.getUserName().length() > 255) vr.addError("UserName");
        if (dev.getPassword().length() > 255) vr.addError("Password");

        return vr;
    }

    //Public due to unit test
    public static boolean isValidIPv4(String ip) {
        String[] octets = ip.split("\\.");

        //Check for the IP address contains of 4 Octets separated by dot
        if (octets.length != 4) return false;

        for (int i = 0; i < octets.length; i++) {
            //Checks all octets is an int value
            try {
                int val = Integer.parseInt(octets[i]);

                //Just check for valid IP address format. No check for Reserved IP addresses
                if (val > 255 || val < 0) return false;
            } catch (Exception ex) {
                return false;
            }
        }
        return true;
    }

    //Public so it can be unit tested
    public static boolean isValidSubnetMask(String sm){
        String[] octets = sm.split("\\.");
        var ints = new int[]{0, 128, 192, 224, 240, 248, 252, 254, 255};
        ArrayList<Integer> acceptedValues = new ArrayList<>();

        for (int i : ints
             ) {
            acceptedValues.add(i);
        }

        boolean endOfOnes = false;
        //Check for the IP address contains of 4 Octets separated by dot
        if (octets.length != 4) return false;

        for (int i = 0; i < octets.length; i++) {
            //Checks all octets is an int value
            try {
                //Checks all octets is an int value
                int val = Integer.parseInt(octets[i]);
                //Check for legal subnetmask val
                if (! acceptedValues.contains(val)) return false;
                //Check for Binary values contains 0
                if (val == 255 && !endOfOnes) continue;

                if (val != 255 && !endOfOnes){
                    endOfOnes = true;
                    continue;
                }

                if (val != 0 && endOfOnes){
                    return false;
                }
            } catch (Exception ex) {
                return false;
            }
        }
        return true;
    }
}