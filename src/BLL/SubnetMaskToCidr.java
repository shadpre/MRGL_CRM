package BLL;

import BLL.Datavalidation.ValidationHelper;

public class SubnetMaskToCidr {
    public static int getCidr(String subnetmask){
        int cidr = 0;
        if (ValidationHelper.isValidSubnetMask(subnetmask)){
            String[] octets = subnetmask.split("\\.");
            for (String octet: octets
                 ) {
                switch (octet){
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
                    default: throw new RuntimeException("Someting went wrong");
                }
            }
        }
        return cidr;
    }
}
