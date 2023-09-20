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
import uk.gov.ch.model.update.trusts.TrustLinkData;
import uk.gov.ch.service.update.trusts.TrustLinksService;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;
import uk.gov.companieshouse.logging.util.DataMap;

@RestController
public class TrustLinksController {

    private static final Logger LOGGER = LoggerFactory.getLogger(
            OracleQueryApplication.APPLICATION_NAME_SPACE);
    @Autowired
    private TrustLinksService trustLinksService;

    @GetMapping("overseas-entity/{oeNumber}/trusts/beneficial-owners/links")
    public ResponseEntity<List<TrustLinkData>> getTrustLinkData(
            @PathVariable String oeNumber) {

        DataMap dataMap = new DataMap.Builder().companyNumber(oeNumber).build();
        LOGGER.infoContext(oeNumber,
                "Calling service to retrieve trust link data for OE Number: " + oeNumber,
                dataMap.getLogMap());

        try {
            List<TrustLinkData> details = trustLinksService.getTrustLinkData(oeNumber);
            LOGGER.infoContext(oeNumber, "Returning trust link data for OE Number: " + oeNumber,
                    dataMap.getLogMap());
            return ResponseEntity.status(HttpStatus.OK).body(details);
        } catch (TrustDataCountNotFoundException e) {
            LOGGER.infoContext(oeNumber, "No trust link data for OE Number: " + oeNumber,
                    dataMap.getLogMap());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
