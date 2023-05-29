package BE.Exptions.NotFoundExeptions;

import BE.Exptions.NotFoundException;

public class CustomerTaskNotFoundException extends NotFoundException {
    public CustomerTaskNotFoundException(String message) {
        super(message);
    }
}
