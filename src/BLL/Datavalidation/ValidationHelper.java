package BLL.Datavalidation;

import BE.DBEnteties.Interfaces.*;
import BLL.Interfaces.IValidationHelper;
import BLL.Interfaces.IValidationResult;

import java.util.ArrayList;
import java.util.regex.Pattern;
/**
 The ValidationHelper class provides methods for validating various entities in the system.
 */
public class ValidationHelper implements IValidationHelper {

    /**
     * Validates a customer object.
     *
     * @param customer The customer object to validate.
     * @return The validation result.
     */
    public IValidationResult validate(ICustomer customer) {
        IValidationResult vr = new ValidationResult();
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

    /**
     * Validates a customer task object.
     *
     * @param ct The customer task object to validate.
     * @return The validation result.
     */
    public IValidationResult validate(ICustomerTask ct) {
        IValidationResult vr = new ValidationResult();
        if (ct.getDescription().length() > 100) vr.addError("Description");
        if (ct.getRemarks().length() > 255) vr.addError("Remarks");
        if (ct.getStatus() < 0 || ct.getStatus() > 255) vr.addError("Status");

        return vr;
    }

    /**
     * Validates a device object.
     *
     * @param dev The device object to validate.
     * @return The validation result.
     */
    public IValidationResult validate(IDevice dev) {
        IValidationResult vr = new ValidationResult();
        if (dev.getDescription().length() > 100) vr.addError("Description");
        if (dev.getRemarks().length() > 255) vr.addError("Remarks");
        if (!isValidIPv4(dev.getIP())) vr.addError("IP");
        if (!isValidSubnetMask(dev.getSubnetMask())) vr.addError("SubnetMask");
        if (dev.getUserName().length() > 255) vr.addError("UserName");
        if (dev.getPassword().length() > 255) vr.addError("Password");

        return vr;
    }

    /**
     * Validates an image object.
     *
     * @param img The image object to validate.
     * @return The validation result.
     */
    public IValidationResult validate(IImage img) {
        IValidationResult vr = new ValidationResult();
        if (img.getDescription().length() > 100) vr.addError("Description");
        if (img.getRemarks().length() > 255) vr.addError("Remarks");
        if (img.getImageType() < 0 || img.getImageType() > 255) vr.addError("ImageType");

        return vr;
    }

    /**
     * Validates an installation object.
     *
     * @param inst The installation object to validate.
     * @return The validation result.
     */
    public IValidationResult validate(IInstallation inst) {
        IValidationResult vr = new ValidationResult();
        if (inst.getDescription().length() > 100) vr.addError("Description");
        if (inst.getRemarks().length() > 255) vr.addError("Remarks");

        return vr;
    }

    /**
     * Validates a network object.
     *
     * @param network The network object to validate.
     * @return The validation result.
     */
    public IValidationResult validate(INetwork network) {
        IValidationResult vr = new ValidationResult();
        if (network.getDescription().length() > 100) vr.addError("Description");
        if (network.getRemarks().length() > 255) vr.addError("Remarks");
        if (!isValidIPv4(network.getNetworkIP())) vr.addError("NetworkIP");
        if (!isValidSubnetMask(network.getSubnetMask())) vr.addError("SubnetMask");
        // There is no check for Default GW actually is on the same network. Just check for Valid IP adr.
        if (!isValidIPv4(network.getDefaultGateway())) vr.addError("DefaultGateway");

        return vr;
    }

    /**
     * Validates a user object.
     *
     * @param user The user object to validate.
     * @return The validation result.
     */
    public IValidationResult validate(IUser user) {
        IValidationResult vr = new ValidationResult();

        if (user.getLoginName().length() > 5) vr.addError("LoginName");
        if (user.getFirstName().length() > 40) vr.addError("FirstName");
        if (user.getLastName().length() > 40) vr.addError("LastName");
        if (!isEmailValidFormat(user.getEMail())) vr.addError("Email");

        return vr;
    }

    /**
     * Validates a WiFi object.
     *
     * @param wifi The WiFi object to validate.
     * @return The validation result.
     */
    public IValidationResult validate(IWiFi wifi) {
        IValidationResult vr = new ValidationResult();

        if (wifi.getDescription().length() > 100) vr.addError("Description");
        if (wifi.getRemarks().length() > 255) vr.addError("Remarks");
        if (wifi.getSSID().length() > 32) vr.addError("SSID");
        if (wifi.getPSK().length() > 63) vr.addError("PSK");

        return vr;
    }

    /**
     * Checks if the provided email is in a valid format.
     *
     * @param email The email to validate.
     * @return True if the email is valid, false otherwise.
     */
    public boolean isEmailValidFormat(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    /**
     * Checks if the provided IP address is a valid IPv4 address.
     *
     * @param ip The IP address to validate.
     * @return True if the IP address is valid, false otherwise.
     */
    public boolean isValidIPv4(String ip) {
        String[] octets = ip.split("\\.");

        // Check if the IP address contains 4 octets separated by dots
        if (octets.length != 4) return false;

        for (int i = 0; i < octets.length; i++) {
            // Check if each octet is an integer value
            try {
                int val = Integer.parseInt(octets[i]);

                // Just check for valid IP address format. No check for reserved IP addresses
                if (val > 255 || val < 0) return false;
            } catch (Exception ex) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the provided subnet mask is a valid IPv4 subnet mask.
     *
     * @param sm The subnet mask to validate.
     * @return True if the subnet mask is valid, false otherwise.
     */
    public boolean isValidSubnetMask(String sm) {
        String[] octets = sm.split("\\.");
        int[] ints = new int[]{0, 128, 192, 224, 240, 248, 252, 254, 255};
        ArrayList<Integer> acceptedValues = new ArrayList<>();

        for (int i : ints) {
            acceptedValues.add(i);
        }

        boolean endOfOnes = false;
        // Check if the subnet address contains 4 octets separated by dots
        if (octets.length != 4) return false;

        for (int i = 0; i < octets.length; i++) {
            // Check if each octet is an integer value
            try {
                int val = Integer.parseInt(octets[i]);
                // Check for legal subnet mask value
                if (!acceptedValues.contains(val)) return false;
                // Check for binary values containing 0
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
