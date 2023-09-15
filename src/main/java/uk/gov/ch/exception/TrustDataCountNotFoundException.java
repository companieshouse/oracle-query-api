package uk.gov.ch.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TrustDataCountNotFoundException extends Exception {
    public TrustDataCountNotFoundException(String message) {
        super(message);
    }
}