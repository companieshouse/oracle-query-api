package uk.gov.ch.controller.officer;

import jakarta.validation.constraints.Pattern;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.exception.NoOfficersExistingException;
import uk.gov.ch.exception.OfficersMappingException;
import uk.gov.ch.service.officer.OfficerService;
import uk.gov.companieshouse.api.model.officers.OfficersApi;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;

@RestController
public class OfficerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(
            OracleQueryApplication.APPLICATION_NAME_SPACE);

    private final OfficerService officerService;

    public OfficerController(OfficerService officerService) {
        this.officerService = officerService;
    }

    @GetMapping("/company/{companyNumber}/officers")
    public ResponseEntity<OfficersApi> getOfficers(
        @PathVariable @Pattern(regexp = "^[A-Z0-9]+$", message = "Invalid company number") String companyNumber) { // NOSONAR really do want 0-9 here not any digit
        Map<String, Object> debugMap = new HashMap<>();
        debugMap.put("company_number", companyNumber);
        LOGGER.info("Calling service to retrieve all company officers", debugMap);

        try {
            OfficersApi response = officerService.getOfficers(companyNumber);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (OfficersMappingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (NoOfficersExistingException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
