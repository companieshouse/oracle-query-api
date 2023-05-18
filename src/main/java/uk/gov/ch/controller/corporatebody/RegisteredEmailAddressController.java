package uk.gov.ch.controller.corporatebody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.exception.RegisteredEmailAddressNotFoundException;
import uk.gov.ch.model.corporatebody.sqldatamodels.RegisteredEmailAddressJson;
import uk.gov.ch.service.corporatebody.impl.RegisteredEmailAddressServiceImpl;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;
import uk.gov.companieshouse.logging.util.DataMap;


@RestController
@Validated
public class RegisteredEmailAddressController {
    @Autowired
    private RegisteredEmailAddressServiceImpl registeredEmailAddressService;

    private static final Logger LOGGER = LoggerFactory.getLogger(OracleQueryApplication.APPLICATION_NAME_SPACE);

    @GetMapping("/company/{companyNumber}/registered-email-address")
    public ResponseEntity<RegisteredEmailAddressJson> getRegisteredEmailAddress(@PathVariable("companyNumber") String companyNumber) {

        DataMap dataMap = new DataMap.Builder().companyNumber(companyNumber).build();

        try {
            return ResponseEntity.ok(registeredEmailAddressService.getRegisteredEmailAddress(companyNumber));
        } catch (RegisteredEmailAddressNotFoundException e) {
            LOGGER.errorContext("The registered email address could not be found for: " + companyNumber, e, dataMap.getLogMap());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}