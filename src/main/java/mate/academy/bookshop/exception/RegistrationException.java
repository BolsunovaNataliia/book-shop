package mate.academy.bookshop.exception;

import org.springframework.http.HttpStatus;

public class RegistrationException extends Exception {
    public RegistrationException(String message, HttpStatus httpStatus) {
        super(message);
    }

    public RegistrationException(String message) {
        super(message);
    }
}
