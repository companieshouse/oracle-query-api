package uk.gov.ch.controller.register;

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
import uk.gov.ch.model.register.RegisterLocation;
import uk.gov.ch.service.register.RegisterLocationService;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;

import java.util.List;

@RestController
public class RegisterLocationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OracleQueryApplication.APPLICATION_NAME_SPACE);

    @Autowired
    private RegisterLocationService registerLocationService;

    @GetMapping("/company/{companyNumber}/register/location")
    public ResponseEntity<List<RegisterLocation>> getRegisterLocation(@PathVariable String companyNumber,
           @RequestParam(name = "start_index", defaultValue = "0", required = false) int startIndex,
           @RequestParam(name = "items_per_page", defaultValue = "15", required = false) int itemsPerPage) {

        Pageable pageable = PageRequest.of(startIndex, itemsPerPage);
        LOGGER.info("Calling service to retrieve register location for company number: " + companyNumber);
        List<RegisterLocation> result = registerLocationService.getRegisterLocation(companyNumber, pageable);
        LOGGER.info("Retrieved results for register location for company number " + companyNumber);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
