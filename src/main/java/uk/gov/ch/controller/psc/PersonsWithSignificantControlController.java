package uk.gov.ch.controller.psc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.model.psc.PersonWithSignificantControl;
import uk.gov.ch.service.psc.PersonsWithSignificantControlService;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;

import java.util.List;

@RestController
public class PersonsWithSignificantControlController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OracleQueryApplication.APPLICATION_NAME_SPACE);

    @Autowired
    private PersonsWithSignificantControlService personsWithSignificantControlService;

    @GetMapping("/company/{companyNumber}/corporate-body-appointments/persons-of-significant-control")
    public ResponseEntity<List<PersonWithSignificantControl>> getPeopleWithSigniificantControl(@PathVariable String companyNumber) {
        LOGGER.info("Calling service to obtain persons with significant control for company " + companyNumber);
        List<PersonWithSignificantControl> result = personsWithSignificantControlService.getPersonsWithSignificantControl(companyNumber);
        LOGGER.info("Returning details for persons with significant control " + companyNumber);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
