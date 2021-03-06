package uk.gov.ch.controller.corporatebody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.exception.CorporateBodyNotFoundException;
import uk.gov.ch.service.corporatebody.CorporateBodyService;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;

@RestController
public class CorporateBodyController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OracleQueryApplication.APPLICATION_NAME_SPACE);

    @Autowired
    private CorporateBodyService corporateBodyService;

    @GetMapping("/company/{companyNumber}/action-code")
    public ResponseEntity<Long> getActionCode(@PathVariable String companyNumber) {

        LOGGER.info("Calling service to retrieve action code for company number " + companyNumber);

        try {
            Long actionCode = corporateBodyService.getActionCode(companyNumber);

            LOGGER.info("Returning action code " + actionCode + " for company " + companyNumber);

            return ResponseEntity.status(HttpStatus.OK).body(actionCode);
        } catch (CorporateBodyNotFoundException e) {
            LOGGER.info("No company could be found with company number " + companyNumber);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/company/{companyNumber}/traded-status")
    public ResponseEntity<Long> getTradedStatus(@PathVariable String companyNumber) {
        LOGGER.info("Calling service to retrieve a traded status for company number " + companyNumber);

        try {
            Long tradedStatus = corporateBodyService.getTradedStatus(companyNumber);

            LOGGER.info("Returning traded status " + tradedStatus + " for company " + companyNumber);

            return ResponseEntity.status(HttpStatus.OK).body(tradedStatus);
        } catch (CorporateBodyNotFoundException e) {
            LOGGER.info("No company could be found with company number " + companyNumber);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
