package BLL;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubnetMaskToCidrTest {

    @Test
    void Subnetmasktest() {
        System.out.println("Subnetmask");
        String subnetMask = "255.255.128.0";

        int result = SubnetMaskToCidr.getCidr(subnetMask);
        assertEquals(17,result);

        subnetMask = "255.255.255.0";
        result = SubnetMaskToCidr.getCidr(subnetMask);
        assertEquals(24,result);
    }
}