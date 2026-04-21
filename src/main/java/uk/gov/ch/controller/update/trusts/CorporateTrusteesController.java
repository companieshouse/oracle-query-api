package uk.gov.ch.controller.update.trusts;

import jakarta.validation.constraints.Pattern;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.exception.TrustDataCountNotFoundException;
import uk.gov.ch.model.update.trusts.CorporateTrusteeData;
import uk.gov.ch.service.update.trusts.CorporateTrusteesService;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;

@RestController
public class CorporateTrusteesController {

    private static final Logger LOGGER = LoggerFactory.getLogger(
            OracleQueryApplication.APPLICATION_NAME_SPACE);

    private CorporateTrusteesService corporateTrusteesService;

    public CorporateTrusteesController(CorporateTrusteesService corporateTrusteesService) {
        this.corporateTrusteesService = corporateTrusteesService;
    }

    @GetMapping("/overseas-entity/trusts/{trustId}/corporate-trustees")
    public ResponseEntity<List<CorporateTrusteeData>> getCorporateTrusteeData(
            @PathVariable @Pattern(regexp = "^[0-9]+$", message = "Invalid trust ID") String trustId) { // NOSONAR really do want 0-9 here not any digit

        LOGGER.info("Calling service to retrieve Corporate Trustee Data for Trust Id " + trustId);

        try {
            List<CorporateTrusteeData> corporateTrusteeData = corporateTrusteesService.getCorporateTrusteeData(
                    trustId);

            LOGGER.info("Returning Corporate Trustee Data for Trust Id " + trustId);

            return ResponseEntity.status(HttpStatus.OK).body(corporateTrusteeData);
        } catch (TrustDataCountNotFoundException e) {
            LOGGER.info("No Corporate Trustee Data can be found for Trust Id " + trustId);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
