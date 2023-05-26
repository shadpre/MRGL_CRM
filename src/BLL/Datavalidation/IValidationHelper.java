package BLL.Datavalidation;

import BE.DBEnteties.*;

import java.util.ArrayList;
import java.util.regex.Pattern;

public interface IValidationHelper {
    static ValidationResult validate(Customer customer) {
        ValidationResult vr = new ValidationResult();
        if (customer.getName().length() > 60) vr.addError("Name");
        if (customer.getAddress1().length() > 60) vr.addError("Address1");
        if (customer.getAddress2().length() > 60) vr.addError("Address2");
        if (customer.getAddress3().length() > 60) vr.addError("Address3");
        if (customer.getZipcode().length() > 20) vr.addError("Zipcode");
        if (customer.getCity().length() > 60) vr.addError("City");
        if (customer.getCountry().length() > 40) vr.addError("Country");
        if (customer.getPhone().length() > 20) vr.addError("Phone");
        if (customer.getCategory().length() > 20) vr.addError("Category");
        if (customer.getTaxNo().length() > 20) vr.addError("TaxNo");

        return vr;
    }

    static ValidationResult validate(CustomerTask ct) {
        ValidationResult vr = new ValidationResult();
        if (ct.getDescription().length() > 100) vr.addError("Description");
        if (ct.getRemarks().length() > 255) vr.addError("Remarks");
        if (ct.getStatus() < 0 || ct.getStatus() > 255) vr.addError("Status");

        return vr;
    }

    static ValidationResult validate(Device dev) {
        ValidationResult vr = new ValidationResult();
        if (dev.getDescription().length() > 100) vr.addError("Description");
        if (dev.getRemarks().length() > 255) vr.addError("Remarks");
        if (!isValidIPv4(dev.getIP())) vr.addError("IP");
        if (!isValidSubnetMask(dev.getSubnetMask())) vr.addError("SubnetMask");
        if (dev.getUserName().length() > 255) vr.addError("UserName");
        if (dev.getPassword().length() > 255) vr.addError("Password");

        return vr;
    }

    static ValidationResult validate(Image img) {
        ValidationResult vr = new ValidationResult();
        if (img.getDescription().length() > 100) vr.addError("Description");
        if (img.getRemarks().length() > 255) vr.addError("Remarks");
        if (img.getImageType() < 0 || img.getImageType() > 255) vr.addError("ImageType");

        return vr;
    }

    static ValidationResult validate(Installation inst) {
        ValidationResult vr = new ValidationResult();
        if (inst.getDescription().length() > 100) vr.addError("Description");
        if (inst.getRemarks().length() > 255) vr.addError("Remarks");

        return vr;
    }

    static ValidationResult validate(Network network) {
        ValidationResult vr = new ValidationResult();
        if (network.getDescription().length() > 100) vr.addError("Description");
        if (network.getRemarks().length() > 255) vr.addError("Remarks");
        if (!isValidIPv4(network.getNetworkIP())) vr.addError("NetworkIP");
        if (!isValidSubnetMask(network.getSubnetMask())) vr.addError("SubnetMask");
        // There is no check for Default GW actually is on the same network. Just check for Valid IP adr.
        if (!isValidIPv4(network.getDefaultGateway())) vr.addError("DefaultGateway");

        return vr;
    }

    static ValidationResult validate(User user) {
        ValidationResult vr = new ValidationResult();

        if (user.getLoginName().length() > 5) vr.addError("LoginName");
        if (user.getFirstName().length() > 40) vr.addError("FirstName");
        if (user.getLastName().length() > 40) vr.addError("LastName");
        if (!isEmailValidFormat(user.getEMail())) vr.addError("Email");

        return vr;
    }

    static ValidationResult validate(WiFi wifi) {
        ValidationResult vr = new ValidationResult();

        if (wifi.getDescription().length() > 100) vr.addError("Description");
        if (wifi.getRemarks().length() > 255) vr.addError("Remarks");
        if (wifi.getSSID().length() > 32) vr.addError("SSID");
        if (wifi.getPSK().length() > 63) vr.addError("PSK");

        return vr;
    }

    static boolean isEmailValidFormat(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    //Public due to unit test
    static boolean isValidIPv4(String ip) {
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
    static boolean isValidSubnetMask(String sm) {
        String[] octets = sm.split("\\.");
        var ints = new int[]{0, 128, 192, 224, 240, 248, 252, 254, 255};
        ArrayList<Integer> acceptedValues = new ArrayList<>();

        for (int i : ints
        ) {
            acceptedValues.add(i);
        }

        boolean endOfOnes = false;
        //Check for the Subnet address contains of 4 Octets separated by dot
        if (octets.length != 4) return false;

        for (int i = 0; i < octets.length; i++) {
            //Checks all octets is an int value
            try {
                //Checks all octets is an int value
                int val = Integer.parseInt(octets[i]);
                //Check for legal subnetmask val
                if (!acceptedValues.contains(val)) return false;
                //Check for Binary values contains 0
                if (val == 255 && !endOfOnes) continue;

                if (val != 255 && !endOfOnes) {
                    endOfOnes = true;
                    continue;
                }

                if (val != 0 && endOfOnes) {
                    return false;
                }
            } catch (Exception ex) {
                return false;
            }
        }
        return true;
    }
}
