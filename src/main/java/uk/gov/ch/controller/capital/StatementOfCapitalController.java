package uk.gov.ch.controller.capital;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.exception.StatementOfCapitalNotFoundException;
import uk.gov.ch.model.capital.StatementOfCapital;
import uk.gov.ch.service.capital.StatementOfCapitalService;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;

import java.util.List;

@RestController
public class StatementOfCapitalController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OracleQueryApplication.APPLICATION_NAME_SPACE);

    @Autowired
    private StatementOfCapitalService statementOfCapitalService;

    @GetMapping("/company/{companyNumber}/statement-of-capital")
    public ResponseEntity<StatementOfCapital> getStatementOfCapital(@PathVariable String companyNumber) {

        LOGGER.info("Calling service to retrieve statement of capital for company number " + companyNumber);
        try {
            StatementOfCapital statementOfCapital = statementOfCapitalService.getStatementOfCapital(companyNumber);
            return ResponseEntity.status(HttpStatus.OK).body(statementOfCapital);
        } catch(StatementOfCapitalNotFoundException e) {
            LOGGER.error("No statement of capital data could be found for company " + companyNumber, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
