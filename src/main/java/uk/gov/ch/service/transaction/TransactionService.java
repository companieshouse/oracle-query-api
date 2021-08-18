package uk.gov.ch.service.transaction;

import uk.gov.ch.exception.TransactionMappingException;
import uk.gov.companieshouse.api.model.filinghistory.FilingHistoryApi;

public interface TransactionService {

    FilingHistoryApi getTransactions(String companyNumber) throws TransactionMappingException;

}
