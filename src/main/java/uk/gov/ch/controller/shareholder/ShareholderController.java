package uk.gov.ch.controller.shareholder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.model.shareholder.Shareholder;
import uk.gov.ch.service.shareholder.ShareholderService;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;

@RestController
public class ShareholderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OracleQueryApplication.APPLICATION_NAME_SPACE);

    @Autowired
    private ShareholderService shareholderService;

    @GetMapping("/company/{companyNumber}/shareholders/count")
    public ResponseEntity<Integer> getShareholdersCount(@PathVariable String companyNumber) {

        LOGGER.info("Calling service to retrieve shareholder count for company number " + companyNumber);
        int response = shareholderService.getShareholderCount(companyNumber);

        LOGGER.info("Returning shareholder count ("+ response +") for company number " + companyNumber);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/company/{companyNumber}/shareholders")
    public ResponseEntity<List<Shareholder>> getShareholders(@PathVariable String companyNumber,
            @RequestParam(name = "start_index", defaultValue = "0", required = false) int startIndex,
            @RequestParam(name = "items_per_page", defaultValue = "15", required = false) int itemsPerPage) {

        Pageable pageable = PageRequest.of(startIndex, itemsPerPage);

        LOGGER.info("Calling service to retrieve the list of shareholders for company number " + companyNumber);
        List<Shareholder> response = shareholderService.getShareholders(companyNumber, pageable);

        LOGGER.info("Returning "+ response.size() +" shareholders for company number " + companyNumber);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
}
