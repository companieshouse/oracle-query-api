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

    @GetMapping("/shareholders/{corporateBodyId}/count")
    public ResponseEntity getShareholdersCount(@PathVariable String corporateBodyId) {

        LOGGER.info("Calling service to retrieve shareholder count for corporate body ID " + corporateBodyId);
        int response = shareholderService.getShareholderCount(corporateBodyId);

        LOGGER.info("Returning shareholder count ("+ response +") for company " + corporateBodyId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/shareholders/{corporateBodyId}/list")
    public ResponseEntity getShareholders(@PathVariable String corporateBodyId) {
        
        LOGGER.info("Calling service to retrieve the list of shareholders for corporate body ID " + corporateBodyId);
        List<Shareholder> response = shareholderService.getShareholders(corporateBodyId);

        LOGGER.info("Returning "+ response.size() +"shareholders for company " + corporateBodyId);
        return ResponseEntity.status(HttpStatus.OK).body(response);        
    }
    
}
