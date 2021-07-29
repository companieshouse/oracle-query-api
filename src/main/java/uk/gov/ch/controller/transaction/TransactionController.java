package uk.gov.ch.controller.transaction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import uk.gov.ch.OracleQueryApplication;
import uk.gov.ch.model.transaction.jsondatamodels.FilingHistoryTransaction;
import uk.gov.ch.service.transaction.TransactionService;
import uk.gov.companieshouse.logging.Logger;
import uk.gov.companieshouse.logging.LoggerFactory;

@RestController
public class TransactionController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OracleQueryApplication.APPLICATION_NAME_SPACE);

	@Autowired
	private TransactionService transactionService;
	
	@GetMapping("/company/{companyNumber}/transaction-history")
	public ResponseEntity<List<FilingHistoryTransaction>> getTransactionHistory(@PathVariable String companyNumber) {
		LOGGER.info("Getting transaction history for " + companyNumber);
		List<FilingHistoryTransaction> filingHistoryTransactions = transactionService.getTransactions(companyNumber);
		return ResponseEntity.status(HttpStatus.OK).body(filingHistoryTransactions);
	}

}
