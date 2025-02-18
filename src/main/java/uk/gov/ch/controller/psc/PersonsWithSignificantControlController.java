package uk.gov.ch.controller.psc;

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
import uk.gov.ch.model.psc.PersonWithSignificantControl;
import uk.gov.ch.service.psc.PersonsWithSignificantControlService;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;

@RestController
public class PersonsWithSignificantControlController {

    private static final Logger LOGGER = LoggerFactory.getLogger(
            OracleQueryApplication.APPLICATION_NAME_SPACE);

    @Autowired
    private PersonsWithSignificantControlService personsWithSignificantControlService;

    @GetMapping("/company/{companyNumber}/corporate-body-appointments/persons-of-significant-control")
    public ResponseEntity<List<PersonWithSignificantControl>> getPeopleWithSignificantControl(
            @PathVariable String companyNumber,
            @RequestParam(name = "start_index", defaultValue = "0", required = false) int startIndex,
            @RequestParam(name = "items_per_page", defaultValue = "15", required = false) int itemsPerPage) {

        Pageable pageable = PageRequest.of(startIndex, itemsPerPage);

        LOGGER.info("Calling service to obtain persons with significant control for company "
                + companyNumber);
        List<PersonWithSignificantControl> result = personsWithSignificantControlService.getPersonsWithSignificantControl(
                companyNumber, pageable);
        LOGGER.info("Returning details for persons with significant control " + companyNumber);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
