package uk.gov.ch.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ManagingOfficerCountNotFoundException extends Exception {

    public ManagingOfficerCountNotFoundException(String message) {
        super(message);
    }
}
