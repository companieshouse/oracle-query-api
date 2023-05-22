package uk.gov.ch.controller.update;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.exception.CorporateBodyDetailsEmailAddressNotFoundException;
import uk.gov.ch.model.update.OverseasEntityDataJson;
import uk.gov.ch.service.update.impl.OverseasOverseasEntityDataServiceImpl;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;
import uk.gov.companieshouse.logging.util.DataMap;

import javax.validation.constraints.Pattern;

@RestController
@Validated
public class OverseasEntityDataController {
    @Autowired
    private OverseasOverseasEntityDataServiceImpl entityDataService;

    private static final Logger LOGGER = LoggerFactory.getLogger(OracleQueryApplication.APPLICATION_NAME_SPACE);

    @GetMapping("/overseas-entity/{companyNumber}/entity-data")
    public ResponseEntity<OverseasEntityDataJson> getEntityEmail(@PathVariable("companyNumber")
                                                                 @Pattern(regexp = "^OE\\d{6}$", message = "Invalid overseas entity number") String companyNumber) {

        DataMap dataMap = new DataMap.Builder().companyNumber(companyNumber).build();

        try {
            return ResponseEntity.ok(entityDataService.getEntityEmail(companyNumber));
        } catch (CorporateBodyDetailsEmailAddressNotFoundException e) {
            LOGGER.errorContext("The overseas entity email address could not be found for: " + companyNumber, e, dataMap.getLogMap());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}