package uk.gov.ch.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CorporateBodyDetailsEmailAddressNotFoundException extends RuntimeException {

    public CorporateBodyDetailsEmailAddressNotFoundException(String message) {
        super(message);
    }
}
