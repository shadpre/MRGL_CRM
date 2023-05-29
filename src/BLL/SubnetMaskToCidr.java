package BLL;

import BLL.Datavalidation.ValidationHelper;
import BLL.Interfaces.IValidationHelper;

/**
 * Utility class for converting a subnet mask to CIDR notation.
 */
public class SubnetMaskToCidr {

    /**
     * Converts a subnet mask to CIDR notation.
     *
     * @param subnetMask The subnet mask to convert.
     * @return The CIDR value.
     * @throws RuntimeException if the subnet mask is invalid.
     */
    public static int getCidr(String subnetMask) {
        int cidr = 0;
        IValidationHelper validationHelper = new ValidationHelper();
        if (validationHelper.isValidSubnetMask(subnetMask)) {
            String[] octets = subnetMask.split("\\.");
            for (String octet : octets) {
                switch (octet) {
                    case "0":
                        cidr += 0;
                        break;
                    case "128":
                        cidr += 1;
                        break;
                    case "192":
                        cidr += 2;
                        break;
                    case "224":
                        cidr += 3;
                        break;
                    case "240":
                        cidr += 4;
                        break;
                    case "248":
                        cidr += 5;
                        break;
                    case "252":
                        cidr += 6;
                        break;
                    case "254":
                        cidr += 7;
                        break;
                    case "255":
                        cidr += 8;
                        break;
                    default:
                        throw new RuntimeException("Something went wrong");
                }
            }
        } else {
            throw new RuntimeException("Invalid subnet mask");
        }
        return cidr;
    }
}
