package uk.gov.ch.service.transaction;

import java.util.List;

import uk.gov.ch.model.transaction.jsondatamodels.FilingHistoryTransaction;

public interface TransactionService {
	
	List<FilingHistoryTransaction> getTransactions(String companyNumber);

}
