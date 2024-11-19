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
import uk.gov.ch.model.update.trusts.IndividualTrusteeData;
import uk.gov.ch.service.update.trusts.IndividualTrusteesService;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;
import uk.gov.companieshouse.logging.util.DataMap;

@RestController
public class IndividualTrusteesController {

    private static final Logger LOGGER = LoggerFactory.getLogger(
            OracleQueryApplication.APPLICATION_NAME_SPACE);
    @Autowired
    private IndividualTrusteesService individualTrusteesService;

    @GetMapping("/overseas-entity/trusts/{trustId}/individual-trustees")
    public ResponseEntity<List<IndividualTrusteeData>> getIndividualTrustees(
            @PathVariable String trustId) {
        DataMap dataMap = new DataMap.Builder().build();
        dataMap.getLogMap().put("trust_id", trustId);
        LOGGER.infoContext(trustId,
                String.format(
                        "Calling service to retrieve individual trustee data for trust ID: %s",
                        trustId),
                dataMap.getLogMap());

        try {
            List<IndividualTrusteeData> details = individualTrusteesService.getIndividualTrustees(
                    trustId);
            LOGGER.infoContext(trustId,
                    String.format("Returning individual trustee data for trust ID: %s", trustId),
                    dataMap.getLogMap());
            return ResponseEntity.status(HttpStatus.OK).body(details);
        } catch (TrustDataCountNotFoundException e) {
            LOGGER.infoContext(trustId,
                    String.format("No individual trustees could be found for trust ID: %s",
                            trustId),
                    dataMap.getLogMap());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
