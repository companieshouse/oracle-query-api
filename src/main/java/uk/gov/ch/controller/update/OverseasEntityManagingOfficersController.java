package uk.gov.ch.controller.update;

import jakarta.validation.constraints.Pattern;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.exception.ManagingOfficerCountNotFoundException;
import uk.gov.ch.model.update.OverseasEntityManagingOfficerData;
import uk.gov.ch.service.update.OverseasEntityManagingOfficersService;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;
import uk.gov.companieshouse.logging.util.DataMap;

@RestController
public class OverseasEntityManagingOfficersController {

    private static final Logger LOGGER = LoggerFactory.getLogger(
            OracleQueryApplication.APPLICATION_NAME_SPACE);

    private OverseasEntityManagingOfficersService overseasEntityManagingOfficersService;

    public OverseasEntityManagingOfficersController(OverseasEntityManagingOfficersService overseasEntityManagingOfficersService) {
        this.overseasEntityManagingOfficersService = overseasEntityManagingOfficersService;
    }

    @GetMapping("/overseas-entity/{companyNumber}/managing-officers")
    public ResponseEntity<List<OverseasEntityManagingOfficerData>> getOverseasEntityManagingOfficers(
            @PathVariable @Pattern(regexp = "^OE[0-9]{6}$", message = "Invalid overseas entity number") String companyNumber) { // NOSONAR really do want 0-9 here not any digit

        DataMap dataMap = new DataMap.Builder().companyNumber(companyNumber).build();

        LOGGER.infoContext(companyNumber,
                String.format("Calling service to retrieve managing officers for company number %s",
                        companyNumber),
                dataMap.getLogMap());

        try {
            List<OverseasEntityManagingOfficerData> details = overseasEntityManagingOfficersService
                    .getOverseasEntityManagingOfficers(companyNumber);
            LOGGER.infoContext(companyNumber,
                    String.format("Returning managing officers for company number %s",
                            companyNumber),
                    dataMap.getLogMap());
            return ResponseEntity.status(HttpStatus.OK).body(details);
        } catch (ManagingOfficerCountNotFoundException e) {
            LOGGER.infoContext(companyNumber,
                    String.format("No managing officers could be found for company number %s",
                            companyNumber),
                    dataMap.getLogMap());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }
}
