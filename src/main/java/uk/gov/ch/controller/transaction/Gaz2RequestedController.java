package uk.gov.ch.controller.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.model.transaction.jsondatamodels.Gaz2Transaction;
import uk.gov.ch.service.transaction.Gaz2RequestedService;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;

@RestController
public class Gaz2RequestedController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OracleQueryApplication.APPLICATION_NAME_SPACE);

    @Autowired
    private Gaz2RequestedService gaz2RequestedService;

    @GetMapping("/company/{companyNumber}/gaz2-requested")
    public ResponseEntity<Gaz2Transaction> getGaz2Requested(@PathVariable String companyNumber) {

        LOGGER.info("Checking if Gaz2 requested for: " + companyNumber);

        Gaz2Transaction requestedGaz2 = gaz2RequestedService.getRequestedGaz2(companyNumber);

        LOGGER.info("Returning gaz2 " + requestedGaz2 + " for company " + companyNumber);

        return ResponseEntity.status(HttpStatus.OK).body(requestedGaz2);
    }
}
