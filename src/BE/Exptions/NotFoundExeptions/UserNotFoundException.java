package BE.Exptions.NotFoundExeptions;

import BE.Exptions.NotFoundException;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
