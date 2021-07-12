package uk.gov.ch.controller.officer.active;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.exception.NoActiveOfficersFoundException;
import uk.gov.ch.exception.ServiceException;
import uk.gov.ch.model.officer.active.ActiveOfficerDetails;
import uk.gov.ch.service.officer.active.ActiveOfficerDetailsService;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;

@RestController
public class ActiveOfficerDetailsController {

    @Autowired
    private ActiveOfficerDetailsService service;

    private static final Logger LOGGER = LoggerFactory.getLogger(OracleQueryApplication.APPLICATION_NAME_SPACE);

    @GetMapping("/company/{companyNumber}/officer/active")
    public ResponseEntity<ActiveOfficerDetails> getActiveOfficerDetails(@PathVariable String companyNumber) {

        LOGGER.info("Calling service to retrieve active director for company number " + companyNumber);

        try {
            ActiveOfficerDetails details = service.getActiveOfficerDetails(companyNumber);
            LOGGER.info("Returning active officer for company number " + companyNumber);
            return ResponseEntity.status(HttpStatus.OK).body(details);
        } catch (NoActiveOfficersFoundException e) {
            LOGGER.info("No active officer could be found for company number " + companyNumber);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch ( ServiceException e) {
            LOGGER.info("More than one active officer could be found for company number " + companyNumber);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
