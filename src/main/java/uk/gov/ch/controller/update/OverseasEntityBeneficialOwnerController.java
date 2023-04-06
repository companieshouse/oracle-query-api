package uk.gov.ch.controller.update;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.exception.BeneficialOwnerNotFoundException;
import uk.gov.ch.model.update.OverseasEntityBeneficialOwner;
import uk.gov.ch.service.update.OverseasEntityBeneficialOwnerService;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;
import uk.gov.companieshouse.logging.util.DataMap;

import java.util.List;

@RestController
public class OverseasEntityBeneficialOwnerController {

    @Autowired
    private OverseasEntityBeneficialOwnerService overseasEntityBeneficialOwnerService;

    private static final Logger LOGGER = LoggerFactory.getLogger(OracleQueryApplication.APPLICATION_NAME_SPACE);

    @GetMapping("/overseas-entity/{incorporationNumber}/beneficial-owners")
    public ResponseEntity<List<OverseasEntityBeneficialOwner>> getOverseasEntityBeneficialOwners(@PathVariable String incorporationNumber) {
        DataMap dataMap = new DataMap.Builder(null).build();
        dataMap.getLogMap().put("incorporationNumber", incorporationNumber);
        LOGGER.infoContext(incorporationNumber,
                String.format("Calling service to retrieve active beneficial owner details for incorporation number %s", incorporationNumber),
                dataMap.getLogMap());

        try {
            List<OverseasEntityBeneficialOwner> details = overseasEntityBeneficialOwnerService.getBeneficialOwners(incorporationNumber);
            LOGGER.infoContext(incorporationNumber,
                    String.format("Returning beneficial owners for incorporation number %s", incorporationNumber),
                    dataMap.getLogMap());
            return ResponseEntity.status(HttpStatus.OK).body(details);
        } catch (BeneficialOwnerNotFoundException e) {
            LOGGER.errorContext(String.format("No beneficial owners could be found for incorporation number %s", incorporationNumber),
                    e, dataMap.getLogMap());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
