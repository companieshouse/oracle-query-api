package uk.gov.ch.controller.update.trusts;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private CorporateTrusteesService corporateTrusteesService;

    @GetMapping("/overseas-entity/trusts/{trustId}/corporate-trustees")
    public ResponseEntity<List<CorporateTrusteeData>> getCorporateTrusteeData(
            @PathVariable String trustId) {

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
