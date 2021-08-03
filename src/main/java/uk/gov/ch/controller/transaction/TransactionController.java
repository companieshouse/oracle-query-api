package uk.gov.ch.controller.transaction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.exception.TransactionMappingException;
import uk.gov.ch.service.transaction.TransactionService;
import uk.gov.companieshouse.api.model.filinghistory.FilingApi;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;

@RestController
public class TransactionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OracleQueryApplication.APPLICATION_NAME_SPACE);

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/company/{companyNumber}/transaction-history")
    public ResponseEntity<List<FilingApi>> getTransactionHistory(@PathVariable String companyNumber) {
        Map<String, Object> logMap = new HashMap<>();
        logMap.put("company_number", companyNumber);
        LOGGER.info("Getting transaction history for " + companyNumber, logMap);
        if (companyNumber == null || companyNumber.isEmpty()) {
            logProgess("No company number", logMap);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            List<FilingApi> filingHistoryTransactions = transactionService.getTransactions(companyNumber);
            if (filingHistoryTransactions.isEmpty()) {
                logProgess("No transactions found", logMap);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            logProgess("Returning transactions for company", logMap);
            return ResponseEntity.status(HttpStatus.OK).body(filingHistoryTransactions);
        } catch (TransactionMappingException e) {
            logProgess("Exception when mapping results", logMap);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private void logProgess(String message, Map<String, Object> logMap) {
        logMap.remove("message");
        LOGGER.info(message, logMap);
    }

}
