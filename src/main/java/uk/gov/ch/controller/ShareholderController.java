package uk.gov.ch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.model.Shareholder;
import uk.gov.ch.service.ShareholderService;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;

@RestController
public class ShareholderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OracleQueryApplication.APPLICATION_NAME_SPACE);

    @Autowired
    private ShareholderService shareholderService;

    @GetMapping("/company/{companyNumber}/shareholders/count")
    public ResponseEntity getShareholdersCount(@PathVariable String companyNumber) {

        LOGGER.info("Calling service to retrieve shareholder count for corporate body ID " + companyNumber);
        int response = shareholderService.getShareholderCount(companyNumber);

        LOGGER.info("Returning shareholder count ("+ response +") for company " + companyNumber);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/company/{companyNumber}/shareholders")
    public ResponseEntity getShareholders(@PathVariable String companyNumber) {
        
        LOGGER.info("Calling service to retrieve the list of shareholders for corporate body ID " + companyNumber);
        List<Shareholder> response = shareholderService.getShareholders(companyNumber);

        LOGGER.info("Returning "+ response.size() +"shareholders for company " + companyNumber);
        return ResponseEntity.status(HttpStatus.OK).body(response);        
    }
    
}
