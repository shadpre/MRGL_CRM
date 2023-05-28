package GUI.Model;

import BE.DBEnteties.Interfaces.*;
import BLL.Datavalidation.ValidationHelper;
import BLL.Interfaces.IValidationHelper;
import BLL.Interfaces.IValidationResult;

public class ValidationModel {
    private IValidationHelper vh = new ValidationHelper();

    public IValidationResult validate(ICustomer customer){
        return vh.validate(customer);
    }

    public IValidationResult validate(ICustomerTask ct) {
        return vh.validate(ct);
    }

    public IValidationResult validate(IDevice dev) {
        return vh.validate(dev);
    }

    public IValidationResult validate(IImage img) {
        return vh.validate(img);
    }

    public IValidationResult validate(IInstallation inst) {
        return vh.validate(inst);
    }

    public IValidationResult validate(INetwork network) {
        return vh.validate(network);
    }

    public IValidationResult validate(IUser user) {
        return vh.validate(user);
    }

    public IValidationResult validate(IWiFi wifi) {
        return vh.validate(wifi);
    }
}
