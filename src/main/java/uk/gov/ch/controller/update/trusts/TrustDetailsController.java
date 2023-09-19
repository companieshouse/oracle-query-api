package uk.gov.ch.controller.update.trusts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.exception.TrustDataCountNotFoundException;
import uk.gov.ch.model.update.trusts.TrustDetails;
import uk.gov.ch.service.update.trusts.TrustDetailsService;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;
import uk.gov.companieshouse.logging.util.DataMap;

import java.util.List;

@RestController
public class TrustDetailsController {
    @Autowired
    private TrustDetailsService trustDetailsService;

    private static final Logger LOGGER = LoggerFactory.getLogger(OracleQueryApplication.APPLICATION_NAME_SPACE);

    @GetMapping("/overseas-entity/{companyNumber}/trusts/details")
    public ResponseEntity<List<TrustDetails>> getTrustDetails(@PathVariable String companyNumber) {
        DataMap dataMap = new DataMap.Builder().companyNumber(companyNumber).build();
        LOGGER.infoContext(companyNumber,
                String.format("Calling service to retrieve trust information for company number %s", companyNumber),
                dataMap.getLogMap());

        try {
            List<TrustDetails> trustDetails = trustDetailsService.getTrustDetails(companyNumber);
            LOGGER.infoContext(companyNumber,
                    String.format("Returning trusts for company number %s", companyNumber),
                    dataMap.getLogMap());
            return ResponseEntity.status(HttpStatus.OK).body(trustDetails);
        } catch (TrustDataCountNotFoundException e) {
            LOGGER.infoContext(companyNumber,
                    String.format("No trusts could be found for company number %s", companyNumber),
                    dataMap.getLogMap());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
