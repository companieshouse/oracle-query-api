package uk.gov.ch.service.transaction;

import java.util.List;

import uk.gov.companieshouse.api.model.filinghistory.FilingApi;

public interface TransactionService {
	
	List<FilingApi> getTransactions(String companyNumber);

}
