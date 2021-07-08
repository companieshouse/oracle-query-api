package uk.gov.ch.controller.officer.active;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.model.officer.active.ActiveOfficerDetails;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;

@RestController
public class ActiveOfficerDetailsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OracleQueryApplication.APPLICATION_NAME_SPACE);

    @GetMapping("/company/{companyNumber}/officer/active")
    public ResponseEntity<ActiveOfficerDetails> getActiveOfficerDetails() {
        return ResponseEntity.ok().build();
    }
}
