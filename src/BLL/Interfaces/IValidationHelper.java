package BLL.Interfaces;

import BE.DBEnteties.Interfaces.*;

public interface IValidationHelper {
    IValidationResult validate(ICustomer customer);

    IValidationResult validate(ICustomerTask ct);

    IValidationResult validate(IDevice dev);

    IValidationResult validate(IImage img);

    IValidationResult validate(IInstallation inst);

    IValidationResult validate(INetwork network);

    IValidationResult validate(IUser user);

    IValidationResult validate(IWiFi wifi);

    boolean isEmailValidFormat(String email);

    //Public due to unit test
    boolean isValidIPv4(String ip);

    //Public so it can be unit tested
    boolean isValidSubnetMask(String sm);
}
