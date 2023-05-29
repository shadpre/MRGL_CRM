package BE.Exptions.NotFoundExeptions;

import BE.Exptions.NotFoundException;

public class CustomerNotFoundException extends NotFoundException {
    public CustomerNotFoundException(String message) {
        super(message);
    }
}
