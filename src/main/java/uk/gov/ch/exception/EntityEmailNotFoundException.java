package uk.gov.ch.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityEmailNotFoundException extends RuntimeException {
    public EntityEmailNotFoundException(String message) {
        super(message);
    }
}
