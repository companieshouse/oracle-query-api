package uk.gov.ch.controller.officer.active;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.exception.InvalidActiveOfficersCountFoundException;
import uk.gov.ch.model.officer.active.ActiveOfficerDetails;
import uk.gov.ch.service.officer.active.ActiveOfficerDetailsService;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;

@RestController
public class ActiveOfficerDetailsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(
            OracleQueryApplication.APPLICATION_NAME_SPACE);
    @Autowired
    private ActiveOfficerDetailsService service;

    @GetMapping("/company/{companyNumber}/officers/active")
    public ResponseEntity<List<ActiveOfficerDetails>> getActiveOfficersDetails(
            @PathVariable String companyNumber,
            @RequestParam(name = "start_index", defaultValue = "0", required = false) int startIndex,
            @RequestParam(name = "items_per_page", defaultValue = "15", required = false) int itemsPerPage) {

        LOGGER.info("Calling service to retrieve active officers' details for company number "
                + companyNumber);
        Pageable pageable = PageRequest.of(startIndex, itemsPerPage);

        try {
            List<ActiveOfficerDetails> details = service.getActiveOfficersDetails(companyNumber,
                    pageable);
            LOGGER.info(String.format("Returning active officers for company number %s",
                    companyNumber));
            return ResponseEntity.status(HttpStatus.OK).body(details);
        } catch (InvalidActiveOfficersCountFoundException e) {
            LOGGER.info(String.format("No active officers could be found for company number %s",
                    companyNumber));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
