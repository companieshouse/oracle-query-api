package uk.gov.ch.controller.update;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.exception.BeneficialOwnerCountNotFoundException;
import uk.gov.ch.model.update.OverseasEntityBeneficialOwner;
import uk.gov.ch.service.update.OverseasEntityBeneficialOwnerService;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;
import uk.gov.companieshouse.logging.util.DataMap;

@RestController
public class OverseasEntityBeneficialOwnerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(
            OracleQueryApplication.APPLICATION_NAME_SPACE);
    @Autowired
    private OverseasEntityBeneficialOwnerService overseasEntityBeneficialOwnerService;

    @GetMapping("/overseas-entity/{companyNumber}/beneficial-owners")
    public ResponseEntity<List<OverseasEntityBeneficialOwner>> getOverseasEntityBeneficialOwners(
            @PathVariable String companyNumber) {
        DataMap dataMap = new DataMap.Builder().companyNumber(companyNumber).build();
        LOGGER.infoContext(companyNumber,
                String.format(
                        "Calling service to retrieve active beneficial owner details for company number %s",
                        companyNumber),
                dataMap.getLogMap());

        try {
            List<OverseasEntityBeneficialOwner> details = overseasEntityBeneficialOwnerService.getBeneficialOwners(
                    companyNumber);
            LOGGER.infoContext(companyNumber,
                    String.format("Returning beneficial owners for company number %s",
                            companyNumber),
                    dataMap.getLogMap());
            return ResponseEntity.status(HttpStatus.OK).body(details);
        } catch (BeneficialOwnerCountNotFoundException e) {
            LOGGER.infoContext(companyNumber,
                    String.format("No beneficial owners could be found for company number %s",
                            companyNumber),
                    dataMap.getLogMap());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
